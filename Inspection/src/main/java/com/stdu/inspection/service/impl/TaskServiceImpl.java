package com.stdu.inspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stdu.inspection.pojo.Task;
import com.stdu.inspection.mapper.TaskMapper;
import com.stdu.inspection.pojo.TaskProcess;
import com.stdu.inspection.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stdu.inspection.utils.ConstUtil;
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

        task.insert();
    }
}
