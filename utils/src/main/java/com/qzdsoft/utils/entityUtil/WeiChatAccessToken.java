package com.qzdsoft.utils.entityUtil;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qzdsoft.utils.JsonUtils;
import com.qzdsoft.utils.httpUtil.WebUtils;

public class WeiChatAccessToken {
	private static final Logger logger = LoggerFactory.getLogger(WeiChatAccessToken.class);
	// 接口访问凭证
	private String access_token;
	// 凭证有效期，单位：秒
	private int expires_in;
	
	private Long timeStamp = System.currentTimeMillis()/1000;
	
	private static WeiChatAccessToken accessToken = null;
	
	private WeiChatAccessToken() {//私有的构造函数
		super();
		logger.debug("WeiChatAccessToken 初始化");
	}
	
	public static String getAccessToken(String appID,String appsecret) {
		if(accessToken==null) {
			synchronized(WeiChatAccessToken.class) {
				if(accessToken == null) {
					accessToken = getWeiChatAccessToken(appID,appsecret);
				}
			}
		}else if(accessToken.isExpires()) {
			accessToken = getWeiChatAccessToken(appID,appsecret);
		}
		return accessToken.getAccess_token();
	}
	
	public boolean isExpires() {
		return System.currentTimeMillis()/1000<(timeStamp+expires_in)?false:true;
	}
	
	private static WeiChatAccessToken getWeiChatAccessToken(String appID,String appsecret) {
		String tokenApi = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+appsecret;
    	String str = WebUtils.get(tokenApi, null);
    	logger.debug("str={}",str);
    	Map<String,Object> access = JsonUtils.jsonToMap(str);
		WeiChatAccessToken token = new WeiChatAccessToken();
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
}
