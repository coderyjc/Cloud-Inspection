package com.stdu.inspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stdu.inspection.pojo.User;
import com.stdu.inspection.mapper.UserMapper;
import com.stdu.inspection.pojo.vUserDayTask;
import com.stdu.inspection.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stdu.inspection.utils.MD5Util;
import com.stdu.inspection.utils.TimeUtils;
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
    public User login(String userId, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("user_password", MD5Util.getMD5(password));
        User user = baseMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public vUserDayTask userDayTaskStatus(String id, String date) {

        Integer ID = Integer.parseInt(id);


        //selectCountUserDayTask的参数对应 用户id，日期，任务状态
        Integer countReceive = baseMapper.selectCountUserDayTask(ID,date, 2);
        Integer countCheck =  baseMapper.selectCountUserDayTask(ID,date, 3);
        Integer countComplete =  baseMapper.selectCountUserDayTask(ID,date, 4);

        vUserDayTask vUserDayTask = new vUserDayTask();
        vUserDayTask.setId(ID);
        vUserDayTask.setCountReceive(countReceive);
        vUserDayTask.setCountCheck(countCheck);
        vUserDayTask.setCountComplete(countComplete);

        System.out.println(vUserDayTask);

        return vUserDayTask;
    }
}
