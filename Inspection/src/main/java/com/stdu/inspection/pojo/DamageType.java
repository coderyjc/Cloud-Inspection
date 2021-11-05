package com.stdu.inspection.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 损伤类型表
 */
@TableName("damage_type")
@EqualsAndHashCode()
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DamageType {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 损伤类型
     */
    private String typeName;

    /**
     * 损伤描述
     */
    private  String typeDescription;

}
