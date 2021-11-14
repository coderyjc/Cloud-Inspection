package com.stdu.inspection.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.stdu.inspection.pojo.Damage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stdu.inspection.pojo.DamageDamageType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
@Mapper
public interface DamageMapper extends BaseMapper<Damage> {

    IPage<DamageDamageType> listDamageToday(IPage<DamageDamageType> iPage,@Param("time") String time);

    IPage<DamageDamageType> listDamageUptoNow(IPage<DamageDamageType> iPage,@Param("time") String time);
}
