package com.qzdsoft.eshop.vo.orders.pc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzdsoft.eshop.vo.orders.OrderGoodsItem;

public class OrderExpressInfo {
	
	private String orderNo;
	private Integer orderId;
	private String expressNo;
	private String expressName;
	private BigDecimal refundTotalPrice;
	private List<OrderGoodsItem> goods = new ArrayList<OrderGoodsItem>();
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public List<OrderGoodsItem> getGoods() {
		return goods;
	}
	public void setGoods(List<OrderGoodsItem> goods) {
		this.goods = goods;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public BigDecimal getRefundTotalPrice() {
		return refundTotalPrice;
	}
	public void setRefundTotalPrice(BigDecimal refundTotalPrice) {
		this.refundTotalPrice = refundTotalPrice;
	}
	
	
}
