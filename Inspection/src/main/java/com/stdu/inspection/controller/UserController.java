package com.stdu.inspection.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.User;
import com.stdu.inspection.pojo.vUserDayTask;
import com.stdu.inspection.service.UserService;
import com.stdu.inspection.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * 获取用户管理的所有用户
     * @param pn 页码
     * @param limit 容量
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg listAll(
            @RequestParam(value = "pn", defaultValue = "1")String pn,
            @RequestParam(value = "limit", defaultValue = "10")String limit
    ){
        IPage<User> iPage = userService.listAll(pn, limit);
        return Msg.success().add("pageInfo", iPage);
    }

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Msg login(
        @RequestParam(value = "username") String userName,
        @RequestParam(value = "pwd") String password
    ){
        boolean suc = userService.login(userName, password);
        return Msg.success().add("suc", suc);
    }


    /**
     * 获取用户【今日】任务统计信息
     *
     */
    @RequestMapping(value = "/userDayTaskStatus", method = RequestMethod.GET)
    public Msg userTaskList(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "date") String date
    )
    {
       vUserDayTask vUserDayTask = userService.userDayTaskStatus(id, date);
       return Msg.success().add("userDayTaskStatus", vUserDayTask);
    }



}

