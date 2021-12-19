package com.stdu.inspection.mapper;

import com.stdu.inspection.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jancoyan
 * @since 2021-10-20
 */
public interface UserMapper extends BaseMapper<User> {

    Integer selectCountUserDayTask(@Param("id") Integer id, @Param("date") String date, @Param("status") int status);
}
