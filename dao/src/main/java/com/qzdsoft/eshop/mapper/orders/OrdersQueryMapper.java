package com.qzdsoft.eshop.mapper.orders;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.vo.orders.OrderAddressInfo;
import com.qzdsoft.eshop.vo.orders.OrderDetailInfo;
import com.qzdsoft.eshop.vo.orders.OrderGoodsItem;
import com.qzdsoft.eshop.vo.orders.OrderRefundInfo;
import com.qzdsoft.eshop.vo.orders.OrdersQueryInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersDetailInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersInfo;
import com.qzdsoft.eshop.vo.orders.pc.OrdersQueryParam;

public interface OrdersQueryMapper{
	
	List<OrdersInfo> findByParam(OrdersQueryParam param);
	
	OrdersDetailInfo findOrderById(@Param("userId") Integer userId,@Param("orderId")Integer orderId);
	/**
	 * 订单管理查询
	 * @param info
	 * @return
	 */
	List<com.qzdsoft.eshop.vo.orders.OrdersInfo> findByQuery(OrdersQueryInfo info);
	/**
	 * 订单管理订单详情
	 * @param orderId
	 * @return
	 */
	OrderDetailInfo selectByOrderId(Integer orderId);
	/**
     * 根据订单id查询订单地址信息
     * @param orderId
     * @return
     */
    OrderAddressInfo selectaddByOrid(Integer orderId);
    /**
     * 查询订单退款
     * @param orderId
     * @return
     */
    OrderRefundInfo selectRefundInfoByOrderId(Integer orderId);
    
    List<OrderGoodsItem> selectOrderItemsByOrderId(Integer orderId);
}