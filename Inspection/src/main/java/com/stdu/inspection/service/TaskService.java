package com.stdu.inspection.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stdu.inspection.pojo.Task;
import com.stdu.inspection.pojo.TaskProcess;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
public interface TaskService extends IService<Task> {

    /**
     * 通过任务id获取任务对象
     *
     * @param taskId 任务Id
     * @return
     */
    TaskProcess getTaskProcessById(String taskId);

    /**
     * 获取处于某个状态的任务的列表
     *
     * @param status 状态
     * @param pn     页码
     * @param limit  容量
     * @return
     */
    IPage<TaskProcess> listTaskByProcess(String status, String pn, String limit);

    /**
     * 2021年11月7日23:32:31
     * 根据日期查询某天任务总数
     *
     * @param time
     * @return
     */
    Integer getTaskCountByTime(String time);

    /**
     * 接单 - 向表中插入一条数据
     * @param damage 损伤id
     * @param user 用户id
     */
    void insert(int damage, int user);
}
