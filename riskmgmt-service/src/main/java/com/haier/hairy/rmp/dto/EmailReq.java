package com.haier.hairy.rmp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author: Xie Hao
 * @Create: 2019-08-06 10:11
 **/
@Data
@Builder
public class EmailReq {
    /**
     * 邮件发送请求系统编码
     */
    private final String appId = "riskmgmt";

    /**
     * 发送序列号
     */
    private String requestNo;

    /**
     * 邮件发送目的地址列表，通过英文逗号相隔
     */
    private String emailAddrs;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮件附件文件全路径列表
     */
    private List<String> attachmentFilePathList;
}
