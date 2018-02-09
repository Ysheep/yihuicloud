package com.qzdsoft.eshop.vo.orders.pc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.qzdsoft.vo.OrderStatus;

public class OrdersInfo {
	
	private Integer orderId;
	private String orderNo;
	private Integer goodsNum;
	private BigDecimal totalPrice;//应收总额
	private BigDecimal actualTotal;//实收总额
	private String orderStatus;
	private String status;
	private List<GoodsOrderInfo> items  = new ArrayList<GoodsOrderInfo>();
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getStatus() {
		if(orderStatus!=null) {
			switch (orderStatus) {
			case OrderStatus.UNPAID_CODE:
				status = OrderStatus.UNPAID.getValue();
				break;
			case OrderStatus.PAID_CODE:
				status = OrderStatus.PAID.getValue();
				break;
			case OrderStatus.REFUNDING_CODE:
				status = OrderStatus.REFUNDING.getValue();
				break;
			case OrderStatus.REFUNDED_CODE:
				status = OrderStatus.REFUNDED.getValue();
				break;
			case OrderStatus.DELIVERED_CODE:
				status = OrderStatus.DELIVERED.getValue();
				break;
			case OrderStatus.SIGNED_CODE:
				status = OrderStatus.SIGNED.getValue();
				break;
			case OrderStatus.REFUSE_CODE:
				status = OrderStatus.REFUSE.getValue();
				break;
			case OrderStatus.CLOSE_CODE:
				status = OrderStatus.CLOSE.getValue();
				break;
			}
		}
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<GoodsOrderInfo> getItems() {
		return items;
	}
	public void setItems(List<GoodsOrderInfo> items) {
		this.items = items;
	}
	public BigDecimal getActualTotal() {
		return actualTotal;
	}
	public void setActualTotal(BigDecimal actualTotal) {
		this.actualTotal = actualTotal;
	}
	
	
}
