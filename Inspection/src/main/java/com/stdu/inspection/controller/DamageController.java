package com.stdu.inspection.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.DamageDamageType;
import com.stdu.inspection.service.DamageImageService;
import com.stdu.inspection.service.DamageService;
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
@RequestMapping("/damage")
public class DamageController {

    @Autowired
    DamageService damageService;

    @Autowired
    DamageImageService imageService;

    /**
     * 实时监测-实时数据右2 今日损伤列表
     *
     * @param time 前端传过来的字符串类型的时间
     * @return 返回日的损伤列表
     */
    @RequestMapping(value = "/listDamageToday", method = RequestMethod.GET)
    public Msg listDamageToday(
            @RequestParam(value = "time") String time,
            @RequestParam(value = "pn", defaultValue = "1") String pn,
            @RequestParam(value = "limit", defaultValue = "10") String limit
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
    @RequestMapping(value = "/getDamageCompleteCount", method = RequestMethod.GET)
    public Msg getDamageCompleteCount(
            @RequestParam(value = "time")String time
    )
    {
        Integer damageCompleteCount = damageService.getDamageCompleteCount(time);
        return Msg.success().add("damageCompleteCount",damageCompleteCount);
    }

    /**
     * 【获取当前已有损伤列表】
     * 2021年11月11日 22:52:52
     * @param pn 第几页
     * @param limit 容量
     * @return
     */
    @RequestMapping(value = "/listDamageUpToNow")
    public Msg listDamageUptoNow(
            @RequestParam(value = "pn")String pn,
            @RequestParam(value = "limit")String limit
    )
    {
        IPage<DamageDamageType> listDamageUptoNow = damageService.listDamageUptoNow(pn, limit);
        return Msg.success().add("list",listDamageUptoNow);
    }

    /**
     * 【获取单个损伤的详情信息】
     * 2021年11月14日23:11:02
     * @param damageId
     * @return DamageDamageType视图
     */
    @RequestMapping(value = "/getDamageById")
    public Msg getDamageById(
            @RequestParam(value = "damageId")String damageId
    )
    {
        DamageDamageType getDamageById = damageService.getDamageById(damageId);
        return Msg.success().add("info",getDamageById);
    }

    /**
     * 【上传损伤】
     * 前提：上传损伤的时候带有图片，每个损伤的每个图片是记录在【damage_image】表中的。
     *  提交损伤的人员在报告损伤的时候上传了图片，但是这时候图片的【damage_id】字段没有指定
     *  设置 damage_image 表中的 damage_id 应该在插入damage之后进行
     * 我实现的逻辑是这样的：
     *  在damage_image中创建字段 update_id
     *  表在工人发现损伤，在报告损伤界面插入图片的时候，向damage_image插入数据时将【暂时】update_id 置为user_id
     *  在获得damage_id之后，再将updateid=user_id 的记录的 damage_id 改为刚刚获得的damageid，并且将update_id=0
     * @param type 损伤类型
     * @param location 损伤所在的经纬度
     * @param postId 上传损伤者的id
     * @param description 对损伤的描述
     * @param source 上传损伤的是人还是设备
     * @return 成功和失败的消息
     */
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Msg insertDamage(
            String location,
            Integer type,
            Integer postId,
            Integer source,
            String description
    ){

        // 返回损伤类型号
        int damageId =
                damageService.insert(location, type, postId, source, description);

        // 格式化损伤类型号
        int suc = imageService.modifyDamageId(postId, damageId);

        return Msg.success().add("suc", suc);
    }

}

