package com.qzdsoft.eshop.controller.admin.sys;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.SysUser;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.service.sys.RoleService;
import com.qzdsoft.eshop.service.sys.SysUserService;
import com.qzdsoft.eshop.vo.sys.LoginUserInfo;
import com.qzdsoft.eshop.vo.sys.SysUserInfo;
import com.qzdsoft.eshop.vo.sys.UserQueryInfo;
import com.qzdsoft.utils.ExceptionUtil;
/**
 * 
 * 系统用户
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月21日
 * @see
 * @since 1.0
 */
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.UserStatus;
@Controller
@RequestMapping("/admin/sys/user")
public class SysUserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
	private static final String BASEPATH = "admin/sys/user";
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private RoleService roleService;
	
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(BASEPATH+"/index");
		return mv;
	}
	
	@RequestMapping("/data")
	@ResponseBody
	public LayTableResponse<SysUserInfo> data(UserQueryInfo info) {
		List<SysUserInfo> lists = sysUserService.data(info);
		LayTableResponse<SysUserInfo> result = new LayTableResponse<SysUserInfo>(lists);
		return result;
	}
	
	@Permission(validate=false)
	@RequestMapping("/addinit")
	public ModelAndView addinit() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("status", UserStatus.ALL);
		mv.addObject("roleList", roleService.selectAll());
		mv.setViewName(BASEPATH+"/add");
		return mv;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response add(SysUser user) {
		try {
			return sysUserService.add(user);
		} catch (Exception e) {
			logger.error("系统用户新增错误:"+ExceptionUtil.getStackTrace(e));
		}
		return new Response(ResultEnum.ERROR);
	}
	
	@Permission(validate=false)
	@RequestMapping("/editinit")
	public ModelAndView editinit(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("status", UserStatus.ALL);
		mv.addObject("roleList", roleService.selectAll());
		mv.addObject("obj", sysUserService.selectById(id));
		mv.setViewName(BASEPATH+"/edit");
		return mv;
	}
	
	@RequestMapping("/editExcude")
	@ResponseBody
	public Response editExcude(SysUser user) {
		try {
			logger.debug("userId={}",user.getUserId());
			return sysUserService.edit(user);
		} catch (Exception e) {
			logger.error("系统用户新增错误:"+ExceptionUtil.getStackTrace(e));
		}
		return new Response(ResultEnum.ERROR);
	}
	
	@Permission(validate=false)
	@RequestMapping("/del_execute")
	@ResponseBody
	public Response delExcude(Integer id) {
		try {
			return sysUserService.del(id);
		} catch (Exception e) {
			logger.error("系统用户新增错误:"+ExceptionUtil.getStackTrace(e));
		}
		return new Response(ResultEnum.ERROR);
	}
	
	@Permission(validate=false)
	@RequestMapping("/view")
	public ModelAndView Information() {
		ModelAndView mv = new ModelAndView();
		LoginUserInfo info = getUser();
		mv.addObject("obj", sysUserService.pInformation(info));
		mv.setViewName(BASEPATH+"/userInfo");
		return mv;
	}
}
