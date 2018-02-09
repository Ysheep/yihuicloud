package com.qzdsoft.utils;

public interface WeiXinUrl {
	String API_ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token";
	String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	String API_CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create";
	String USER_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token";
	String SEND_RED_PACK_URL="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	String GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=#wechat_redirect";
	String GET_OPEINID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code ";
	String SEND_TMPLATE_MSG = "https://api.weixin.qq.com/cgi-bin/message/template/send";
	String GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";
	String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	String WX_ACODEUNLIMIT= "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";//小程序二维码b
	String WX_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";//申请退款
	String REFUND_Query_URL = "https://api.mch.weixin.qq.com/pay/refundquery";//查询退款
}
