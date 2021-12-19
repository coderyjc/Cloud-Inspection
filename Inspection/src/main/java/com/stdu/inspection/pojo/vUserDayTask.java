package com.stdu.inspection.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class vUserDayTask {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 查询日期
     */
    private Date date;

    /**
     *  status = 2 已接受任务
     */
    private Integer countReceive;

    /**
     *  status = 3 验收中的任务
     */
    private Integer countCheck;

    /**
     *  status = 4 已完成的任务
     */
    private Integer countComplete;

    /**
     *  超时任务 暂定
     */
    private Integer countTimeout;





}
