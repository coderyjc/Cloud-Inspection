package com.stdu.inspection.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jancoyan
 * @since 2021-12-30
 */
@TableName("damage_image")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DamageImage extends Model<DamageImage> {

    private static final long serialVersionUID = 1L;

    /**
     * 损伤id，就是damage表中的主键
     */
    private Integer damageId;

    /**
     * 图片的文件名
     */
    private String filename;

    /**
     * 插入图片时候的缓冲
     */
    private Integer updateId;

    /**
     * 图片上传时间
     */
    private Date insertTime;

}
