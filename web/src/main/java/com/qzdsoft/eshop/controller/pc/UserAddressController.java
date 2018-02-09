package com.qzdsoft.eshop.controller.pc;

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
import com.qzdsoft.eshop.domain.deliverytemplate.Area;
import com.qzdsoft.eshop.service.address.AddressService;
import com.qzdsoft.eshop.vo.address.pc.AddressInfo;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;

@Controller
@RequestMapping("/user/deliver_address")
public class UserAddressController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(UserAddressController.class);
	private static final String BASEPATH = "pc/deliver-address";
	@Autowired
	private AddressService addressService;
	
	@RequestMapping("/list")
	public ModelAndView addressList(Integer orderId){
		Integer userId = getLoginId();
		ModelAndView mav = new ModelAndView();
		//登录用户的id  直接从redis中获取
		mav.addObject("ads", addressService.getUserAddress(userId));
		mav.addObject("orderId", orderId);
		mav.setViewName(BASEPATH+"/address-list");
		return mav;
	}
	/**
	 * 设置默认
	 * @param id
	 * @return
	 */
	@RequestMapping("/ad_fault")
	@ResponseBody
	public Response<String> setDefault(Integer id){
		try {
			Integer userId = getLoginId();
			return addressService.setDefault(userId, id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新默认地址失败，错误信息={}",e.getMessage());
		}
		return Response.error("更新失败");
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/ad_delete")
	@ResponseBody
	public Response<String> delAddress(Integer id) {
		try {
			Integer userId = getLoginId();
			return addressService.delAddress(userId, id);
		} catch (Exception e) {
			logger.error("删除地址失败，错误信息：{}",e.getMessage());
		}
		return Response.error("删除失败");
	}
	
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("provices", addressService.getAreaById(0, null));
		mav.setViewName(BASEPATH+"/address-add");
		return mav;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(Integer id) {
		Integer userId = getLoginId();
		ModelAndView mav = new ModelAndView();
		logger.info("addressId={}",id);
		//登录用户的id  直接从redis中获取
//		mav.addObject("ads", addressService.getUserAddress(1));
		AddressInfo info = addressService.findaddressById(id, userId);
		mav.addObject("address", info);
		mav.addObject("provices", addressService.getAreaById(0, null));
		mav.addObject("citys", addressService.getAreaById(1, info.getProvinceId()));
		mav.addObject("towns", addressService.getAreaById(2, info.getCityId()));
		mav.setViewName(BASEPATH+"/address-edit");
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Response<String> saveOrUpdate(@RequestBody AddressInfo info){
		try {
			return addressService.saveOrUpdate(info, getLoginId());
		} catch (Exception e) {
			logger.error("收货地址更新错误，错误信息={}",e.getMessage());
		}
		
		return new Response<>(ResultEnum.ERROR);
	}
	
	@RequestMapping("/areas")
	@ResponseBody
	public Response<List<Area>> getAreas(Integer level,Integer pid) {
		Integer deepLevel = 0;
		switch (level) {
		case 1://省
			deepLevel = 0;
			break;
		case 2://市
			deepLevel = 1;
			break;
		case 3://区
			deepLevel = 2;
			break;
		}
		List<Area> areas = addressService.getAreaById(deepLevel, pid);
		return new Response<>(ResultEnum.SUCCESS,areas);
	}
}
