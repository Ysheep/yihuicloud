package com.qzdsoft.eshop.vo.orders;

import java.util.List;

public class CommentsEditInfo {
	
	private Integer orderId;
	
	private List<CommentsInfo> items;

	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<CommentsInfo> getItems() {
		return items;
	}

	public void setItems(List<CommentsInfo> items) {
		this.items = items;
	}
	
	

}
