package com.qzdsoft.eshop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.utils.Constant;

@Component
public class PCUserLoginInterceptor implements HandlerInterceptor
{

    private Logger logger = LoggerFactory.getLogger(PCUserLoginInterceptor.class);
    
    @Value("${login.validate}")
    private boolean loginValidate;
    
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception
    {
        
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception
    {
        
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
      //如果配置文件配置不需要登录验证 直接返回true （开发时可以配置为false）
        if(loginValidate==false) {
            return true;
        }
       LoginUserInfo user = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
       if(user==null) {
            logger.warn("登录失效，需要重新登录");
            if(isAjaxRequest(request)) {
                response.setHeader("sessionstatus", "false");// 在响应头设置permn状态
            }else {
                logger.debug("没有登录跳转");
                request.setAttribute("status",2); 
                request.getRequestDispatcher("/").forward(request, response);
            }
            return false;
       }
        return true;
    }
    private boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");// 如果是ajax请求响应头会有，x-requested-with；
    }
}
