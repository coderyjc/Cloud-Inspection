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


    IPage<DamageDamageType> listDamageToday(String time, String pn, String limit) ;
}
