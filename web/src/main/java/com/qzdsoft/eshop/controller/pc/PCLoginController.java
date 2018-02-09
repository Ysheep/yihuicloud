/**
 * 
 */
package com.qzdsoft.eshop.controller.pc;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.service.sys.LoginService;
import com.qzdsoft.eshop.service.sys.SmsService;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.utils.Constant;
import com.qzdsoft.utils.StringUtil;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * 登录controller
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/pc")
public class PCLoginController extends BaseController
{
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private SmsService smsService;
    
    @ResponseBody
    @RequestMapping("/login")
    public Object login(String username,String password){
        if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)) {
        	return new Response<String>(ResultEnum.ERROR.getCode(),"请输入用户名或者密码");
        }else {
        	User user = loginService.loginPC(username, password);
        	if(user==null) {
        		return new Response<String>(ResultEnum.ERROR.getCode(),"用户名或者密码错误");
        	}else {
        		logger.info("登录成功：{}",user);
        		LoginUserInfo userInfo = new LoginUserInfo();
        		userInfo.setLoginId(user.getUserId().toString());
        		userInfo.setName(user.getUserName());
        		userInfo.setPhone(user.getPhone());
        		request.getSession().setAttribute(Constant.SESSION_USER, userInfo);
        		return new Response<String>(ResultEnum.SUCCESS);
        	}
        }
      
    }
    
    @RequestMapping("/index")
    public ModelAndView indexLogin() {
 	   logger.debug("------登录------");
 	   ModelAndView mv = new ModelAndView();
 	   mv.setViewName("redirect:/");
 	   return mv;
    }
    
    
    @RequestMapping("/registerSendSmsCode")
    @ResponseBody
    public Response<String> registerSendSmsCode(String phone){
        if(StringUtil.isEmpty(phone)) {
            return new Response<String>(ResultEnum.ERROR.getCode(),"手机号不能为空");
        }
        return smsService.sendVerificationCode(phone);
    }
    
    @RequestMapping("/register")
    @ResponseBody
    public Response<String> register(String phone,String pwd,String code,String recommend){
        if(StringUtil.isEmpty(phone)) {
            return new Response<String>(ResultEnum.ERROR.getCode(),"手机号不能为空");
        }
        return loginService.regist(phone, pwd, code, recommend);
    }
    
    
    @RequestMapping("/logout")  
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("redirect:/");
        request.getSession().removeAttribute(Constant.SESSION_USER);
        return mv;
    }
    
    
}
