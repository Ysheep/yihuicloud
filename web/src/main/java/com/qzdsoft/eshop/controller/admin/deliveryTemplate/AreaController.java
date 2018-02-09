package com.qzdsoft.eshop.controller.admin.deliveryTemplate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.service.deliverytemplate.AreaService;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.vo.area.AreaModel;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月21日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/area")
public class AreaController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	private static final String BASEPATH = "admin/transport";
	@Autowired
	private AreaService areaService;
	
	@Permission(validate=false)
	@RequestMapping("/settings")
	public ModelAndView setting(String modelid) {
		ModelAndView mv = new ModelAndView();
		List<AreaModel> model = areaService.getAllarea();
		logger.debug("model={}",model);
		mv.addObject("model", model);
		mv.addObject("modelid", modelid);
		mv.setViewName(BASEPATH+"/area");
		return mv;
	}
	
	@Permission(validate=false)
	@RequestMapping("/areas")
	@ResponseBody
	public Response<List<AreaModel>> getAreas() {
		List<AreaModel> model = areaService.getAllarea();
		return new Response<>(ResultEnum.SUCCESS,model);
	}
}
