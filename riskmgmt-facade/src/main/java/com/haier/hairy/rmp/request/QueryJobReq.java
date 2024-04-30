package com.haier.hairy.rmp.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author xiehao
 */
@Data
@ApiModel("任务查询请求")
public class QueryJobReq implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty("任务日期 格式yyyy-MM-dd")
    private String reportDate;

    @ApiModelProperty("任务类型 1=万链指数预警, 2=DTS数据预警")
    @NotNull
    private Integer reportType;
}
