package com.qzdsoft.eshop.vo.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qzdsoft.vo.OrderStatus;
import com.qzdsoft.vo.PayType;
import com.qzdsoft.vo.SplitStatus;

public class OrderDetailInfo {
	
	List<OrderGoodsItem> goods = new ArrayList<OrderGoodsItem>();
	private Integer orderId;
	private String orderNo;
	private String payType;
	private String goodsTotalPrice;
	private BigDecimal carrige;
	private BigDecimal endCarrige;
	private BigDecimal onlinePayMoney;
	private BigDecimal balancePayMoney;
	private String userRemark;
	private String orderStatus;
	private String splitStatus;
	private String invoice;
	private String userNmae;
	private String trueName;
	private String addInfo;
	private String phone;
	private String zip;
	private String expressNo;//物流单号
	private String expressCode;//物流公司code
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private String ctime;
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private String payTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date deliverTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date endTime;
	public List<OrderGoodsItem> getGoods() {
		return goods;
	}
	public void setGoods(List<OrderGoodsItem> goods) {
		this.goods = goods;
	}
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
	public String getPayType() {
		if(payType!=null) {
			switch (payType) {
			case PayType.WXPAY_CODE:
				payType = PayType.WXPAY.getValue();
				break;
			case PayType.ALIPAY_CODE:
				payType = PayType.ALIPAY.getValue();
				break;
			case PayType.WXPAY_BALANCE_CODE:
				payType = PayType.WXPAY_BALANCE.getValue();
				break;
			case PayType.ALIPAY_BALANCE_CODE:
				payType = PayType.ALIPAY_BALANCE.getValue();
				break;
			}
		}
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(String goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
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
	public BigDecimal getOnlinePayMoney() {
		return onlinePayMoney;
	}
	public void setOnlinePayMoney(BigDecimal onlinePayMoney) {
		this.onlinePayMoney = onlinePayMoney;
	}
	public BigDecimal getBalancePayMoney() {
		return balancePayMoney;
	}
	public void setBalancePayMoney(BigDecimal balancePayMoney) {
		this.balancePayMoney = balancePayMoney;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getOrderStatus() {
		if(orderStatus!=null) {
			switch (orderStatus) {
			case OrderStatus.UNPAID_CODE:
				orderStatus = OrderStatus.UNPAID.getValue();
				break;
			case OrderStatus.PAID_CODE:
				orderStatus = OrderStatus.PAID.getValue();
				break;
			case OrderStatus.REFUNDING_CODE:
				orderStatus = OrderStatus.REFUNDING.getValue();
				break;
			case OrderStatus.REFUNDED_CODE:
				orderStatus = OrderStatus.REFUNDED.getValue();
				break;
			case OrderStatus.DELIVERED_CODE:
				orderStatus = OrderStatus.DELIVERED.getValue();
				break;
			case OrderStatus.SIGNED_CODE:
				orderStatus = OrderStatus.SIGNED.getValue();
				break;
			case OrderStatus.REFUSE_CODE:
				orderStatus = OrderStatus.REFUSE.getValue();
				break;
			case OrderStatus.CLOSE_CODE:
				orderStatus = OrderStatus.CLOSE.getValue();
				break;
			}
		}
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getSplitStatus() {
		if(splitStatus!=null) {
			switch (splitStatus) {
			case SplitStatus.NOT_SPLIT_CODE:
				splitStatus = SplitStatus.NOT_SPLIT.getValue();
				break;
			case SplitStatus.HAS_SPLIT_CODE:
				splitStatus = SplitStatus.HAS_SPLIT.getValue();
				break;
			}
		}
		return splitStatus;
	}
	public void setSplitStatus(String splitStatus) {
		this.splitStatus = splitStatus;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getUserNmae() {
		return userNmae;
	}
	public void setUserNmae(String userNmae) {
		this.userNmae = userNmae;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
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
	
	
}
