/**
 * 
 */
package com.qzdsoft.eshop.service.sys.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.mapper.user.UserMapper;
import com.qzdsoft.eshop.service.sys.RedisService;
import com.qzdsoft.eshop.service.sys.SmsService;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;
import com.qzdsoft.utils.JsonUtils;
import com.qzdsoft.utils.MD5Util;
import com.qzdsoft.utils.constant.RedisConstant;
import com.qzdsoft.utils.httpUtil.WebUtils;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.SysConfigCode;

/**
 * 发送消息service
 *
 * <p>detailed comment
 * @author pc-20170420 2017年10月21日
 * @see
 * @since 1.0
 */
@Service
public class SmsServiceImpl implements SmsService
{

    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
    private static String ENCODING = "utf-8";
    private static final char[] str = {'0','1','2','3','4','5','6','7','8','9','A','B', 
            'C','D','E','F','G','H','I','J','K','L','M','N', 
            'O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    @Autowired
    private RedisService redisService;
    @Autowired
    private UserMapper userMapper;
    /**
     * @see com.qzdsoft.erpcloud.service.msg.SmsService#sendSms()
     */
    @Override
    public Response<String> sendSms(String phone,String content)
    {
        String sendSmsUrl = SysConfigInfo.getValue(SysConfigCode.SMS_SEND_URL);
        String param = SysConfigInfo.getValue(SysConfigCode.DDPAAS_SMS_PARAM);
        Map<String,Object> paramMap =  new HashMap<>();
        
        Map<String,Object> configMap= JsonUtils.jsonToMap(param);
        String appid = configMap.get("app_id").toString();
        paramMap.put("app_id", appid);
        paramMap.put("phone", phone);
        paramMap.put("content", content);
        long nonce = System.currentTimeMillis()/1000;
        paramMap.put("nonce",nonce);
        String sign = MD5Util.MD5Encode(appid+phone+nonce+configMap.get("key"),ENCODING);
        paramMap.put("sign", sign);
        String responseStr = WebUtils.get(sendSmsUrl, paramMap);
        Map<String,Object> responseMap = JsonUtils.jsonToMap(responseStr);
        logger.debug("发送短信url:{},参数：{}，返回数据：{}",sendSmsUrl,paramMap,responseStr);
        if(responseMap!=null&&responseMap.get("code")!=null&&"0".equals(responseMap.get("code").toString())) {
            return new Response<>(ResultEnum.SUCCESS);
        }else {
            logger.error("发送短信错误，返回数据：{}",responseStr);
            return new Response<>(ResultEnum.ERROR);
        }
    }
    
    /**
     * @see com.qzdsoft.erpcloud.service.msg.SmsService#validVerificationCode(java.lang.String)
     */
    @Override
    public Response<String> validVerificationCode(String phone,String verificationCode)
    {
        String key = RedisConstant.redisSmsVerificationKey+phone;
        Object verifStr = redisService.get(key);
        if(verifStr==null) {
            return new Response<>(ResultEnum.ERROR.getCode(),"短信验证码已过期，或者没有发送");
        }else {
            
            Map<String,Object> jsonToMap = JsonUtils.jsonToMap(verifStr.toString());
            String code = (String) jsonToMap.get("verificationCode");
            if(!code.equals(verificationCode)) {
                return new Response<>(ResultEnum.ERROR.getCode(),"短信验证码不正确");
            }
        }
        return new Response<String>(ResultEnum.SUCCESS);
    }

    /**
     * @see com.qzdsoft.erpcloud.service.msg.SmsService#sendVerificationCode(java.lang.String)
     */
    @Override
    public Response<String> sendVerificationCode(String phone)
    {
    	 User record = new User();
		 record.setPhone(phone);
		 List<User> list = userMapper.select(record);
		 if(list.size()>0) {
		 	return Response.error("该手机号已注册");
		 }
    	 Object count = redisService.get(RedisConstant.redisSmsVerificationCount+phone);
         logger.debug("手机号：{},发送短信次数：{}",phone,count);
         if(count!=null&&count.toString().compareTo(SysConfigInfo.getValue(SysConfigCode.MAX_SMS_SEND))>=0) {
             return new Response<>(ResultEnum.ERROR.getCode(),"当日该手机发送短信次数太多");
         }
         
        String verificationCode = generatorVerificationCode();
        String key = RedisConstant.redisSmsVerificationKey+phone;
        Object verifStr = redisService.get(key);
        logger.debug("手机号：{}，短信验证码信息：{}",phone,verifStr);
        if(verifStr!=null) {
            Map<String,Object> jsonToMap = JsonUtils.jsonToMap(verifStr.toString());
            Long sendTime = (Long) jsonToMap.get("sendTime");
            if(System.currentTimeMillis()-sendTime<Long.parseLong(SysConfigInfo.getValue(SysConfigCode.VERIFICATION_CODE_INTERVAL_TIME))) {
                logger.warn("手机号：{}，短信发送过于频繁",phone);
                return new Response<>(ResultEnum.ERROR.getCode(),"验证码发送过于频繁，请稍后再试");
            }else {
                verificationCode = (String)jsonToMap.get("verificationCode");
            }
        }
        
        Map<String,Object> verifMap = new HashMap<>();
        verifMap.put("verificationCode", verificationCode);
        verifMap.put("sendTime", System.currentTimeMillis());
        redisService.set(key, JsonUtils.objectToJson(verifMap),
                Integer.parseInt(SysConfigInfo.getValue(SysConfigCode.VERIFICATION_CODE_VALID_TIME)));
        
        String content = String.format(SysConfigInfo.getValue(SysConfigCode.VERFICATION_CONTENT), verificationCode);
        Response<String> result = sendSms(phone, content);
        if(!ResultEnum.SUCCESS.getCode().equals(result.getCode())) {
            redisService.del(key);
        }else {
            //发送次数+1
            String sendCount = (count!=null?(Integer.parseInt(count.toString())+1)+"":"1");
            LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
            redisService.set(RedisConstant.redisSmsVerificationCount+phone,sendCount,(int)seconds);
        }
        return result;
    }

    private String generatorVerificationCode() {
        String validate = "";
        while(true) {
            char c = str[new Random().nextInt(36)];
            validate += c;
            if(validate.length()==6) {
                break;
            }
        }
        return validate;
    }

	
}
