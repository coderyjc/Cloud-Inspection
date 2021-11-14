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
}
