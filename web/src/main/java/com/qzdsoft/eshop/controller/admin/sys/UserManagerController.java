package com.qzdsoft.eshop.controller.admin.sys;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.service.sys.UserService;
import com.qzdsoft.eshop.vo.sys.UserInfo;
import com.qzdsoft.eshop.vo.sys.UserQueryInfo;
import com.qzdsoft.utils.ExceptionUtil;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.UserSex;

@Controller
@RequestMapping("/admin/user")
public class UserManagerController {
	private static final Logger logger = LoggerFactory.getLogger(UserManagerController.class);
	private static final String BASEPATH = "admin/user";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(BASEPATH+"/index");
		return mv;
	}
	
	@RequestMapping("/data")
	@ResponseBody
	public LayTableResponse<UserInfo> data(UserQueryInfo info) {
		List<UserInfo> lists = userService.selectAllByUser(info);
		LayTableResponse<UserInfo> result = new LayTableResponse<UserInfo>(lists);
		return result;
	}
	
	@RequestMapping("/del_execute")
	@ResponseBody
	public Response<String> delExcude(Integer id) {
		try {
			return userService.del(id);
		} catch (Exception e) {
			logger.error("会员信息删除异常"+ExceptionUtil.getStackTrace(e));
			return new Response<String>(ResultEnum.ERROR,"删除失败");
		}
	}
	
	@Permission(validate=false)
	@RequestMapping("/addinit")
	public ModelAndView addinit() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("status", UserSex.ALL);
		mv.setViewName(BASEPATH+"/add");
		return mv;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response<String> add(User user) {
		try {
			return userService.add(user);
		} catch (Exception e) {
			logger.error("会员新增错误:"+ExceptionUtil.getStackTrace(e));
		}
		return new Response<String>(ResultEnum.ERROR);
	}
	
	@Permission(validate=false)
	@RequestMapping("/editinit")
	public ModelAndView editinit(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("status", UserSex.ALL);
		mv.addObject("obj", userService.findByUserId(id));
		mv.setViewName(BASEPATH+"/edit");
		return mv;
	}
	
	@RequestMapping("/editExcude")
	@ResponseBody
	public Response<String> editExcude(User user) {
		try {
			logger.debug("userId={}",user.getUserId());
			return userService.edit(user);
		} catch (Exception e) {
			logger.error("会员修改错误:"+ExceptionUtil.getStackTrace(e));
		}
		return new Response<String>(ResultEnum.ERROR);
	}
}
