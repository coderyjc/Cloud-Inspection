package com.stdu.inspection.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.Damage;
import com.stdu.inspection.pojo.DamageDamageType;
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
     * @return 返回日的损伤列表
     */

    @RequestMapping(value = "/listDamageToday", method = RequestMethod.GET)
    public Msg listDamageToday(
            @RequestParam(value = "time") String time,
            @RequestParam(value = "pn") String pn,
            @RequestParam(value = "limit") String limit
    )
    {
        IPage<DamageDamageType> damageTodayList = damageService.listDamageToday(time, pn, limit);
        return Msg.success().add("damageTodayList",damageTodayList);
    }

    /**
     * 当日发现的 损伤和当日已经维修好的损伤数量
     * @param time
     * @return 日损伤修复数量
     */
    @RequestMapping(value = "getDamageCompleteCount", method = RequestMethod.GET)
    public Msg getDamageCompleteCount(
            @RequestParam(value = "time")String time
    )
    {
        Integer damageCompleteCount = damageService.getDamageCompleteCount(time);
        return Msg.success().add("damageCompleteCount",damageCompleteCount);
    }

}

