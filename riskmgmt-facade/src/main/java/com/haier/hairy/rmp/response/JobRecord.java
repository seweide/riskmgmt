package com.haier.hairy.rmp.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Xie Hao
 * @Create: 2019-08-06 11:40
 **/
@Data
public class JobRecord implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("任务日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date reportDate;

    @ApiModelProperty("任务类型")
    private Integer reportType;

    @ApiModelProperty("任务开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("任务结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty("任务状态 I=初始化，P=处理中，S=成功，F=失败")
    private String taskStatus;

    @ApiModelProperty("通知时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date noticeTime;

    @ApiModelProperty("通知结果 S=成功，F=失败")
    private String noticeStatus;
}
