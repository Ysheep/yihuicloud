package com.qzdsoft.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qzdsoft.utils.httpUtil.WebUtils;

public class WxLoginUtil {
	
	private static Logger logger = LoggerFactory.getLogger(WxLoginUtil.class);
	
	final static String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	//刷新token
	final static String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	//验证token
	final static String CHECK_TOKEN = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	//获取用户信息
	final static String GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
	
	// 接口访问凭证
		private String access_token;
		// 凭证有效期，单位：秒
		private int expires_in;
		
		private Long timeStamp = System.currentTimeMillis()/1000;
		
		private static WxLoginUtil accessToken = null;
		
		private WxLoginUtil() {//私有的构造函数
			super();
			logger.debug("WeiChatAccessToken 初始化");
		}
	
		/*********获取token start********/
		public static String getAccessToken(String appID,String appsecret,String code) {
			if(accessToken==null) {
				synchronized(WxLoginUtil.class) {
					if(accessToken == null) {
						accessToken = getWeiChatAccessToken(appID,appsecret,code);
					}
				}
			}else if(accessToken.isExpires()) {
				accessToken = getWeiChatAccessToken(appID,appsecret,code);
			}
			return accessToken.getAccess_token();
		}
		public boolean isExpires() {
			return System.currentTimeMillis()/1000<(timeStamp+expires_in)?false:true;
		}
		private static WxLoginUtil getWeiChatAccessToken(String appID,String appsecret,String code) {
			String tokenApi = ACCESS_TOKEN.replace("APPID", appID).replace("SECRET", appsecret).replace("CODE",code);
	    	String str = WebUtils.get(tokenApi, null);
	    	logger.debug("str={}",str);
	    	Map<String,Object> access = JsonUtils.jsonToMap(str);
	    	WxLoginUtil token = new WxLoginUtil();
			token.setAccess_token(access.get("access_token").toString());
			token.setExpires_in(Integer.valueOf(access.get("expires_in").toString()));
			token.setTimeStamp(System.currentTimeMillis()/1000);
			return token;
		}
		public String getAccess_token() {
			return access_token;
		}

		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}

		public int getExpires_in() {
			return expires_in;
		}

		public void setExpires_in(int expires_in) {
			this.expires_in = expires_in;
		}

		public Long getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(Long timeStamp) {
			this.timeStamp = timeStamp;
		}
		
		/*********获取token end********/
		
	public static boolean checkToken(String token,String openid) {
		String url = CHECK_TOKEN.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		logger.debug("检测token"+url);
		Map<String,Object> map = JsonUtils.jsonToMap(WebUtils.get(url, null));
		String result = (String)map.get("errcode");
		if(result.equals("0")) {
			return true;//有效
		}else {
			return false;//失效
		}
		
	}
	public static String getToken(String appid,String secret,String code) {
		String url = ACCESS_TOKEN.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
		logger.debug("获取token"+url);
		return WebUtils.get(url, null);
	}
	
	public static String refreshToken(String refreshtoken) {
		String appid = "wx2d2671dfc603a318";
		String url = REFRESH_TOKEN.replace("APPID", appid).replace("REFRESH_TOKEN", refreshtoken);
		logger.debug("刷新token"+url);
		return WebUtils.get(url, null);
	}
	/**
	 * 获取用户信息
	 * @param accesstoken
	 * @param openid
	 * @return
	 */
	public static String getUserInfo(String appid,String secret,String code) {
		Map<String,Object> map = JsonUtils.jsonToMap(WxLoginUtil.getToken(appid,secret,code));
		String token = (String)map.get("access_token");
		String openid = (String)map.get("openid");
		String refreshtoken = (String)map.get("refresh_token");
		boolean checked = WxLoginUtil.checkToken(token,openid);
		if(!checked) {
			Map<String,Object> newMap = JsonUtils.jsonToMap(refreshToken(refreshtoken));
			token = (String)newMap.get("access_token");
			openid = (String)newMap.get("openid");
			refreshtoken = (String)map.get("refresh_token");
		}
		
		String url = GET_USERINFO.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		return WebUtils.get(url, null);
	}
	
}
