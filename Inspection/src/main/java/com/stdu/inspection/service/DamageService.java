package com.stdu.inspection.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.Damage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stdu.inspection.pojo.DamageDamageType;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
public interface DamageService extends IService<Damage> {


    /**
     * 根据时间获取日损伤列表
     * @param time
     * @param pn
     * @param limit
     * @return list集合 日损伤列表
     */
    IPage<DamageDamageType> listDamageToday(String time, String pn, String limit) ;

    /**
     * 根据时间获得日损伤完成数量
     * @param time
     * @return 整数 日损伤修复数量
     */
    Integer getDamageCompleteCount(String time);

    /**
     * 2021年11月11日22:54:21
     * 获取当前已有损伤列表
     * @param pn
     * @param limit
     * @return
     */
    IPage<DamageDamageType> listDamageUptoNow(String pn, String limit);

    /**获取单个损伤的详情信息
     * 2021年11月14日23:11:36
     * @param damageId
     * @return
     */
    DamageDamageType getDamageById(String damageId);

    /**
     * 插入损伤数据
     * @param location 位置
     * @param type 类型
     * @param postId 上传id
     * @param source 上传的是人还是车
     * @param description 介绍说明
     * @return 损伤id
     */
    int insert(String location, Integer type, Integer postId, Integer source, String description);


    /**
     * 改变damage的状态，也就是改变status字段的状态
     * 1表示未修复
     * 2表示正在修复
     * 3表示已经修复
     * @param status 要改变为的状态
     */
    void updateDamageStatus(int id, int status);

}
