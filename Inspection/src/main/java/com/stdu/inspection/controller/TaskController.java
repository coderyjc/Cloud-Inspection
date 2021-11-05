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

