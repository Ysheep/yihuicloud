package com.qzdsoft.eshop.vo.orders.pc;

public class OrderSubmitInfo {
	
	private Integer userId;
	private Integer addressId;
	private Integer orderId;
//	private Integer carryType;
	private Integer payType;
	private Integer isUseBalance;
	private String userRemark;
	private Integer isInvoice;//是否开发票
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
//	public Integer getCarryType() {
//		return carryType;
//	}
//	public void setCarryType(Integer carryType) {
//		this.carryType = carryType;
//	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getIsUseBalance() {
		return isUseBalance;
	}
	public void setIsUseBalance(Integer isUseBalance) {
		this.isUseBalance = isUseBalance;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public Integer getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}
	
	
	
}
