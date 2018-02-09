package com.qzdsoft.eshop.controller.pc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.service.deliverytemplate.AreaService;
import com.qzdsoft.eshop.service.sys.UserService;
import com.qzdsoft.eshop.vo.log.LayerWallertLogInfo;
import com.qzdsoft.eshop.vo.sys.CustomerInfo;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.eshop.vo.sys.UserInfo;
import com.qzdsoft.utils.Constant;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.TypeInfo;

@Controller
@RequestMapping("/user/center")
public class UserCenterController {

	final static Logger logger = LoggerFactory.getLogger(UserCenterController.class);
	final static String BASEPATH = "pc/user";
	
	@Autowired
	private UserService userService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/customer")
	public ModelAndView customer(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		LoginUserInfo userInfo = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
		mv.addObject("userName", userInfo.getName());
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		if(userInfo.getPhone()!=null) {
			list = userService.findAllCustomer(userInfo.getPhone());
		}
		
		mv.addObject("customerList", list);
		mv.setViewName(BASEPATH+"/customer");
		return mv;
	}
	
	@RequestMapping("/asset")
	public ModelAndView asset(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		LoginUserInfo userInfo = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
		User user = userService.findByUserId(Integer.parseInt(userInfo.getLoginId()));
		mv.addObject("balance", user.getBalance());
		mv.addObject("walletList", userService.findWalletLog(user.getUserId(),1));
		mv.setViewName(BASEPATH+"/asset");
		return mv;
	}
	@RequestMapping("/pinfo")
	public ModelAndView pinfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		LoginUserInfo userInfo = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
		User user = userService.findByUserId(Integer.parseInt(userInfo.getLoginId()));
		//处理address
		if(user.getAddress()!=null&&user.getAddress().length()>0) {
			String[] addr = user.getAddress().split(",");
			String address = areaService.selectById(Integer.parseInt(addr[0])).getName()+areaService.selectById(Integer.parseInt(addr[1])).getName()+areaService.selectById(Integer.parseInt(addr[2])).getName();
			user.setAddress(address);
			mv.addObject("proviceId",addr[0]);
			mv.addObject("cityId",addr[1]);
			mv.addObject("citys",  areaService.findAreaById(Integer.parseInt(addr[0])));
			if(addr.length>1) {
				mv.addObject("townId",addr[2]);
				mv.addObject("towns",  areaService.findAreaById(Integer.parseInt(addr[1])));
			}
		}
		mv.addObject("user", user);
		mv.addObject("area1", areaService.findAreaById(0));
		mv.setViewName(BASEPATH+"/pinfo");
		return mv;
	}
	@RequestMapping("/saveInfo")
	@ResponseBody
	public Response<String> save(@RequestBody UserInfo info,HttpServletRequest request){
		LoginUserInfo userInfo = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
		info.setUserId(Integer.parseInt(userInfo.getLoginId()));
		return userService.save(info);
	}
	@RequestMapping("/area")
	@ResponseBody
	public Response<List<TypeInfo>> area(Integer id) {
		return areaService.findAreaById(id); 
	}
	@RequestMapping("/wallet")
	@ResponseBody
	public Response<LayerWallertLogInfo> wallet(HttpServletRequest request,Integer page) {
		LoginUserInfo userInfo = (LoginUserInfo)request.getSession().getAttribute(Constant.SESSION_USER);
		return new Response<LayerWallertLogInfo>(ResultEnum.SUCCESS,userService.findWalletLog(Integer.parseInt(userInfo.getLoginId()),page));
	}
	
}
