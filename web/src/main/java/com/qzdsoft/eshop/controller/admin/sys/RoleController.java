/**
 * 
 */
package com.qzdsoft.eshop.controller.admin.sys;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.Role;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.service.sys.RoleService;
import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.eshop.vo.role.RoleInfo;
import com.qzdsoft.eshop.vo.role.RoleQueryInfo;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月14日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/sys/role")
public class RoleController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	private static final String BASEPATH = "admin/sys/role";
	
	@Autowired
	private RoleService roleService;
	
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(BASEPATH+"/index");
		return mv;
	}
	
	@RequestMapping("/data")
	@ResponseBody
	public LayTableResponse<RoleInfo> selectData(RoleQueryInfo info) {
		List<RoleInfo> lists = roleService.selectData(info);
		return new LayTableResponse<RoleInfo>(lists);
	}
	
	@Permission(validate=false)
	@RequestMapping("/addinit")
	public ModelAndView addinit() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(BASEPATH+"/add");
		return mv;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Response add(Role role) {
		role.setCtime(new Date());
		return roleService.save(role);
	}
	
	@Permission(validate=false)
	@RequestMapping("/edit")
	public ModelAndView edit(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj", roleService.selctById(id));
		mv.setViewName(BASEPATH+"/edit");
		return mv;
	}
	
	/**
	 * 修改保存
	 * @param res
	 * @return
	 */
	@RequestMapping("/edit_execute")
	@ResponseBody
	public Response editExecute(Role role) {
		
		return roleService.editExecute(role);
	}
	
	/**
	 * 删除
	 * @param res
	 * @return
	 */
	@RequestMapping("/del_execute")
	@ResponseBody
	public Response delExecute(Integer id) {
		return roleService.delExecute(id);
	}
	
	
	 /**
     * 打开ztree页面
     * @param Id
     * @return
     */
	@Permission(validate=false)
    @RequestMapping("/openTree")
    public ModelAndView opendTree(Integer id){
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("roleId", id);
    	 mv.setViewName(BASEPATH+"/treeNode");
    	 return mv;
    }
	
    /**
     *  菜单树
     *
     * @param request
     * @return
     */
	@Permission(validate=false)
    @ResponseBody
    @RequestMapping("/findAllMenu")
    public Response<ZtreeNode> findAllMenu(Integer id) {
    	return new Response(ResultEnum.SUCCESS,roleService.findByRoleId(id));
    }
    
    /**
     * 菜单权限保存
     */
    @ResponseBody
    @RequestMapping("/roleinit")
    public Response roleinit(Integer roleId,String id) {
    	logger.debug("权限保存:roleId={},id={}",roleId,id);
    	return roleService.roleInit(roleId, id);
    }
	
}
