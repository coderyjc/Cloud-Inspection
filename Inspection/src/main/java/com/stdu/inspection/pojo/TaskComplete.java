/**
 * @Author: Yan Jingcun
 * @Date: 2022/1/13
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskComplete {

    private Integer id;

    private Integer damageId;

    private Integer receiver;

    private Date receiveDate;

    private Date deadline;

    private Date submitDate;

    private Date completeDate;

    private String description;

    @TableField(exist = false)
    private String damageDescription;

    @TableField(exist = false)
    private Date postDate;

    @TableField(exist = false)
    private String damageType;

    @TableField(exist = false)
    private String location;

    @TableField(exist = false)
    private Integer source;

}