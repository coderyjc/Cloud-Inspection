/**
 * @Author: Yan Jingcun
 * @Date: 2021/10/30
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskProcess {

    private Integer taskId;

    private Integer damageId;

    private Date receiveDate;

    private Date deadline;

    private Date submitDate;

    private Integer status;

    private Integer receiver;

    private Date postDate;

    private String location;

    private String description;

    private Integer type;

    private Integer source;

}
