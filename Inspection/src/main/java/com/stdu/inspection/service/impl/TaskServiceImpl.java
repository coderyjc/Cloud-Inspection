package com.stdu.inspection.service.impl;

import com.alibaba.druid.sql.ast.statement.SQLAlterTableDisableKeys;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stdu.inspection.pojo.Task;
import com.stdu.inspection.mapper.TaskMapper;
import com.stdu.inspection.pojo.TaskComplete;
import com.stdu.inspection.pojo.TaskProcess;
import com.stdu.inspection.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stdu.inspection.utils.ConstUtil;
import com.stdu.inspection.utils.TimeUtils;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public TaskProcess getTaskProcessById(String taskId) {
        return baseMapper.getTaskProcessById(taskId);
    }

    @Override
    public IPage<TaskProcess> listTaskByProcess(String status, String pn, String limit) {
        IPage<TaskProcess> iPage =  new Page<>(Integer.parseInt(pn),Integer.parseInt(limit));
        QueryWrapper<TaskProcess> wrapper = new QueryWrapper<>();
        wrapper.eq("status", status);
        return baseMapper.listTaskByProcess(iPage, wrapper);
    }

    @Override
    public Integer getTaskCountByTime(String time) {
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        wrapper.like("receive_date",time);
        Integer count = baseMapper.selectCount(wrapper);
        return count;
    }

    @Override
    public void insert(int damage, int user) {
        Task task = new Task();

        Date now = new Date();
        task.setTaskId(null)
            .setDamageId(damage)
            .setReceiver(user)
            .setReceiveDate(now)
            .setStatus(ConstUtil.TASK_ACCEPT)
            .setDeadline(new Date(now.getTime() + 86400000L));

        // 截止日期为1天

        task.insert();
    }

    @Override
    public boolean insertCompletePicture(String id, String filename) {
        return baseMapper.insertCompletePicture(id, filename);
    }

    @Override
    public boolean submitTask(String taskId, String description) {
        // 提交任务，直接调用存储过程
        return baseMapper.submitTask(taskId, description);
    }

    @Override
    public Task deleteTaskById(String id) {
        Task task = new Task();
        task.setTaskId(Integer.parseInt(id));
        task = task.selectById();
        task.deleteById();
        return task;
    }

    @Override
    public IPage<TaskProcess> listTaskCheckingByUser(String userId, String pn, String limit) {
        IPage<TaskProcess> iPage =  new Page<>(Integer.parseInt(pn),Integer.parseInt(limit));
        QueryWrapper<TaskProcess> wrapper = new QueryWrapper<>();
        wrapper.eq("receiver", Integer.parseInt(userId));
        wrapper.eq("status", ConstUtil.TASK_COMMIT);
        return baseMapper.listTaskByProcess(iPage, wrapper);
    }

    @Override
    public boolean updateTaskStatus(String taskId, int status) {
        Task task = new Task();
        if(status == ConstUtil.TASK_COMMIT) {
            task.setSubmitDate(new Date());
        }
        return task.setTaskId(Integer.parseInt(taskId)).setStatus(status).updateById();
    }

    @Override
    public IPage<TaskComplete> listTaskCompleteByUserToday(String userId, String pn, String limit) {
        IPage<TaskComplete> iPage = new Page<>(Integer.parseInt(pn), Integer.parseInt(limit));
        QueryWrapper<TaskComplete> wrapper = new QueryWrapper<>();
        wrapper.eq("receiver", userId);
        String time =
                TimeUtils.castDateTypeToDateString(TimeUtils.localToUTC(TimeUtils.getCurrentTimeString()));
        wrapper.like("submit_date",time.substring(0, 10));
        return baseMapper.listTaskComplete(iPage, wrapper);
    }

    @Override
    public IPage<TaskComplete> listTaskCompleteByUserAll(String userId, String pn, String limit) {
        IPage<TaskComplete> iPage = new Page<>(Integer.parseInt(pn), Integer.parseInt(limit));
        QueryWrapper<TaskComplete> wrapper = new QueryWrapper<>();
        wrapper.eq("receiver", userId);
        return baseMapper.listTaskComplete(iPage, wrapper);
    }

    @Override
    public boolean delayTask(String taskId, String time, String description) {
        Task task = new Task();
        task.setTaskId(Integer.parseInt(taskId));
        task = task.selectById();
        int ori = Integer.parseInt(time);
        long after = task.getDeadline().getTime() + ori * 3600000L;
        task.setDeadline(new Date(after));
        baseMapper.delayTask(taskId, time, description);
        return task.updateById();
    }

    @Override
    public IPage<TaskProcess> listTaskAcquiredByUser(String userId, String pn, String limit) {
        IPage<TaskProcess> iPage =  new Page<>(Integer.parseInt(pn),Integer.parseInt(limit));
        QueryWrapper<TaskProcess> wrapper = new QueryWrapper<>();
        wrapper.eq("receiver", Integer.parseInt(userId));
        return baseMapper.listTaskByProcess(iPage, wrapper);
    }
}
