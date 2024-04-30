package com.haier.hairy.rmp.service;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.haier.hairy.mts.facade.EmailFacade;
import com.haier.hairy.mts.request.SendEmailRequest;
import com.haier.hairy.mts.response.SendEmailResponse;
import com.haier.hairy.rmp.dto.EmailReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Xie Hao
 * @Create: 2019-07-30 10:27
 **/
@Slf4j
@Component
public class EmailService {

    @Resource
    private EmailFacade emailFacade;

    /**
     * 邮件发送(附件文件名暂时只支持英文)
     *
     * @param req 邮件地址，主题，内容，附件等请求对象封装
     * @return
     */
    public SendEmailResponse send(EmailReq req) {
        SendEmailResponse emailResp = new SendEmailResponse();
        String emailAddrs = req.getEmailAddrs();
        if (StringUtils.isBlank(emailAddrs)) {
            log.warn("未配置邮件发送地址列表");
            emailResp.setResponseCode(1);
            emailResp.setResponseMessage("未配置邮件发送地址列表");
            return emailResp;

        }
        List<String> emailArr = new ArrayList<>();
        for (String emailAddr : emailAddrs.split(",")) {
            if (Validator.isEmail(emailAddr)) {
                emailArr.add(emailAddr);
            } else {
                log.warn("邮件发送地址错误, email={}", emailAddr);
            }
        }
        if (emailArr.size() == 0) {
            log.warn("邮件发送地址列表配置错误，请核对格式");
            emailResp.setResponseCode(1);
            emailResp.setResponseMessage("邮件发送地址列表配置错误");
            return emailResp;
        }

        SendEmailRequest emailReq = new SendEmailRequest();
        BeanUtils.copyProperties(req, emailReq);
        emailReq.setReceivers(JSON.toJSONString(emailArr));
        List<String> filePathList = req.getAttachmentFilePathList();
        if (filePathList != null && filePathList.size() != 0) {
            List<File> files = filePathList.stream().map(path -> new File(path))
                    .collect(Collectors.toList());
            emailReq.setAttachments(filesToJsonString(files));
        }
        log.info("邮件服务请求：{}", JSON.toJSONString(emailReq));
        emailResp = emailFacade.sendEmail(emailReq);
        log.info("邮件服务响应：{}", JSON.toJSONString(emailResp));
        return emailResp;
    }

    /**
     * 解析附件
     *
     * @param files
     * @return
     */
    private String filesToJsonString(List<File> files) {
        String json = null;
        if (CollectionUtils.isNotEmpty(files)) {
            List<Map<String, String>> attachments = Lists.newArrayList();
            for (File file : files) {
                try (InputStream input = new FileInputStream(file);
                     ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                    StringBuilder buffer = new StringBuilder();
                    byte[] temp = new byte[1024];
                    for (int len = input.read(temp); len != -1; len = input.read(temp)) {
                        out.write(temp, 0, len);
                        buffer.append(Base64Encoder.encode(out.toByteArray()));
                        out.reset();
                    }
                    Map<String, String> attachment = Maps.newHashMap();
                    attachment.put("name", file.getName());
                    attachment.put("content", buffer.toString());
                    attachments.add(attachment);
                } catch (Exception e) {
                    log.error("发送邮件附件, 文件转Base64异常", e);
                }
            }
            json = JSON.toJSONString(attachments);
        }
        return json;
    }
}
