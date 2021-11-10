/**
 * @Author: Yan Jingcun
 * @Date: 2021/10/30
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stdu.inspection.pojo.Damage;
import com.stdu.inspection.pojo.Task;
import com.stdu.inspection.pojo.User;
import com.stdu.inspection.utils.MD5Util;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Random;


public class Injection extends InspectionApplicationTests{

    /**
     * 随机注入用户
     */
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

    /**
     * 随机注入损伤数据
     */
    @Test
    public void injectDamage(){

        Damage damage = new Damage();
        Random r = new Random();

        long basedate = 1635585409000L;
        for(int i = 0; i < 1000; i++){
            damage.setPostUser(r.nextInt(199) + 1);
            damage.setDamageType(r.nextInt(3));
            damage.setRailway(0);
            damage.setLocation("106.14568785951,65.2565478256");
            damage.setStatus(r.nextInt(2) + 1);
            long date = basedate - r.nextInt(1419200000);
            damage.setPostDate(new Date(date));
            damage.insert();
            System.out.print(i + " ");
        }


    }

    /**
     * 把Damage表中的正在修复的数据注入到Task中
     */
    @Test
    public void injectTask(){
        QueryWrapper<Damage> wrapper = new QueryWrapper<>();
        // 2表示已接受
        wrapper.eq("status", "2");
        Damage damage = new Damage();
        List<Damage> damages = damage.selectList(wrapper);
        // 构建任务对象
        Random random = new Random();
        Task task = new Task();
        task.setTaskId(null);
        Date now = new Date();
        int i = 1;
        for (Damage item : damages) {
            task.setStatus(random.nextInt(3) + 2);
            task.setDamageId(item.getId());
            task.setReceiver(random.nextInt(195) + 1);
            task.setReceiveDate(item.getPostDate());
            task.setDeadline(now);
            task.insert();
            System.out.print(i++);
        }

    }

}

