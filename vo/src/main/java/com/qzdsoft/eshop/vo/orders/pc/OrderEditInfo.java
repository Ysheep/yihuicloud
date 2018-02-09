package com.qzdsoft.eshop.vo.orders.pc;

import java.util.List;

import com.qzdsoft.eshop.domain.orders.GoodsOrder;

public class OrderEditInfo {
	
	
	private Integer userId;//用户id
	
	
	private Integer orderType;//订单提交方式  1 购物车提交  0直接购买
	
	private List<GoodsOrderInfo> goods ;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public List<GoodsOrderInfo> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsOrderInfo> goods) {
		this.goods = goods;
	}


	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	
	
}
