package com.stdu.inspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stdu.inspection.pojo.Damage;
import com.stdu.inspection.mapper.DamageMapper;
import com.stdu.inspection.pojo.DamageDamageType;
import com.stdu.inspection.service.DamageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stdu.inspection.utils.TimeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        IPage<DamageDamageType> iPage = new Page<DamageDamageType>(Integer.parseInt(pn),Integer.parseInt(limit));
        return baseMapper.listDamageToday(iPage,time);
    }

    /**
     *
     *
     * 通过时间yyyy-mm-dd来获取当日的损伤完成数量
     * @param time
     * @return
     */
    @Override
    public Integer getDamageCompleteCount(String time) {
        QueryWrapper<Damage> damageCompleteQueryWrapper = new QueryWrapper<Damage>();
        damageCompleteQueryWrapper.like("fixed_date",time);
        return baseMapper.selectCount(damageCompleteQueryWrapper);
    }

    /**
     * 获取当前已有损伤列表
     * @param pn
     * @param limit
     * @return
     */

    @Override
    public IPage<DamageDamageType> listDamageUptoNow(String pn, String limit) {

        IPage<DamageDamageType> iPage = new Page<DamageDamageType>(Integer.parseInt(pn),Integer.parseInt(limit));
        String time = TimeUtils.getCurrentTimeString(); // 获取当前系统时间，格式为 yyyy-MM-dd HH:mm:ss

//        QueryWrapper<DamageDamageType> wrapper = new QueryWrapper<DamageDamageType>();
//        wrapper.apply("UNIX_TIMESTAMP(post_date) < UNIX_TIMESTAMP('"+time+"')");
//        System.out.println("UNIX_TIMESTAMP(post_date) < UNIX_TIMESTAMP('"+tadayS+"')");

        return baseMapper.listDamageUptoNow(iPage,time);
    }
}
// select * from v_damage_damage_type where  UNIX_TIMESTAMP(post_date) < UNIX_TIMESTAMP('2021-11-13 00:14:32')