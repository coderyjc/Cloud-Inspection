package com.stdu.inspection.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.User;
import com.stdu.inspection.service.UserService;
import com.stdu.inspection.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    /**
     *
     * @param pn
     * @param limit
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg listAll(
            @RequestParam(value = "pn")String pn,
            @RequestParam(value = "limit")String limit
    ){
        IPage<User> iPage = userService.listAll(pn, limit);
        return Msg.success().add("pageInfo", iPage);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Msg login(
        @RequestParam(value = "username") String userName,
        @RequestParam(value = "pwd") String password
    ){
        boolean suc = userService.login(userName, password);
        return Msg.success().add("suc", suc);
    }




}

