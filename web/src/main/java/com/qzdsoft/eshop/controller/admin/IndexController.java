package com.qzdsoft.eshop.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.service.sys.LoginService;
import com.qzdsoft.eshop.service.sys.ResourceService;
import com.qzdsoft.eshop.vo.res.MenuInfo;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.utils.Constant;
import com.qzdsoft.utils.JsonUtils;

/**
 * 
 * 首页controller
 *
 * <p>detailed comment
 * @author pc-20170420 2017年11月14日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController{
    
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);
    private static final String BASEPATH = "admin";
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private LoginService loginService;
    

    /**
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        logger.debug("进入首页。。。。。");
        ModelAndView mav = new ModelAndView();
        LoginUserInfo user = getUser();
        List<MenuInfo> menus = resourceService.findPermissionMenu(user);
        List<String> permissionUrl = loginService.selectAuthorizedUrl(user);
        request.getSession().setAttribute(Constant.PERMISSION_URLS+"_"+user.getLoginId(),JsonUtils.objectToJson(permissionUrl));
        mav.addObject("menus", menus);
        mav.setViewName(BASEPATH+"/index");
        return mav;
    }
    @RequestMapping("/notLogin")
    public ModelAndView notLogin() {
        logger.debug("进入首页。。。。。");
        ModelAndView mav = new ModelAndView();
        mav.setViewName(BASEPATH+"/login");
        return mav;
    }


    /**
     * 首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView home(HttpServletRequest request, Model model) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("main");
        return mav;
    }
    
}



