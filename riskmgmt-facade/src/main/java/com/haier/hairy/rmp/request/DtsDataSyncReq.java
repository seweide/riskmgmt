package com.haier.hairy.rmp.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiehao
 */
@Data
@ApiModel("DTS数据同步请求")
public class DtsDataSyncReq implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty("数据月份，格式yyyyMM")
    private String month;
}
