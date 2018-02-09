package com.qzdsoft.eshop.controller.admin.deliveryTemplate;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.service.deliverytemplate.DeliveryTemplateService;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.vo.deliverytemplate.DeliveryTemplateEditInfo;
import com.qzdsoft.eshop.vo.deliverytemplate.DeliveryTemplateInfo;
import com.qzdsoft.utils.ExceptionUtil;
import com.qzdsoft.vo.Response;
/**
 * 运费模板
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月20日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/template")
public class DeliveryTemplateController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryTemplateController.class);
	private static final String BASEPATH = "admin/transport";
	@Autowired
	private DeliveryTemplateService deliveryTemplateService;
	
	/**
	 * 模板页面查询
	 * @return
	 */
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		List<DeliveryTemplateInfo> info = new ArrayList<DeliveryTemplateInfo>();
		info = deliveryTemplateService.getAllDeliveryTemplate();
		mv.addObject("models", info);
		mv.addObject("modelength", info.size());
		mv.setViewName(BASEPATH+"/index");
		return mv;
	}
	/**
	 * 模板删除
	 * @param templateId
	 * @return
	 */
	@Permission(validate=false)
	@RequestMapping("/deltemplate")
	@ResponseBody
	public Response deltemplate(Integer templateId) {
		logger.debug("templateId={}",templateId);
		return deliveryTemplateService.delTemplate(templateId);
	}
	
	/**
	 * 模板新增
	 * @return
	 */
	@Permission(validate=false)
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(BASEPATH+"/createOrUpdate");
		return mv;
	}
	
	@Permission(validate=false)
	@RequestMapping("/createTemplate")
	@ResponseBody
	public Response createTemplate(@RequestBody DeliveryTemplateEditInfo info) {
		try{
		 return	deliveryTemplateService.tempalteSave(info);
		}catch(Exception e){
			logger.error(ExceptionUtil.getStackTrace(e));
		}
		return Response.error("保存失败");
	}
}
