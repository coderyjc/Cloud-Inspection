package com.stdu.inspection.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-10-20
 */
@TableName("task")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Task extends Model<Task> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    /**
     * 接受的任务id

     */
    private Integer damageId;

    /**
     * 接受任务的人
     */
    private Integer receiver;

    /**
     * 接受任务的时间
     */
    private Date receiveDate;

    /**
     * 截止日期
     */
    private Date deadline;

    /**
     * 任务状态 1234

     */
    private Integer status;

}
