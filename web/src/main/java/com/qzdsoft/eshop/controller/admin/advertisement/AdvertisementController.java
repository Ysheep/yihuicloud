package com.qzdsoft.eshop.controller.admin.advertisement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.advertisement.Advertisement;
import com.qzdsoft.eshop.service.advertisement.AdvertisementService;
import com.qzdsoft.eshop.service.goods.GoodsService;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.vo.advertisement.AdvertisementInfo;
import com.qzdsoft.eshop.vo.advertisement.AdvertisementQueryInfo;
import com.qzdsoft.eshop.vo.goods.GoodsQueryInfo;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.TypeInfo;
@Controller("adminAdvertisementController")
@RequestMapping("/admin/ad")
public class AdvertisementController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdvertisementController.class);
	private static final String BASEPATH = "admin/advertisement";
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(BASEPATH+"/index");
		return mv;
	}
	
	@RequestMapping("/data")
	@ResponseBody
	public LayTableResponse<AdvertisementInfo> data(AdvertisementQueryInfo info){
		logger.debug("page={}, size={}",info.getPage(),info.getLimit());
		LayTableResponse<AdvertisementInfo> result = advertisementService.getAllAdvertisements(info);
		return result;
	}
	
	
	@Permission(validate=false)
	@RequestMapping("/editinit")
	public ModelAndView editinit(Integer adverId) {
		ModelAndView mv = new ModelAndView();
		AdvertisementInfo info = advertisementService.selctById(adverId);
		mv.addObject("info", info);
		mv.setViewName(BASEPATH+"/edit");
		return mv;
	}
	
	@RequestMapping("/edit_excuted")
	@ResponseBody
	public Response<String> editExcuted(Advertisement info){
		try {
			return advertisementService.createOrUpdate(info);
		} catch (Exception e) {
			logger.error("修改广告信息异常，错误信息:{}",e.getMessage());
		}
		return Response.success("失败");
		
	}
	
	@Permission(validate=false)
	@RequestMapping("/addinit")
	public ModelAndView addinit() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(BASEPATH+"/add");
		return mv;
	}
	
	@RequestMapping("/add_excuted")
	@ResponseBody
	public Response<String> addExcuted(Advertisement info){
		try {
			return advertisementService.createOrUpdate(info);
		} catch (Exception e) {
			logger.error("新增广告信息异常，错误信息:{}",e.getMessage());
		}
		return Response.success("失败");
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Response<String> del(Integer adverId){
		try {
			return advertisementService.del(adverId);
		} catch (Exception e) {
			logger.error("删除广告信息异常，错误信息:{}",e.getMessage());
		}
		return Response.success("失败");
	}
	
	
	@Permission(validate=false)
	@RequestMapping("/goodsList")
	@ResponseBody
	public LayTableResponse<TypeInfo> goodsList(GoodsQueryInfo info) {
		List<TypeInfo> list = goodsService.getGoods(info);
		return new LayTableResponse<TypeInfo>(list);
	}
}
