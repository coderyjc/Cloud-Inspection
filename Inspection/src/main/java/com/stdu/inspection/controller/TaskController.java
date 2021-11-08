package com.stdu.inspection.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.TaskProcess;
import com.stdu.inspection.service.TaskService;
import com.stdu.inspection.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

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
    )
    {
        Integer taskCountToday = taskService.getTaskCountByTime(time);
        return Msg.success().add("taskCountToday",taskCountToday);
    }

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public Msg getTaskProcessById(
            @RequestParam(value = "taskid") String taskId
    ){
        TaskProcess taskProcess = taskService.getTaskProcessById(taskId);
        return Msg.success().add("taskProcess", taskProcess);
    }

    @RequestMapping(value = "/process/{status}", method = RequestMethod.GET)
    public Msg listTaskByProcess(
            @PathVariable(value = "status")String status,
            @RequestParam(value = "pn")String pn,
            @RequestParam(value = "limit", defaultValue = "10") String limit
    ){
        IPage<TaskProcess> iPage = taskService.listTaskByProcess(status, pn, limit);
        return Msg.success().add("pageInfo", iPage);
    }





}

