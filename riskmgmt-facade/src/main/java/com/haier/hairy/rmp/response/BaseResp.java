package com.haier.hairy.rmp.response;

import com.haier.hairy.rmp.enums.StatusCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Xie Hao
 * @Create: 2019-08-06 11:20
 **/
@Data
public class BaseResp implements Serializable {
    @ApiModelProperty("响应码，0表示成功")
    private int code = 0;

    @ApiModelProperty("响应信息")
    private String msg;

    public void setRet(StatusCode statusCode){
        setCode(statusCode.getStatus());
        setMsg(statusCode.getMessage());
    }

    public void setRet(StatusCode statusCode, String msg){
        setCode(statusCode.getStatus());
        setMsg(msg);
    }
}
