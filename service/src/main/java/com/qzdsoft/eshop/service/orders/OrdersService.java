package com.qzdsoft.eshop.service.orders;

import java.math.BigDecimal;
import java.util.List;

import com.qzdsoft.eshop.domain.express.ExpressInfo;
import com.qzdsoft.eshop.vo.orders.CommentsEditInfo;
import com.qzdsoft.eshop.vo.orders.OrderAddressInfo;
import com.qzdsoft.eshop.vo.orders.OrderDetailInfo;
import com.qzdsoft.eshop.vo.orders.OrdersQueryInfo;
import com.qzdsoft.eshop.vo.orders.pc.GoodsOrderInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderEditInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderExpressInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderPayInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderRefundEditInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrderSubmitInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersDetailInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersQueryParam;
import com.qzdsoft.vo.LayTableResponse;
import com.qzdsoft.vo.Page;
import com.qzdsoft.vo.Response;

/**
 * 
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年2月1日
 * @see
 * @since 1.0
 */
public interface OrdersService {
	/**
	 * 查询用户订单列表
	 * @param param
	 * @return
	 */
	Page<OrdersInfo> findOrderByParam(OrdersQueryParam param);
	/**
	 * 查询订单详情
	 * @param userId
	 * @param OrderId
	 * @return
	 */
	OrdersDetailInfo findOrderById(Integer userId,Integer orderId);
	/**
	 * 订单创建
	 * @param info
	 * @return
	 */
	Response<String> orderAdd(OrderEditInfo info);
	/**
	 * 取消订单
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Response<String> cancelOfOrder(Integer orderId,Integer userId);
	/**
	 * 提醒发货
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Response<String> remindDeliver(Integer orderId,Integer userId);
	/**
	 * 确认收货
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Response<String> signedOrder(Integer orderId,Integer userId);
	
	/**
     * 订单申请退款
     * @param token
     * @param orderId
     * @return
     */
    Response<String> applyRefund(OrderRefundEditInfo info);
    /**
     * 删除订单
     * @param orderId
     * @param userId
     * @return
     */
    Response<String> delOrder(Integer orderId,Integer userId);
    /**
     * 查询订单物流信息
     * @param orderId
     * @return
     */
    OrderExpressInfo selectOrderGoodsbyOrderId(Integer orderId);
    /**
     * 计算订单运费
     * @param orderId
     * @param addressId
     * @return
     */
    Response<BigDecimal> countCarryPrice(Integer orderId,Integer addressId,Integer userId);
    /**
     * 订单付款结算
     * @param info
     * @return
     */
    Response<String> orderSubmit(OrderSubmitInfo info);
    /*===================== admin =============================*/
    /**
     * 订单管理查询
     * @param info
     * @return
     */
   LayTableResponse<com.qzdsoft.eshop.vo.orders.OrdersInfo> slectOrdersByQuery(OrdersQueryInfo info);
   /**
    * 订单详情
    * @param orderId
    * @return
    */
   OrderDetailInfo selectOrderDetailByOrderI(Integer orderId);
   
   /**
    * 订单id查询订单地址信息
    * @param orderId
    * @return
    */
   OrderAddressInfo selectAddressInfo(Integer orderId);
   
   /**
    * 确认发货
    * @return
    */
   Response deliveryConfirm(ExpressInfo info);
   
   BigDecimal totalCaryPrice(List<GoodsOrderInfo> info,Integer cityId);
   
   /**
    * 查询支付订单信息
    * @param orderNo
    * @return
    */
   OrderPayInfo selectOrderPayInfo(String orderNo,Integer userId);
   
   Response<String> queryOrderState(String orderNo,Integer userId);
   
   Response<String> commentsave(CommentsEditInfo info,Integer userId);
}
