/**
 * 
 */
package com.qzdsoft.eshop.controller.pc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.user.User;
import com.qzdsoft.eshop.service.address.AddressService;
import com.qzdsoft.eshop.service.deliverytemplate.DeliveryTemplateService;
import com.qzdsoft.eshop.service.express.ExpressInfoService;
import com.qzdsoft.eshop.service.orders.OrdersService;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.service.sys.UserService;
import com.qzdsoft.eshop.vo.address.pc.AddressInfo;
import com.qzdsoft.eshop.vo.orders.CommentsEditInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderEditInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderExpressInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderPayInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderRefundEditInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderSubmitInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersDetailInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersQueryParam;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;
import com.qzdsoft.utils.ExceptionUtil;
import com.qzdsoft.utils.StringUtil;
import com.qzdsoft.vo.InvoiceStatus;
import com.qzdsoft.vo.OrderStatus;
import com.qzdsoft.vo.PayType;
import com.qzdsoft.vo.RefundType;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
import com.qzdsoft.vo.SysConfigCode;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年2月2日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/user/orders")
public class OrdersController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);
	private static final String BASEPATH = "pc/order";
	
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExpressInfoService expressInfoService;
	
	@Autowired
	private DeliveryTemplateService deliveryTemplateService;
	
	@RequestMapping("/my-orders")
	public ModelAndView orderList(OrdersQueryParam param) {
		Integer userId = getLoginId();
		ModelAndView mav = new ModelAndView();
		mav.addObject("status", param.getOrderStatus()!=null?param.getOrderStatus():0);
		switch (param.getOrderStatus()) {
		case 0:
			param.setOrderStatus(null);
			break;
		case 1:
			param.setOrderStatus(Integer.parseInt(OrderStatus.UNPAID_CODE));
			break;
		case 2:
			param.setOrderStatus(Integer.parseInt(OrderStatus.PAID_CODE));
			break;
		case 3:
			param.setOrderStatus(Integer.parseInt(OrderStatus.DELIVERED_CODE));
			break;
		case 4:
			param.setOrderStatus(Integer.parseInt(OrderStatus.SIGNED_CODE));
			break;
		}
		param.setUserId(userId);
		mav.addObject("data", ordersService.findOrderByParam(param));
		mav.setViewName(BASEPATH+"/my-order");
		return mav;
	}
	
	@RequestMapping("/detail")
	public ModelAndView orderDetail(Integer orderId){
		ModelAndView mav = new ModelAndView();
		mav.addObject("order", ordersService.findOrderById(getLoginId(), orderId));
		mav.setViewName(BASEPATH+"/order-detail");
		return mav;
	}
	/**
	 * 订单确认页面
	 * @param orderId
	 * @param addressId
	 * @return
	 */
	@RequestMapping("/firm-order")
	public ModelAndView firmOrder(Integer orderId,Integer addressId){
		Integer UserId = getLoginId();
		ModelAndView mav = new ModelAndView();
		OrdersDetailInfo orderInfo = ordersService.findOrderById(UserId, orderId);
		AddressInfo info = null;
		if(addressId!=null) {
				//根据id查询
				info = addressService.findaddressById(addressId,UserId);
		}else{
			if(orderInfo.getAddressId()!=null) {
				info = addressService.findaddressById(orderInfo.getAddressId(),UserId);
			}

			
		}
		if(info!=null) {
			StringBuffer ad = new StringBuffer();
			if(!StringUtil.isEmpty(info.getProvinceStr()) ) {
				ad.append(info.getProvinceStr());
			}
			if(!StringUtil.isEmpty(info.getCityStr()) ) {
				ad.append(info.getCityStr());
			}
			if(!StringUtil.isEmpty(info.getDistrictStr()) ) {
				ad.append(info.getDistrictStr());
			}
			info.setAddress(ad.toString()+info.getAddress());
			orderInfo.setAddressId(info.getAddressId());
			orderInfo.setAddressInfo(info.getAddress());
			orderInfo.setTrueName(info.getLinkMan());
			orderInfo.setPhone(info.getMobile());
		}
		mav.addObject("order", orderInfo);
		mav.addObject("payType", PayType.ALL);//支付方式
		mav.addObject("invoice", InvoiceStatus.ALL);//是否开发票
		mav.addObject("delivery", deliveryTemplateService.getDeliverysByOrderId(orderInfo.getOrderId()));
		//用户余额信息
		User user = userService.findByUserId(UserId);
		mav.addObject("balance", user.getBalance()!=null?user.getBalance():BigDecimal.ZERO);
		mav.setViewName(BASEPATH+"/firm-order");
		return mav;
	}
	/**
	 * 订单提交
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/orderAdd")
	public Response<String> orderCreate(@RequestBody OrderEditInfo info){
		try {
			Integer userId = getLoginId();
			info.setUserId(userId);
			return ordersService.orderAdd(info);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提交订单失败",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("提交失败，请重新提交");
	}
	
	
	@RequestMapping("/cancel-order")
	@ResponseBody
	public Response<String> cancelOrder(Integer orderId) {
		try {
			Integer userId = getLoginId();
			return ordersService.cancelOfOrder(orderId, userId);
		} catch (Exception e) {
			logger.error("取消订单失败，错误信息:{}",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("取消订单失败");
	}
	
	@RequestMapping("/delete-order")
	@ResponseBody
	public Response<String> deleteOrder(Integer orderId) {
		try {
			Integer userId = getLoginId();
			return ordersService.delOrder(orderId, userId);
		} catch (Exception e) {
			logger.error("删除订单失败，错误信息:{}",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("删除订单失败");
	}
	
	@RequestMapping("/confirm-order")
	@ResponseBody
	public Response<String> confirmOrder(Integer orderId) {
		try {
			Integer userId = getLoginId();
			return ordersService.signedOrder(orderId, userId);
		} catch (Exception e) {
			logger.error("确认收货失败，错误信息:{}",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("确认收货失败");
	}
	
	@RequestMapping("/remind-deliver")
	@ResponseBody
	public Response<String> remindDeliver(Integer orderId) {
		try {
			Integer userId = getLoginId();
			return ordersService.remindDeliver(orderId, userId);
		} catch (Exception e) {
			logger.error("提醒发货失败，错误信息:{}",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("提醒发货失败");
	}
	
	
	
	@RequestMapping("/apply-refund")
	public ModelAndView applyRefund(Integer orderId) {
		ModelAndView mva = new ModelAndView();
		OrderExpressInfo info = ordersService.selectOrderGoodsbyOrderId(orderId);
		mva.addObject("info", info);
		mva.addObject("type", RefundType.ALL);
		mva.setViewName(BASEPATH+"/refund");
		return mva;
	}
	
	@RequestMapping("/confirm-refund")
	@ResponseBody
	public Response<String> confirmRefund(@RequestBody OrderRefundEditInfo info){
		try {
			Integer userId = getLoginId();
			info.setUserId(userId);
			return ordersService.applyRefund(info);
		} catch (Exception e) {
			logger.error("申请退款失败，错误信息:{}",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("申请退款失败,请联系客服");
	}
	
	@RequestMapping("/deliver")
	public ModelAndView deliver(Integer orderId) {
		ModelAndView mva = new ModelAndView();
		OrderExpressInfo info = ordersService.selectOrderGoodsbyOrderId(orderId);
		mva.addObject("info", info);
		mva.setViewName(BASEPATH+"/deliver");
		return mva;
	}
	/**
	 * 物流信息
	 * @param orderId
	 * @return
	 */
	@Permission(validate=false)
	@RequestMapping("/express")
	@ResponseBody
	public Response<Map<String,Object>> expressInfo(Integer orderId) {
		Map<String,Object> map =  expressInfoService.getExpressById(orderId);
		return new Response<>(ResultEnum.SUCCESS,map);
	}
	/**
	 * 运费计算
	 * @param orderId
	 * @param addressId
	 * @param carryType
	 * @return
	 */
   @RequestMapping("/counterCarryPrice")
   @ResponseBody
   public Response<BigDecimal> counterCarryPrice(Integer orderId,Integer addressId){
	   if(orderId == null) {
		   return new Response(ResultEnum.ERROR.getCode(),"订单不存在",null);
	   }
	   if(addressId == null) {
		   return new Response(ResultEnum.ERROR.getCode(),"请选择收货地址",null);
	   }
	   Integer userId = getLoginId();
	   return ordersService.countCarryPrice(orderId,addressId,userId);
   }
   /**
    * 订单确认付款
    * @param info
    * @return
    */
   @RequestMapping("/submitOrders")
   @ResponseBody
	public Response<String> submitOrders(OrderSubmitInfo info) {
		try {
			if(info.getAddressId() == null) {
				return Response.error("请填写收货地址");
			}
			Integer userId = getLoginId();
			info.setUserId(userId);
			return ordersService.orderSubmit(info);
		} catch (Exception e) {
			logger.error("订单确认付款失败,错误信息:{}",ExceptionUtil.getStackTrace(e));
		}
		return Response.error("订单提交失败");
	}
   
   @RequestMapping("/order_pay.html")
   public ModelAndView orderPay(String orderNo) throws IOException {
       Integer userId = getLoginId();
       ModelAndView mv = new ModelAndView();
       if(StringUtil.isEmpty(orderNo)) {
           throw new RuntimeException("订单号不能为空");
       }
       OrderPayInfo info = ordersService.selectOrderPayInfo(orderNo,userId);
       mv.addObject("order",info);
       if(info.getPayType() == Integer.parseInt(PayType.WXPAY_CODE)) {
    	   mv.setViewName(BASEPATH+"/order_pay");
       }else{
    	 mv.setViewName(BASEPATH+"/aliPay");
       }
       return mv;
   }
   
   @RequestMapping("/queryOrderState")
   @ResponseBody
   public Response<String> queryOrderState(String orderNo) {
	   Integer userId = getLoginId();
	   return ordersService.queryOrderState(orderNo, userId);
   }
   
   @RequestMapping("/goodsEvaluate")
   public ModelAndView goodsEvaluate(Integer orderId) {
	   ModelAndView mv = new ModelAndView();
	   mv.addObject("order", ordersService.findOrderById(getLoginId(), orderId));
	   mv.setViewName(BASEPATH+"/goods-evaluate");
	   return mv;
   }
   @RequestMapping("/commentsave")
   @ResponseBody
   public Response<String> commentsave(@RequestBody CommentsEditInfo info) {
	   Integer userId = getLoginId();
	   return ordersService.commentsave(info, userId);
   }
   
   @RequestMapping("/aliPay")
   @ResponseBody
   public Response<String> aliPay(String orderNo) {
	   Integer userId = getLoginId();
	   if(StringUtil.isEmpty(orderNo)) {
           throw new RuntimeException("订单号不能为空");
       }
	   OrderPayInfo info = ordersService.selectOrderPayInfo(orderNo,userId);
	   AlipayClient alipayClient = 
			   new DefaultAlipayClient(SysConfigInfo.getValue(SysConfigCode.ALIPAY_GATEWAYURL),
					   SysConfigInfo.getValue(SysConfigCode.ALIPAY_APPID), 
					   SysConfigInfo.getValue(SysConfigCode.ALIPAY_MERCHANT_PRIVATE_KEY), 
					   "json", SysConfigInfo.getValue(SysConfigCode.ALIPAY_CHARSET), 
					   SysConfigInfo.getValue(SysConfigCode.APPLICATION_PUBLIC_KEY), 
					   SysConfigInfo.getValue(SysConfigCode.ALIPAY_SIGN_TYPE));
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(SysConfigInfo.getValue(SysConfigCode.ALIPAY_RETURN_URL));
		alipayRequest.setNotifyUrl(SysConfigInfo.getValue(SysConfigCode.ALIPAY_NOTIFY_URL));
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(info.getOrderNo());
		//付款金额，必填
		BigDecimal total_amount = info.getPayMoney();
		//订单名称，必填
		String subject = new String("yihuicloud");
		//商品描述，可空
//		String body = new String("亿慧云商场");
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ "" +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String form="";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		logger.debug("form={}",form);
	   return new Response<>(ResultEnum.SUCCESS,form);
   }
}
