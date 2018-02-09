/**
 * 
 */
package com.qzdsoft.eshop.controller.admin.goodsclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.goodsclass.GoodsClass;
import com.qzdsoft.eshop.service.goodsclass.GoodsClassService;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassInfo;
import com.qzdsoft.eshop.vo.goodsclass.GoodsClassQuery;
import com.qzdsoft.eshop.vo.res.ZtreeNode;
import com.qzdsoft.vo.GoodsClassStatus;
import com.qzdsoft.vo.GoodsStatus;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.ShowIndexStatus;

/**
 * @author pc-20170422
 *
 */
@Controller
@RequestMapping("/admin/goodsclass")
public class GoodsClassController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsClassController.class);
	@Autowired
	private GoodsClassService goodsClassService;
	
	private final static String BASICPATH = "admin/goodsclass";
	
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("status", GoodsClassStatus.ALL);
		mv.setViewName(BASICPATH+"/index");
		return mv;
	}
	
	@Permission(validate=false)
	@RequestMapping("/data")
	@ResponseBody
	public LayTableResponse<GoodsClassInfo> list(GoodsClassQuery info) {
		return new LayTableResponse<GoodsClassInfo>(goodsClassService.findGoodsClass(info));
	}
	
	@Permission(validate=false)
	@RequestMapping("/edit")
	public ModelAndView edit(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("classList", goodsClassService.findGoodsClass(new GoodsClassQuery()));
		mv.addObject("status", GoodsClassStatus.ALL);
		mv.addObject("isShow", ShowIndexStatus.ALL);
		if(id == null) {
			mv.setViewName(BASICPATH+"/add");
		}else {
			mv.addObject("goodsclass",goodsClassService.findById(id));
			mv.setViewName(BASICPATH+"/edit");
		}
		return mv;
	}
	
	@Permission(validate=false)
	@RequestMapping("/execute_add")
	@ResponseBody
	public Response<String> executeAdd(GoodsClass info){
		info.setDeleteStatus(Short.valueOf(GoodsStatus.USE_CODE));
		return goodsClassService.saveUpd(info);
	}
	
	@Permission(validate=false)
	@RequestMapping("/execute_edit")
	@ResponseBody
	public Response<String> executeEdit(GoodsClass info){
		return goodsClassService.saveUpd(info);
	}
	
	
	@Permission(validate=false)
	@RequestMapping("/findZtree")
	@ResponseBody
	public Response<ZtreeNode> findZtree(){
		return new Response(ResultEnum.SUCCESS,goodsClassService.findZtree());
	}
	
	@Permission(validate=false)
	@RequestMapping("/issubordinate")
	@ResponseBody
	public Response<String> isSubordinate(Integer pid,Integer classId){
		return goodsClassService.isSubordinate(pid, classId);
	}
	
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<String> delete(Integer id){
		try {
			return goodsClassService.del(id);
		} catch (Exception e) {
			logger.error("删除失败");
		}
		return Response.error("删除失败");
		
	}
}
