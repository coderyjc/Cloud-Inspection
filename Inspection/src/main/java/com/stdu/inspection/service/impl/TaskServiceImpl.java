package com.stdu.inspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stdu.inspection.pojo.Task;
import com.stdu.inspection.mapper.TaskMapper;
import com.stdu.inspection.pojo.TaskProcess;
import com.stdu.inspection.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
