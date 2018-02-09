/**
 * 
 */
package com.qzdsoft.eshop.service.sys;

import com.qzdsoft.vo.Response;

/**
 * 手机短信发送
 *
 * <p>detailed comment
 * @author pc-20170420 2017年10月21日
 * @see
 * @since 1.0
 */
public interface SmsService
{
 
    Response<String> sendSms(String phone,String content);
    
    /**
     * 发送验证码
     * @param phone
     * @return
     */
    Response<String> sendVerificationCode(String phone);
    /**
     * 验证验证码
     * @param verificationCode
     * @return
     */
    Response<String> validVerificationCode(String phone,String verificationCode);
    

}
