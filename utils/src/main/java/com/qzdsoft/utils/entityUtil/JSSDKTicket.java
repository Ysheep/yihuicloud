package com.qzdsoft.utils.entityUtil;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qzdsoft.utils.JsonUtils;
import com.qzdsoft.utils.WeiXinUrl;
import com.qzdsoft.utils.httpUtil.WebUtils;


public class JSSDKTicket {
	public static Logger logger=LoggerFactory.getLogger(JSSDKTicket.class);
	private String ticket;
	private int expires_in;
	private long timeStamp=System.currentTimeMillis()/1000;
	
	public boolean isExpires() {
		return System.currentTimeMillis()/1000<(timeStamp+expires_in)?false:true;
	}
	private static JSSDKTicket JSAPI_TICKET = null;
	private JSSDKTicket() {
		super();
		logger.debug("JSSDKTicket 初始化");
	}
	
	public static String getticket(String accessToken) {
		if(JSAPI_TICKET == null) {
			synchronized(JSSDKTicket.class) {
				if(JSAPI_TICKET == null) {
					JSAPI_TICKET = getJsTicket(accessToken);
				}
			}
		}else if(JSAPI_TICKET.isExpires()) {
			JSAPI_TICKET = getJsTicket(accessToken);
		}
		return JSAPI_TICKET.getTicket();
	}
	
	private static JSSDKTicket getJsTicket(String accessToken) {
		String jspiApi = WeiXinUrl.JSAPI_TICKET_URL+"?access_token="+accessToken+"&type=jsapi";
    	String jsstr = WebUtils.get(jspiApi, null);
    	Map<String,Object> jspi = JsonUtils.jsonToMap(jsstr);
		JSSDKTicket jssdk = new JSSDKTicket();
		jssdk.setTicket(jspi.get("ticket").toString());
		jssdk.setExpires_in(Integer.valueOf(jspi.get("expires_in").toString()));
		jssdk.setTimeStamp(System.currentTimeMillis()/1000);
		return jssdk;
	}
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
