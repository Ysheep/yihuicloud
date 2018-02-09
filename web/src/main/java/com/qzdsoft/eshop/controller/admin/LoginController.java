/**
 * 
 */
package com.qzdsoft.eshop.controller.admin;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
import com.qzdsoft.eshop.service.sys.LoginService;
import com.qzdsoft.eshop.service.sys.SmsService;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.utils.Constant;
import com.qzdsoft.utils.StringUtil;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.TypeInfo;

/**
 * 登录controller
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月15日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController
{
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);
    private static final String BASEPATH = "admin";
    @Autowired
    private Producer captchaProducer;
    @Autowired
    private LoginService loginService;
    @Autowired
    private SmsService smsService;
    
    @RequestMapping("")
    public ModelAndView loginInit(HttpServletRequest request, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(BASEPATH+"/login");
        return mav;
    }
    @ResponseBody
    @RequestMapping("/login")
    public Object login(String username,String password,String verifyCode){
        //从session中取出servlet生成的验证码text值  
        String kaptchaExpected = (String)request.getSession().getAttribute(Constant.KAPTCHA_SESSION_KEY);
      //校验验证码是否正确  
        if (verifyCode == null || !verifyCode.equalsIgnoreCase(kaptchaExpected)){  
            return new Response<String>(ResultEnum.ERROR.getCode(),"验证码错误");
        }else {
            if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)) {
                return new Response<String>(ResultEnum.ERROR.getCode(),"参数错误");
            }else {
               LoginUserInfo user = loginService.login(username, password);
               if(user==null) {
                   return new Response<String>(ResultEnum.ERROR.getCode(),"用户名或者密码错误");
               }else {
                   logger.info("登录成功：{}",user);
                   //登陆成功
                   List<TypeInfo> buttons = loginService.selectAuthorizedButton(user);
                   List<String> btnIds = new ArrayList<>();
                   for(TypeInfo b:buttons) {
                	   btnIds.add(b.getCode());
                   }
                   request.getSession().setAttribute(Constant.SESSION_USER, user);
                   return new Response<List<String>>(ResultEnum.SUCCESS,btnIds);
               }
            }
        }
      
    }
    
    @RequestMapping("/registerSendSmsCode")
    @ResponseBody
    public Response<String> registerSendSmsCode(String phone){
        if(StringUtil.isEmpty(phone)) {
            return new Response<String>(ResultEnum.ERROR.getCode(),"手机号不能为空");
        }
        return smsService.sendVerificationCode(phone);
    }
    
    
    @RequestMapping("/logout")  
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("redirect:/admin");
        request.getSession().removeAttribute(Constant.SESSION_USER);
        return mv;
    }
    @RequestMapping("/kaptcha.jpg")  
    public ModelAndView handleRequest() throws Exception{  
        // Set to expire far in the past.  
        response.setDateHeader("Expires", 0);  
        // Set standard HTTP/1.1 no-cache headers.  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        // Set standard HTTP/1.0 no-cache header.  
        response.setHeader("Pragma", "no-cache");  
  
        // return a jpeg  
        response.setContentType("image/jpeg");  
  
        // create the text for the image  
        String capText = captchaProducer.createText();  
  
        // store the text in the session  
        request.getSession().setAttribute(Constant.KAPTCHA_SESSION_KEY, capText);  
  
        // create the image with the text  
        BufferedImage bi = captchaProducer.createImage(capText);  
  
        ServletOutputStream out = response.getOutputStream();  
  
        // write the data out  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    }  
}
