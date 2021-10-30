package com.stdu.inspection.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
