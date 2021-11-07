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

    /**
     * 2021年11月7日23:32:31
     * 根据日期查询某天任务总数
     * @param time
     * @return
     */
    Integer getTaskCountByTime(String time);
}
