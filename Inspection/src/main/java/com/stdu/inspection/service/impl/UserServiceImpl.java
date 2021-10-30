package com.stdu.inspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stdu.inspection.pojo.User;
import com.stdu.inspection.mapper.UserMapper;
import com.stdu.inspection.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stdu.inspection.utils.MD5Util;
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

    @Override
    public IPage<User> listAll(String pn, String limit) {
        IPage<User> iPage = new Page<>(Integer.parseInt(pn), Integer.parseInt(limit));
        return baseMapper.selectPage(iPage, null);
    }

    @Override
    public boolean login(String userName, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName);
        wrapper.eq("user_password", MD5Util.getMD5(password));
        User user = baseMapper.selectOne(wrapper);
        return user != null;
    }
}
