package com.stdu.inspection.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.Task;
import com.stdu.inspection.pojo.TaskProcess;
import com.stdu.inspection.service.DamageService;
import com.stdu.inspection.service.TaskService;
import com.stdu.inspection.utils.ConstUtil;
import com.stdu.inspection.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 【接单，用户接收巡检任务，向任务表中插入一条数据】
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
     * 分页获取用用户已经接单的任务列表
     * @param userId 用户id
     * @param pn 页码
     * @param limit 容量
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Msg listTaskByUser(
            @RequestParam(value = "id") String userId,
            @RequestParam(value = "pn", defaultValue = "1") String pn,
            @RequestParam(value = "limit", defaultValue = "5") String limit
    ){
        IPage<TaskProcess> ipage = taskService.listTaskByUser(userId, pn, limit);
        return Msg.success().add("list", ipage);
    }


}

