package com.qzdsoft.eshop.vo.orders.pc;

import com.qzdsoft.vo.BaseTableQueryInfo;

public class OrdersQueryParam extends BaseTableQueryInfo {
	
	private Integer userId;
	private Integer orderStatus;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getOrderStatus() {
		return orderStatus!=null?orderStatus:0;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
}
