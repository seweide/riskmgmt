package com.haier.hairy.rmp.dto.hry;

import lombok.Builder;
import lombok.Data;

/**
 * 海融易网关响应
 * @Author: Xie Hao
 * @Create: 2019-07-24 18:45
 **/
@Data
@Builder
public class HryResp {
    /**
     * 网关响应码
      */
    private String code;

    /**
     * 网关响应消息
     */
    private String msg;

    /**
     * 业务响应
     */
    private String bizJson;
}
