package com.haier.hairy.rmp.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RSA加解密
 *
 * @Author: Xie Hao
 * @Create: 2019-07-23 11:38
 **/
public class RSAUtils {

    /**
     * 默认字符串编码
     */
    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private static Cache<String, RSA> CACHE;
    static {
        CACHE = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build();
    }

    public static byte[] encryptByPublicKey(String source, Charset charset, String publicKey){
        RSA rsaCrypto = CACHE.getIfPresent(publicKey);
        if(rsaCrypto == null){
            rsaCrypto = new RSA(null, publicKey);
            CACHE.put(publicKey, rsaCrypto);
        }
        //RSA rsaCrypto = new RSA(null, publicKey);
        return rsaCrypto.encrypt(source, charset, KeyType.PublicKey);
    }

    /**
     * 通过公钥加密，返回Base64编码字符串
     * @param source 原文
     * @param publicKey RSA公钥
     * @return
     */
    public static byte[] encryptByPublicKey(String source, String publicKey){
        return encryptByPublicKey(source, DEFAULT_CHARSET, publicKey);
    }
}
