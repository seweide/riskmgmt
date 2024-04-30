package com.haier.hairy.rmp.config;

import com.haier.hairy.common.apollo.ApolloConfigContext;

/**
 * 海融易网关配置
 * @Author: Xie Hao
 * @Create: 2019-07-24 17:14
 **/
public class HryGatewayConfig {
    /**
     * 网关主机地址
     */
    public static final String URL = ApolloConfigContext.getConfig("hry.gateway.url");

    /**
     * 网关访问万链URL地址
     */
    public static final String CDP_WANLIAN_FACADE = URL + ApolloConfigContext.getConfig("hry.gateway.cdp.wanlian.facade");
    /**
     * 网关公钥
     */
    public static final String PUBLIC_KEY = ApolloConfigContext.getConfig("hry.gateway.publicKey");

    /**
     * 网关访问凭证ID
     */
    public static final String PARTNER_ID = ApolloConfigContext.getConfig("hry.gateway.partnerId");
    /**
     * 秘钥
     */
    public static final String SECRET = ApolloConfigContext.getConfig("hry.gateway.secret");

    /**
     * 网关连接超时，单位秒
     */
    public static final String CONNECT_TIMEOUT_STR = ApolloConfigContext.getConfig("hry.gateway.connect.timeout");
    public static final int CONNECT_TIMEOUT = Integer.valueOf(CONNECT_TIMEOUT_STR);
    /**
     * 网关响应超时，单位秒
     */
    public static final String READ_TIMEOUT_STR = ApolloConfigContext.getConfig("hry.gateway.read.timeout");
    public static final int READ_TIMEOUT = Integer.valueOf(READ_TIMEOUT_STR);
}
