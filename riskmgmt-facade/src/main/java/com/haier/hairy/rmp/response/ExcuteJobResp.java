package com.haier.hairy.rmp.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiehao
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("任务执行响应")
public class ExcuteJobResp extends BaseResp {
    private static final long serialVersionUID = -1L;
}