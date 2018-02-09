package com.qzdsoft.eshop.controller.admin.orders;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qzdsoft.eshop.controller.admin.BaseController;
import com.qzdsoft.eshop.domain.express.ExpressInfo;
import com.qzdsoft.eshop.service.express.ExpressCompanyService;
import com.qzdsoft.eshop.service.express.ExpressInfoService;
import com.qzdsoft.eshop.service.orders.OrdersService;
import com.qzdsoft.eshop.service.sys.Permission;
import com.qzdsoft.eshop.vo.orders.OrdersInfo;
import com.qzdsoft.eshop.vo.orders.OrdersQueryInfo;
import com.qzdsoft.utils.ExceptionUtil;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.OrderStatus;
import com.qzdsoft.vo.RefundStatus;
import com.qzdsoft.vo.Response;
import com.qzdsoft.vo.ResultEnum;
/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年2月3日
 * @see
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);
	private static final String BASEPATH = "admin/orders";
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private ExpressInfoService expressInfoService;
	
	@Autowired
	private ExpressCompanyService expressCompanyService;
	
	@Permission(validate=false)
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("orderStatus", OrderStatus.ALL);
		mv.setViewName(BASEPATH+"/index");
		return mv;
	}
	
	@Permission(validate=false)
	@RequestMapping("/data")
	@ResponseBody
	public LayTableResponse<OrdersInfo> Data(OrdersQueryInfo info) {
		LayTableResponse<OrdersInfo> result =ordersService.slectOrdersByQuery(info);
		return result;
	}
	
	
	@Permission(validate=false)
	@RequestMapping("/detail")
	public ModelAndView detail(Integer orderId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("orderinfo", ordersService.selectOrderDetailByOrderI(orderId));//订单信息
		mv.addObject("expressinfo", expressInfoService.selectOrderExpress(orderId));//物流信息
//		mv.addObject("express", expressInfoService.getExpressById(orderId));
		mv.setViewName(BASEPATH+"/order_detail");
		return mv;
	}
	
	@Permission(validate=false)
	@RequestMapping("/express")
	@ResponseBody
	public Response<Map<String,Object>> expressInfo(Integer orderId) {
		Map<String,Object> map =  expressInfoService.getExpressById(orderId);
		return new Response<>(ResultEnum.SUCCESS,map);
	}
	
	@Permission(validate=false)
	@RequestMapping("/delivery")
	public ModelAndView orderDelivery(Integer orderId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("orderinfo", ordersService.selectAddressInfo(orderId));
		mv.addObject("express", expressCompanyService.selectALLExpress());
		mv.setViewName(BASEPATH+"/order_delivery");
		return mv;
	}
	
	/**
	 * 确认发货
	 * @return
	 */
	@Permission(validate=false)
	@RequestMapping("/deliveryconfirm")
	@ResponseBody
	public Response deliveryConfirm(ExpressInfo info) {
		try {
			if(info.getExpressNo()!=null && info.getExpressNo()!="") {
				info.setExpressNo(info.getExpressNo().trim());
			}
			return ordersService.deliveryConfirm(info);
		} catch (RuntimeException re) {
			logger.error("发货异常，错误信息:{}",ExceptionUtil.getStackTrace(re));
			return Response.error(re.getLocalizedMessage());
		}catch(Exception e ) {
			logger.error("发货异常，错误信息:{}",ExceptionUtil.getStackTrace(e));
		}
		
		return Response.error("发货失败");
	}
	
	@Permission(validate=false)
	@RequestMapping("/refund")
	public ModelAndView orderRefund(Integer orderId) {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("orderinfo", ordersService.selectByOrderId(orderId));
		mv.addObject("restatus", RefundStatus.ALL);
		mv.setViewName(BASEPATH+"/order_refund");
		return mv;
	}
}
