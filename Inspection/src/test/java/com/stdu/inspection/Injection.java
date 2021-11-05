/**
 * @Author: Yan Jingcun
 * @Date: 2021/10/30
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection;

import com.stdu.inspection.pojo.Damage;
import com.stdu.inspection.pojo.User;
import com.stdu.inspection.utils.MD5Util;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Random;


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
    public void injectDamage(){

        Damage damage = new Damage();
        Random r = new Random();

        long basedate = 1635585409000L;
        for(int i = 0; i < 1000; i++){
            damage.setPostUser(r.nextInt(199) + 1);
            damage.setDamageType(r.nextInt(3));
            damage.setRailway(0);
            damage.setDamageLevel(1);
            damage.setLocation("106.14568785951,65.2565478256");
            damage.setStatus(r.nextInt(2) + 1);
            long date = basedate - r.nextInt(1419200000);
            damage.setPostDate(new Date(date));
            damage.insert();
            System.out.print(i + " ");
        }


    }
    @Test
    public void timeTest(){
        Date date = new Date(1635585409000L);
        System.out.println(date);
    }



}

