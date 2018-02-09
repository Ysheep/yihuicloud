package com.qzdsoft.utils.constant;

/**
 *
 * 缓存数据redis相关key
 *
 * Created by Admin
 */
public interface RedisConstant {

    public final static String redisUidPrefix = "qzd-uid-";                 //

    public final static String redisKaptchaPrefix = "qzd-kaptcha-";

    final static String redisButton = "qzd-buttons";
    
    final static String reidsAuthorizedUrl = "qzd-authorizedurl";
    
    final static String redisSmsVerificationKey = "sms-verificationkey-";
    
    final static String redisSmsVerificationCount = "sms-verificationcount-";
}
