package com.qzdsoft.eshop.vo.orders.pc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.qzdsoft.vo.OrderStatus;

public class OrdersDetailInfo {
	
	private Integer orderId;
	private String orderNo;
	private BigDecimal totalPrice;//商品总价
	private BigDecimal actualTotal;//支付金额
	private BigDecimal carrige;//应收运费
	private BigDecimal endCarrige;//实收运费
	private String orderStatus;
	private String status;
	private String ctime;//成交时间
	private String payTime;//支付时间
	private String deliverTime;//发货时间
	private String endTime;//完结时间
	private Integer addressId;
	private String addressInfo;
	private String trueName;
	private String phone;
	private Integer goodsNum;//商品数量
	private String userRemark;
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
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getActualTotal() {
		return actualTotal;
	}
	public void setActualTotal(BigDecimal actualTotal) {
		this.actualTotal = actualTotal;
	}
	public BigDecimal getCarrige() {
		return carrige;
	}
	public void setCarrige(BigDecimal carrige) {
		this.carrige = carrige;
	}
	public BigDecimal getEndCarrige() {
		return endCarrige;
	}
	public void setEndCarrige(BigDecimal endCarrige) {
		this.endCarrige = endCarrige;
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
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public List<GoodsOrderInfo> getItems() {
		return items;
	}
	public void setItems(List<GoodsOrderInfo> items) {
		this.items = items;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	
	
}
