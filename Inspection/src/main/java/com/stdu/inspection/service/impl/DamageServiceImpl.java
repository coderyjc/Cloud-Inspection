package com.stdu.inspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stdu.inspection.pojo.Damage;
import com.stdu.inspection.mapper.DamageMapper;
import com.stdu.inspection.pojo.DamageDamageType;
import com.stdu.inspection.service.DamageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
@Service
public class DamageServiceImpl extends ServiceImpl<DamageMapper, Damage> implements DamageService {


    @Autowired
    DamageMapper damageMapper;

    /**
     * 用来返回当日damage列表
     * 测试url：http://localhost:8080/damage/damageTodayList?time=2021-10-23&pn=2&limit=10
     * @param time
     * @param pn
     * @param limit
     * @return
     */

    @Override
    public IPage<DamageDamageType> listDamageToday(String time, String pn, String limit) {

//        QueryWrapper<DamageDamageType> wrapper = new QueryWrapper<DamageDamageType>();
        IPage<DamageDamageType> iPage = new Page<DamageDamageType>(Integer.parseInt(pn),Integer.parseInt(limit));
//        wrapper.apply("date_format(post_date,'%Y-%m-%d') = '" + time+ "' ");
        return baseMapper.listDamageToday(iPage,time);
    }
}