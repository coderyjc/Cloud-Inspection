/**
 * @Author: Yan Jingcun
 * @Date: 2021/10/30
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection;

import com.stdu.inspection.pojo.User;
import com.stdu.inspection.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;


public class Injection extends InspectionApplicationTests{

    @Test
    public void injectUser(){
        User user = new User();
        String pwd = MD5Util.getMD5("123");
        user.setUserRole(1);
        user.setUserPassword(pwd);

        for(int i = 0; i < 200; i++){
            user.setUserId(i);
            user.setUserName("user-" + i);
            user.insert();
            System.out.println(i);
        }
    }


    @Test
    public void deleteUser(){
        User user = new User();
        user.setUserId(199);
        user.deleteById();
    }


    @Test
    public void updateUser(){
        User user = new User();
        user.setUserId(1);
        user.setUserPassword("123");
        user.updateById();
    }

    

}

