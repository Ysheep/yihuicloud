package com.qzdsoft.eshop.controller.pc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.service.shoppingcart.ShoppingCartService;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartDelInfo;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartEditInfo;
import com.qzdsoft.eshop.vo.goods.pc.ShoppingCartInfo;
import com.qzdsoft.utils.ExceptionUtil;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
/**
 * 购物车
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月31日
 * @see
 * @since 1.0
 */
@RequestMapping("/user/cart")
@Controller
public class CartController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	private static final String BASEPATH = "pc/cart";
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView index() {
		Integer userId = getLoginId();
		ModelAndView mav = new ModelAndView();
		ShoppingCartInfo info = shoppingCartService.findCartInfo(userId);
		mav.addObject("cartList", info);
		mav.setViewName(BASEPATH+"/cart");
		return mav;
	}
	
	@RequestMapping("/update_num")
	@ResponseBody
	public Response<String> updateCartNum(Integer cartId,Integer num){
		try {
			Integer userId = getLoginId();
			return shoppingCartService.updateCart(cartId, num, userId);
		} catch (Exception e) {
			logger.error("更新购物车数量错误，错误信息:{}",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("失败");
	}
	/**
	 * 删除购物车
	 * @param cartIds
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public Response<String> delCartItem(@RequestBody ShoppingCartDelInfo cartIds){
		try {
			Integer userId = getLoginId();
			cartIds.setUserId(userId);
			return shoppingCartService.del(cartIds);
		} catch (Exception e) {
			logger.error("更新购物车删除失败，错误信息:{}",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("失败");
	}
	/**
	 * 新增购物车
	 * @param info
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Response<String> add(ShoppingCartEditInfo info){
		try {
			Integer userId = getLoginId();
			info.setUserId(userId);
			return shoppingCartService.cartAdd(info);
		} catch (Exception e) {
			logger.error("添加购物车失败，错误信息：{}",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("失败");
	}
	
	@RequestMapping("/cart")
	@ResponseBody
	public Response<ShoppingCartInfo> getCartInfo(){
		Integer userId = getLoginId();
		ShoppingCartInfo info = shoppingCartService.findCartInfo(userId);
		return new Response<>(ResultEnum.SUCCESS,info);
	}
}
