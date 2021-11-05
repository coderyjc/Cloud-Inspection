package com.stdu.inspection.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.Damage;
import com.stdu.inspection.pojo.TaskProcess;
import com.stdu.inspection.service.DamageService;
import com.stdu.inspection.service.TaskService;
import com.stdu.inspection.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/damage")
public class DamageController {

    @Autowired
    DamageService damageService;

    /**对应   实时监测-实时数据右2 今日损伤列表
     *
     * @param  time 前端传过来的字符串类型的时间
     * @return 返回当日的损伤列表
     */
    @RequestMapping(value = "/damageTodayList", method = RequestMethod.GET)
    public Msg listDamageToday(
            @RequestParam(value = "time") String time,
            @RequestParam(value = "pn") String pn,
            @RequestParam(value = "limit") String limit
    )
    {
        IPage<Damage> damageTodayList = damageService.listDamageToday(time,pn, limit);
        return Msg.success().add("damageTodayList",damageTodayList);
    }

}

