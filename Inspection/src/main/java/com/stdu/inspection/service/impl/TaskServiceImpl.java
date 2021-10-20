package com.stdu.inspection.service.impl;

import com.stdu.inspection.pojo.Task;
import com.stdu.inspection.mapper.TaskMapper;
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

}
