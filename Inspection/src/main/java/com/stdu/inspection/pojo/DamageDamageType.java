package com.stdu.inspection.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 是 damage 和 damage_type的连接视图pojo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DamageDamageType {
    private Integer id;

    /**
     * 上传任务的人，也就是巡检拍照的人
     */
    private Integer postUser;


    /**
     * 路段，暂时不用这个字段
     */
    private Integer railway;

    /**
     * 经纬度
     */
    private String location;

    /**
     * 上传的时候，对损伤的描述
     */
    private String description;

    /**
     * 上传日期
     */
    private Date postDate;

    /**
     * 任务状态，0表示未接收，1表示已接受
     */
    private Integer status;

    /**
     * 损伤类型
     */
    private String typeName;

    /**
     * 损伤描述
     */
    private  String typeDescription;

}
