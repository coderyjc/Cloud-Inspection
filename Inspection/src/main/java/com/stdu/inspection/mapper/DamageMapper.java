package com.stdu.inspection.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stdu.inspection.pojo.Damage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

    IPage<Damage> listDamageToday(IPage<Damage> iPage, QueryWrapper<Damage> wrapper);
}
