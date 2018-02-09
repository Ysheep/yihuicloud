/**
 * 
 */
package com.qzdsoft.vo;

/**
 * 系统配置表code
 *
 * <p>detailed comment
 * @author pc-20170420 2017年10月21日
 * @see
 * @since 1.0
 */
public interface SysConfigCode
{

    String SMS_RATE = "sm_rate";
    String VOICE_RATE = "voice_rate";
    String VERIFICATION_CODE_INTERVAL_TIME = "verification_code_interval_time";//验证码间隔发送时间
    String VERIFICATION_CODE_VALID_TIME = "verification_code_valid_time";//验证码有效时间
    String SMS_SEND_URL = "sms_send_url";
    String DDPAAS_SMS_PARAM = "ddpaas_sms_param";
    String VERFICATION_CONTENT ="verification_content";
    String MAX_SMS_SEND = "max_send_sms";
    
    String APPID = "app_id";
    String APP_SECRET = "app_secret";
    String MCH_ID = "mch_id";//微信商户号
    String API_KEY = "api_key";
    String URL = "url";
    String CERTPATH = "cert_path";//微信证书绝对路径
    
    String IMAGEUPLOAD = "image_uppath"; //图片上传路径
    String IMAGELOAD = "image_load"; //图片加载路径
    
    String S_APPID = "s_app_id";//微信小程序appid
    String S_APP_SECRET ="s_app_secret";//微信小程序s_app_secret
    String WX_NOTIFY = "wx_notify_url";//微信支付回调
    String QRCODE_PREFIX = "qrcode_prefix";//小程序二维码前缀
    
    String KDNiao_CODE = "EBusinessID";//快递鸟的用户id
    String KDNiao_API_KEY = "kdn_api_key";//快递鸟的api key
    String OFF_TEMPLATE_ID = "off_template_id";//线下代理商支付成功模板消息模板
    
    String WXCHAT_LOGIN="wx_login";//微信登录配置
    String ALIPAY_APPID = "alipay_app_id";//支付宝商户appid
    String ALIPAY_MERCHANT_PRIVATE_KEY = "alipay_merchant_private_key";//支付宝商户私钥，您的PKCS8格式RSA2私钥
    String ALIPAY_PUBLIC_KEY = "alipay_public_key";//支付宝公钥
    String APPLICATION_PUBLIC_KEY = "application_public_key";//支付应用公钥
    String ALIPAY_NOTIFY_URL = "alipay_notify_url";//支付宝异步通知
    String ALIPAY_GATEWAYURL = "alipay_gatewayUrl";//支付宝网关地址
    String ALIPAY_SIGN_TYPE = "alipay_sign_type";//支付宝签名
    String ALIPAY_CHARSET = "alipay_charset";//支付宝字符编码格式
    String ALIPAY_RETURN_URL = "alipay_return_url";//支付宝同步通知

}
