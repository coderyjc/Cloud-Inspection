package com.stdu.inspection.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stdu.inspection.pojo.TaskProcess;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
public interface TaskMapper extends BaseMapper<Task> {

    TaskProcess getTaskProcessById(String taskId);

    IPage<TaskProcess> listTaskByProcess(IPage<TaskProcess> iPage, Wrapper ew);
}
