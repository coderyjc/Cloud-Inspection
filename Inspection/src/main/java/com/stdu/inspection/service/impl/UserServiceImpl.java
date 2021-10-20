package com.stdu.inspection.service.impl;

import com.stdu.inspection.pojo.User;
import com.stdu.inspection.mapper.UserMapper;
import com.stdu.inspection.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
