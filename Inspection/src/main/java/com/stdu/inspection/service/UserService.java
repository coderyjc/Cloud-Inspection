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
     *
     * @param pn
     * @param limit
     * @return
     */
    IPage<User> listAll(String pn, String limit);

    boolean login(String userName, String password);

    /**
     * 获取用户【今日】的任务统计信息
     * @param id
     * @param date
     * @return  vUserDayTask
     */
    vUserDayTask userDayTaskStatus(String id, String date);
}
