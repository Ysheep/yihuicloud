package com.qzdsoft.eshop.controller.admin.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.Resource;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.service.sys.ResourceService;
import com.qzdsoft.eshop.vo.res.ResourceInfo;
import com.qzdsoft.eshop.vo.res.ResourceQueryInfo;
import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.utils.ResourceType;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月14日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/sys/res")
public class ResourceController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	private static final String BASEPATH = "admin/sys/res"; 
	@Autowired
	private ResourceService resourceService;
	
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(BASEPATH+"/index");
		return mv;
	}
	
	@RequestMapping("/data")
	@ResponseBody
	public LayTableResponse<ResourceInfo> selectData(ResourceQueryInfo info) {
		return resourceService.selectData(info);
	}
	/**
	 * 打开资源新增页面
	 * @return
	 */
	@Permission(validate=false)
	@RequestMapping("/addinit")
	public ModelAndView addInit() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("resourceType", ResourceType.ALL);
		mv.setViewName(BASEPATH+"/add");
		return mv;
	}
	/**
	 * 新增保存
	 * @param info
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Response add(Resource info) {
		return resourceService.save(info);
	}
	
	/**
	 * 打开资源修改页面
	 * @return
	 */
	@Permission(validate=false)
	@RequestMapping("/edit")
	public ModelAndView edit(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("typeList", ResourceType.ALL);
		ResourceInfo info = resourceService.selectById(id);
		mv.addObject("item", info);
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
	public Response editExecute(Resource res) {
		logger.debug("type={}",res.getType());
		return resourceService.editExecute(res);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/del_execute")
	@ResponseBody
	public Response delExecute(Integer id) {
	
		return resourceService.delExecute(id);
	}
	
	@Permission(validate=false)
	@RequestMapping("/findZtree")
	@ResponseBody
	public Response<ZtreeNode> findZtree(){
		return new Response(ResultEnum.SUCCESS,resourceService.findZtree());
	}
}
