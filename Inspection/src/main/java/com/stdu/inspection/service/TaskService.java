package com.stdu.inspection.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stdu.inspection.pojo.TaskProcess;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
public interface TaskService extends IService<Task> {

    TaskProcess getTaskProcessById(String taskId);

    IPage<TaskProcess> listTaskByProcess(String status, String pn, String limit);
}
