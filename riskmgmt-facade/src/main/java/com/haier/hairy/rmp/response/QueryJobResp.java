package com.haier.hairy.rmp.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author xiehao
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("任务查询响应")
public class QueryJobResp extends BaseResp {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty("任务列表")
    private List<JobRecord> data;
}