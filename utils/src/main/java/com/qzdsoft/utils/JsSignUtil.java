package com.qzdsoft.utils;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qzdsoft.utils.entityUtil.WxToken;
import com.qzdsoft.utils.httpUtil.WebUtils;
import com.qzdsoft.vo.SysConfigCode;

public class JsSignUtil {
	private static final Logger logger = LoggerFactory.getLogger(JsSignUtil.class);
	public static String accessToken = "";  
	public static String jsapi_ticket = "";
    public static Map<String, String> sign(String url,String appID,String appsecret) {  
    	String tokenApi = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+appsecret;
    	String str = WebUtils.get(tokenApi, null);
    	logger.debug("str={}",str);
    	Map<String,Object> access = JsonUtils.jsonToMap(str);
    	accessToken = access.get("access_token").toString();
    	String jspiApi = WeiXinUrl.JSAPI_TICKET_URL+"?access_token="+accessToken+"&type=jsapi";
    	String jsstr = WebUtils.get(jspiApi, null);
    	Map<String,Object> jspi = JsonUtils.jsonToMap(jsstr);
    	jsapi_ticket = jspi.get("ticket").toString();
//        accessToken = WeixinUtil.getAccessToken("appid", "appsecret").getToken();  
//        String jsapi_ticket = WeixinUtil.getJsApiTicket(accessToken);  
     
          
        Map<String, String> ret = new HashMap<String, String>();  
        String nonce_str = create_nonce_str();  
        String timestamp = create_timestamp();  
        String string1;  
        String signature = "";  
  
        //注意这里参数名必须全部小写，且必须有序  
        string1 = "jsapi_ticket=" + jsapi_ticket +  
                  "&noncestr=" + nonce_str +  
                  "&timestamp=" + timestamp +  
                  "&url=" + url;  
        System.out.println("string1="+string1);  
  
        signature = Sha1Util.getSha1(string1);  
//        try  
//        {  
//            MessageDigest crypt = MessageDigest.getInstance("SHA-1");  
//            crypt.reset();  
//            crypt.update(string1.getBytes("UTF-8"));  
//        }  
//        catch (NoSuchAlgorithmException e)  
//        {  
//            e.printStackTrace();  
//        }  
//        catch (UnsupportedEncodingException e)  
//        {  
//            e.printStackTrace();  
//        }  
  
        ret.put("url", url);  
        ret.put("jsapi_ticket", jsapi_ticket);  
        ret.put("nonceStr", nonce_str);  
        ret.put("timestamp", timestamp);  
        ret.put("signature", signature);  
        ret.put("appId", appID);  
//        System.out.println("1.ticket(原始)="+jsapi_ticket);  
//        System.out.println("2.url="+ret.get("url"));  
//        System.out.println("3.jsapi_ticket（处理后）="+ret.get("jsapi_ticket"));  
//        System.out.println("4.nonceStr="+ret.get("nonceStr"));  
//        System.out.println("5.signature="+ret.get("signature"));  
//        System.out.println("6.timestamp="+ret.get("timestamp"));  
          
        return ret;  
    }  
      
    
    public static Map<String, String> jsSdkConfig(String url,String jsapiTicket,String appID){
   	 Map<String, String> ret = new HashMap<String, String>();  
        String nonce_str = create_nonce_str();  
        String timestamp = create_timestamp();  
        String string1;  
        String signature = "";  
  
        //注意这里参数名必须全部小写，且必须有序  
        string1 = "jsapi_ticket=" + jsapiTicket +  
                  "&noncestr=" + nonce_str +  
                  "&timestamp=" + timestamp +  
                  "&url=" + url;  
        System.out.println("string1="+string1);  
  
        signature = Sha1Util.getSha1(string1);  
  
        ret.put("url", url);  
        ret.put("jsapi_ticket", jsapi_ticket);  
        ret.put("nonceStr", nonce_str);  
        ret.put("timestamp", timestamp);  
        ret.put("signature", signature);  
        ret.put("appId", appID);  
          
        return ret;  
   } 
    
    
    /** 
     * 随机加密 
     * @param hash 
     * @return 
     */  
    private static String byteToHex(final byte[] hash) {  
        Formatter formatter = new Formatter();  
        for (byte b : hash)  
        {  
            formatter.format("%02x", b);  
        }  
        String result = formatter.toString();  
        formatter.close();  
        return result;  
    }  
  
    /** 
     * 产生随机串--由程序自己随机产生 
     * @return 
     */  
    private static String create_nonce_str() {  
        return UUID.randomUUID().toString();  
    }  
  
    /** 
     * 由程序自己获取当前时间 
     * @return 
     */  
    private static String create_timestamp() {  
        return Long.toString(System.currentTimeMillis() / 1000);  
    }  
}
