package com.qzdsoft.eshop.mapper.orders;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzdsoft.eshop.domain.orders.Orders;
import com.qzdsoft.eshop.util.MyMapper;

public interface OrdersMapper extends MyMapper<Orders> {
	
	Orders selectByOrderNo(@Param("orderNo")String orderNo,@Param("userId")Integer userId);
	Orders selectByOrderId(@Param("orderId")Integer orderId,@Param("userId")Integer userId);
	/**
	 *  查询所有未分润的订单 -->
	 * @param status >=4
	 * @return
	 */
	List<Orders> findAllOrders();
}