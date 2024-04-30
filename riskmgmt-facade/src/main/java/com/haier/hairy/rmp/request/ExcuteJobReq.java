package com.haier.hairy.rmp.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Xie Hao
 * @Create: 2019-08-06 11:05
 **/
@Data
@ApiModel("任务执行请求")
public class ExcuteJobReq implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty("任务日期 非必填")
    private Date reportDate;

    @ApiModelProperty("任务类型 1=万链指数预警, 2=DTS数据预警")
    @NotNull
    private Integer reportType;
}
