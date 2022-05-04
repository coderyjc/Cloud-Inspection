package com.stdu.inspection.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stdu.inspection.pojo.Task;
import com.stdu.inspection.pojo.TaskComplete;
import com.stdu.inspection.pojo.TaskProcess;

import java.util.List;

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
     * 分页获取用用户已经接收的任务列表
     * @param userId 用户id
     * @param pn 页码
     * @param limit 容量
     * @return
     */
     IPage<TaskProcess> listTaskAcquiredByUser(String userId, String pn, String limit);

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

    /**
     * 向 task_complete_image 表中插入记录
     * @param id 任务id
     * @param filename 图片名称
     * @return
     */
    boolean insertCompletePicture(String id, String filename);

    /**
     * 【提交任务】
     * 审核通过才是真正的通过
     * @param taskId 任务id
     * @param description 说明
     * @return
     */
    boolean submitTask(String taskId, String description);

    /**
     * 由id 删除task中的记录，如果删除成功，则返回刚刚删除的数据
     * @param id 任务id
     * @return
     */
    Task deleteTaskById(String id);

    /**
     * 【获取用户正在审核的任务列表】
     * @param userId 用户id
     * @param pn 页码
     * @param limit 容量
     * @return
     */
    IPage<TaskProcess> listTaskCheckingByUser(String userId, String pn, String limit);

    /**
     * 修改任务状态
     * @param status 新状态
     * @return
     */
    boolean updateTaskStatus(String taskId, int status);

    /**
     * 【获取用户今日审核完成的任务列表】
     * @param userId 用户id
     * @param pn 页码
     * @param limit 容量
     * @return
     */
    IPage<TaskComplete> listTaskCompleteByUserToday(String userId, String pn, String limit);


    /**
     * 【获取用户审核完成的全部任务列表】
     * @param userId 用户id
     * @param pn 页码
     * @param limit 容量
     * @return
     */
    IPage<TaskComplete> listTaskCompleteByUserAll(String userId, String pn, String limit);

    /**
     * 【任务延期】
     * @param taskId 任务id
     * @param time 时间
     * @param description 原因
     * @return
     */
    boolean delayTask(String taskId, String time, String description);

    /**
     * [获取所有需要审核的任务列表]
     * @param pn 页码
     * @param limit 页容量
     * @return
     */
    IPage<TaskProcess> listTaskCheckingAll(String pn, String limit);

    List<String> listTaskImage(String id);

    IPage<TaskProcess> listTaskAllByProcess(int pn, int limit);
}
