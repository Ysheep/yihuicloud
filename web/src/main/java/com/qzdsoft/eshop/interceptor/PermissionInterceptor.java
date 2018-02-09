package com.qzdsoft.eshop.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.utils.Constant;
import com.qzdsoft.utils.JsonUtils;

import io.jsonwebtoken.lang.Collections;
/**
 * 权限拦截器
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月29日
 * @see
 * @since 1.0
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
	  
	@Value("${permission.validate}")
    private boolean permissionValidate;  
	  
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//如果配置文件配置不需要登录验证 直接返回true （开发时可以配置为false）
		if(permissionValidate==false) {
			return true;
		}
		String curPath = request.getRequestURI();
		logger.debug("curPath={}",curPath);
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
		    /*
            * 1、确认当前的controller是否需要进行权限判定，如果需要则进行验证。
            * 2、当controller不需要验证，则验证当前的方法是否需要权限验证，需要则进行验证，不需要则跳出
            * */
            //获取方法注解，方法检查是否需要验证权限控制
			Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
			if(permission !=null && !permission.validate()) {//不需要验证
				return true;
			}else{
				 LoginUserInfo user = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
				 Object menuObj = request.getSession().getAttribute(Constant.PERMISSION_URLS+"_"+user.getLoginId());
				 if(menuObj!=null) {
					 logger.debug("======访问权限列表：{}",menuObj);
					 List<String> authorizedUrls  = JsonUtils.jsonToList(menuObj.toString(), String.class);
					 logger.debug("访问链接：{},是否含此链接访问权限：{}",curPath,authorizedUrls.contains(curPath));
					 if(!Collections.isEmpty(authorizedUrls) && authorizedUrls.contains(curPath)) {
						 return true;
					 }else{
						 logger.debug("是否为ajax请求:{}",isAjaxRequest(request));
						 if(isAjaxRequest(request)) {
							 response.setHeader("permission", "false");
							 return false;
						 }else{
							 response.sendRedirect("/401");
	                         return false; 
						 }
					 }
				 }
			}
			
		}
		return true;
	}
	private boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");// 如果是ajax请求响应头会有，x-requested-with；
    }
}
