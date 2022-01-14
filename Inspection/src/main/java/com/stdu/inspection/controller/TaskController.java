package com.stdu.inspection.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.DamageImage;
import com.stdu.inspection.pojo.Task;
import com.stdu.inspection.pojo.TaskComplete;
import com.stdu.inspection.pojo.TaskProcess;
import com.stdu.inspection.service.DamageService;
import com.stdu.inspection.service.TaskService;
import com.stdu.inspection.utils.ConstUtil;
import com.stdu.inspection.utils.FileUtil;
import com.stdu.inspection.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    DamageService damageService;

    /**
     * 根据时间查询今日总任务数量（检查量）
     * 对接 实时监测-实时数据界面 右上角的每日检查量
     * @param time
     * @return
     * 2021年11月7日23:25:30
     */
    @RequestMapping(value = "/taskCountToday", method = RequestMethod.GET)
    public Msg getTaskCountToday(
            @RequestParam(value = "time") String time
    ) {
        Integer taskCountToday = taskService.getTaskCountByTime(time);
        return Msg.success().add("taskCountToday", taskCountToday);
    }

    /**
     * 通过任务id获取任务对象
     *
     * @param taskId 任务Id
     * @return
     */
    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public Msg getTaskProcessById(
            @RequestParam(value = "taskid") String taskId
    ) {
        TaskProcess taskProcess = taskService.getTaskProcessById(taskId);
        return Msg.success().add("taskProcess", taskProcess);
    }

    /**
     * 获取处于某个状态的任务的列表
     *
     * @param status 状态
     * @param pn     页码
     * @param limit  容量
     * @return
     */
    @RequestMapping(value = "/process/{status}", method = RequestMethod.GET)
    public Msg listTaskByProcess(
            @PathVariable(value = "status") String status,
            @RequestParam(value = "pn", defaultValue = "1") String pn,
            @RequestParam(value = "limit", defaultValue = "10") String limit
    ) {
        IPage<TaskProcess> iPage = taskService.listTaskByProcess(status, pn, limit);
        return Msg.success().add("pageInfo", iPage);
    }


    @RequestMapping(value = "/user/status")
    public Msg getUserTaskStatusCountToday(
        @RequestParam(value = "userId") String id
    ){


        return Msg.success();

    }


    /**
     * 【接单，用户接收巡检任务】
     *  向任务表中插入一条数据
     * @param damageId 损伤id
     * @param userId 接收用户的用户id
     * @return
     */
    @RequestMapping(value = "/acquire", method = RequestMethod.POST)
    public Msg insertTask(
            @RequestParam("damageid") String damageId,
            @RequestParam("id")String userId
    ){
        // 先向任务表中插入一条这个用户对于这个损伤的工单
        taskService.insert(Integer.parseInt(damageId), Integer.parseInt(userId));

        // 然后在damage表中将status置为“已接收任务”
        damageService.updateDamageStatus(Integer.parseInt(damageId), ConstUtil.DAMAGE_REPAIRING);

        return Msg.success().add("suc", true);
    }


    /**
     * 【分页获取用户已经接单的任务列表】
     * @param userId 用户id
     * @param pn 页码
     * @param limit 容量
     * @return
     */
    @RequestMapping(value = "/user/acquire", method = RequestMethod.GET)
    public Msg listTaskAcquiredByUser(
            @RequestParam(value = "id") String userId,
            @RequestParam(value = "pn", defaultValue = "1") String pn,
            @RequestParam(value = "limit", defaultValue = "5") String limit
    ){
        IPage<TaskProcess> ipage = taskService.listTaskAcquiredByUser(userId, pn, limit);
        return Msg.success().add("list", ipage);
    }


    /**
     * 【提交任务以审核】
     * 设置任务的status 位 TASK_COMMIT
     * @param taskId 任务 id
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Msg submitTask(
            @RequestParam(value = "id")String taskId,
            @RequestParam(value = "description") String description
    ){
        boolean suc = taskService.updateTaskStatus(taskId, ConstUtil.TASK_COMMIT);
        return Msg.success().add("suc", suc);
    }


    /**
     * 【上传提交任务的图片】
     * @param file 图片
     * @param id 任务id
     * @return
     */
    @RequestMapping(value = "/put/{id}", method = RequestMethod.POST)
    public Msg uploadDamageImage(
            @RequestParam(value = "file") MultipartFile file,
            @PathVariable(value = "id") String id
    ) {
        // 判空
        if (file == null) {
            return Msg.fail().add("msg", "请选择要上传的图片");
        }
        // 判断大小
        if (file.getSize() > 1024 * 1024 * 5) {
            return Msg.fail().add("msg", "文件大小不能大于5M");
        }
        //获取文件后缀
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return Msg.fail().add("msg", "请选择jpg,jpeg,gif,png格式的图片");
        }
        // 目标地址
        String destDir = ConstUtil.TASK_COMPLETE_PICTURE;
        File file1 = new File(destDir);
        if(!file1.exists()) file1.mkdir();
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            // 保存图片
            FileUtil.savePicture(file, destDir, filename);
            // 向任务完成的表中插入图片
            boolean suc = taskService.insertCompletePicture(id, filename);
            return Msg.success().add("suc", suc);
        } catch (Exception e) {
            return Msg.fail();
        }
    }


    /**
     * 【取消任务】
     * @param id 任务id
     * @param reason 原因
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public Msg cancelTask(
            @RequestParam(value = "taskid") String id,
            @RequestParam(value = "desc")String reason
    ){
        // 从任务表中删除这个记录
        Task task = taskService.deleteTaskById(id);
        // 修改damage表中这个损伤的damage
        damageService.updateDamageStatus(task.getDamageId(), ConstUtil.DAMAGE_REPAIRING);
        return Msg.success();
    }

    /**
     * 【获取用户正在审核的任务列表】
     * @param userId 用户id
     * @param pn 页码
     * @param limit 容量
     * @return
     */
    @RequestMapping(value = "/user/checking", method = RequestMethod.GET)
    public Msg listTaskCheckingByUser(
            @RequestParam(value = "id") String userId,
            @RequestParam(value = "pn", defaultValue = "1") String pn,
            @RequestParam(value = "limit", defaultValue = "5") String limit
    ){
        IPage<TaskProcess> ipage = taskService.listTaskCheckingByUser(userId, pn, limit);
        return Msg.success().add("list", ipage);
    }

    /**
     * 【获取用户今日审核完成的任务列表】
     * @param userId 用户id
     * @param pn 页码
     * @param limit 容量
     * @return
     */
    @RequestMapping(value = "/user/complete/today", method = RequestMethod.GET)
    public Msg listTaskCompleteByUserToday(
            @RequestParam(value = "id") String userId,
            @RequestParam(value = "pn", defaultValue = "1") String pn,
            @RequestParam(value = "limit", defaultValue = "5") String limit
    ){
        IPage<TaskComplete> ipage = taskService.listTaskCompleteByUserToday(userId, pn, limit);
        return Msg.success().add("list", ipage);
    }

    /**
     * 【获取用户审核完成的全部任务列表】
     * @param userId 用户id
     * @param pn 页码
     * @param limit 容量
     * @return
     */
    @RequestMapping(value = "/user/complete/all", method = RequestMethod.GET)
    public Msg listTaskCompleteByUserAll(
            @RequestParam(value = "id") String userId,
            @RequestParam(value = "pn", defaultValue = "1") String pn,
            @RequestParam(value = "limit", defaultValue = "5") String limit
    ){
        IPage<TaskComplete> ipage = taskService.listTaskCompleteByUserAll(userId, pn, limit);
        return Msg.success().add("list", ipage);
    }

    /**
     * 任务延期
     * @param taskId 任务id
     * @param time 延期时长
     * @return
     */
    @RequestMapping(value = "/delay", method = RequestMethod.POST)
    public Msg delayTask(
            @RequestParam(value = "id") String taskId,
            @RequestParam(value = "delay")String time,
            @RequestParam(value = "description")String description
    ){
        boolean suc = taskService.delayTask(taskId, time, description);
        return Msg.success().add("suc", suc);
    }

}

