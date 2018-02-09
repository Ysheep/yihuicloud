package com.qzdsoft.eshop.controller.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;
import com.qzdsoft.utils.Constant;
import com.qzdsoft.utils.JsonUtils;
import com.qzdsoft.utils.httpUtil.WebUtils;
import com.qzdsoft.vo.SysConfigCode;

public class BaseController
{
    protected HttpServletRequest request;

    protected HttpServletResponse response;
    
    protected LoginUserInfo getUser() {
        return (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
    }
    
    protected Integer getLoginId() {
    	LoginUserInfo userInfo = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
        return Integer.parseInt(userInfo.getLoginId());
    }
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
    }
    
    /**
	 * 获取openid
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public  String getOpedId() throws IOException {
	    String appID = SysConfigInfo.getValue(SysConfigCode.APPID);
	    String appsecret = SysConfigInfo.getValue(SysConfigCode.APP_SECRET);
		String code = request.getParameter("code");
		if(code==null) {
			String curUrl = request.getRequestURL().toString();
			String  apicode = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appID+"&redirect_uri="+curUrl+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
			response.sendRedirect(apicode);
			return null;
		}else{
			//获取openid
			String api = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appID+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
			String res = WebUtils.get(api, null);
			Map<String,Object> result = JsonUtils.jsonToMap(res);
			return result.get("openid").toString();
		}
	}
}
