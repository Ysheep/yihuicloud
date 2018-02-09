package com.qzdsoft.eshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorInfoController
{
	 private static final String BASEPATH = "admin";
    @RequestMapping("/404")
    public ModelAndView error_404()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(BASEPATH+"/error");
        mv.addObject("msg", "404,您访问的页面不存在！");
        return mv;
    }

    @RequestMapping("/401")
    public ModelAndView error_400()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(BASEPATH+"/error");
        mv.addObject("msg", "401,您无权限访问该页面！");
        return mv;
    }
    
    @RequestMapping("/400")
    public ModelAndView error_401()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(BASEPATH+"/error");
        mv.addObject("msg", "400,请求参数不合法！");
        return mv;
    }

    @RequestMapping("/500")
    public ModelAndView error_500()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(BASEPATH+"/error");
        mv.addObject("msg", "500,服务器内部错误！");
        return mv;
    }
}
