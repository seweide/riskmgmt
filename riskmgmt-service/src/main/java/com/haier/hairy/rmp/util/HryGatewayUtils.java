package com.haier.hairy.rmp.util;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.haier.hairy.rmp.config.HryGatewayConfig;
import com.haier.hairy.rmp.dto.hry.HryResp;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 海融易网关工具类，加解密，签名
 *
 * @Author: Xie Hao
 * @Create: 2019-07-23 15:40
 **/
@Slf4j
public class HryGatewayUtils {
    /**
     * 签名字段名称
     */
    public static final String PARAM_SIGN = "sign";
    /**
     * 默认签名算法
     */
    public static final String DEFAULT_SIGN_ALGORITHM = "HmacSHA1";
    /**
     * 默认字符串编码
     */
    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    /**
     * 请求参数拼接分隔符
     */
    private final static String REQ_PARAM_DELIMITER = "&";

    private final static String HRY_RESP_CODE_SUCCESS = "10000";

    public static String encryptByPublicKey(Map<String, String> params, Charset charset, String publicKey){
        String temp = getUnifiedString(params);
        byte[] encryptStr = RSAUtils.encryptByPublicKey(temp, charset, publicKey);
        return Base64.encode(encryptStr);
    }

    /**
     * 通过公钥加密，返回Base64编码字符串
     * @param params 原文
     * @param publicKey RSA公钥
     * @return
     */
    public static String encryptByPublicKey(Map<String, String> params, String publicKey){
        return encryptByPublicKey(params, DEFAULT_CHARSET, publicKey);
    }

    /**
     * 生成参数签名
     *
     * @param params    接口请求参数
     * @param secretKey 海融易颁发的密钥
     * @return
     */
    public static String genSign(Map<String, String> params, String secretKey) throws Exception {
        SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes(), DEFAULT_SIGN_ALGORITHM);
        Mac mac = Mac.getInstance(DEFAULT_SIGN_ALGORITHM);
        mac.init(secret);
        byte[] hmac = mac.doFinal(getUnifiedString(params).getBytes(DEFAULT_CHARSET));
        return Base64.encode(hmac);
    }

    /**
     * 验证参数签名
     *
     * @param params    参数对
     * @param secretKey 海融易颁发的密钥
     * @return
     * @throws Exception
     */
    public static boolean verify(Map<String, String> params, String secretKey) throws Exception {
        String receiveSign = params.get(PARAM_SIGN);
        return receiveSign.equals(genSign(params, secretKey));
    }

    /**
     * 生成URL请求参数字符串，字符串按请求参数名的ASCII码升序，
     * 比如: a=1&c=3&b=2 转换为 a=1&b=2&c=3
     *
     * @param params
     * @return
     */
    public static String getUnifiedString(Map<String, String> params) {
        return new TreeMap<>(params).entrySet().stream()
                .filter(e -> !PARAM_SIGN.equals(e.getKey()))
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining(REQ_PARAM_DELIMITER));
    }

    public static HryResp call(Map<String, String> params) throws Exception {
        //log.info("HRY-GATEWAY-URL:{}", HryGatewayConfig.CDP_WANLIAN_FACADE);
        log.info("HRY-GATEWAY-REQ:{}", params.toString());
        params.put("partnerId", HryGatewayConfig.PARTNER_ID);
        Map<String, String> map = new HashMap<>(2);
        map.put("params", encryptByPublicKey(params, HryGatewayConfig.PUBLIC_KEY));
        map.put("sign", genSign(params, HryGatewayConfig.SECRET));
        String respBodyStr = HttpUtils.doPost(HryGatewayConfig.CDP_WANLIAN_FACADE, map,
                HryGatewayConfig.CONNECT_TIMEOUT, HryGatewayConfig.READ_TIMEOUT);
        log.info("HRY-GATEWAY-RESP:{}", respBodyStr);
        JSONObject respBodyJson = JSON.parseObject(respBodyStr);
        String code = respBodyJson.getString("code");
        String msg = respBodyJson.getString("msg");
        if(!HRY_RESP_CODE_SUCCESS.equals(code)){
            log.warn("HRY-GATEWAY返回错误，错误={}， {}", code, msg);
            return HryResp.builder().code("9999").msg(msg).build();
        }else {
            String bizJson = respBodyJson.getJSONObject("result").toJSONString();
            return HryResp.builder().code("0").bizJson(bizJson).build();
        }
    }
}
