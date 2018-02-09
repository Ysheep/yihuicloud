package com.qzdsoft.eshop.vo.orders;

import com.qzdsoft.vo.BaseTableQueryInfo;

public class OrdersQueryInfo extends BaseTableQueryInfo{
	
	private String keyWord;
	
	private String orderStatus;
	
	private String startTime;
	
	private String endTime;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		if(keyWord!=null || keyWord!="") {
			keyWord = keyWord.trim();
		}
		this.keyWord = keyWord;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
