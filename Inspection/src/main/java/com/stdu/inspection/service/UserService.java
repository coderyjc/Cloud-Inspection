package com.stdu.inspection.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stdu.inspection.pojo.vUserDayTask;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
public interface UserService extends IService<User> {

    /**
     * 列出所有用户列表
     * @param pn
     * @param limit
     * @return
     */
    IPage<User> listAll(String pn, String limit);


    /**
     * 登录
     * @param userId 用户id
     * @param password 密码
     * @return
     */
    User login(String userId, String password);

    /**
     * 获取用户【今日】的任务统计信息
     * @param id
     * @param date
     * @return  vUserDayTask
     */
    vUserDayTask userDayTaskStatus(String id, String date);
}
