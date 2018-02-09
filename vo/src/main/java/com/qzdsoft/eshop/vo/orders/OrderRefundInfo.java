package com.qzdsoft.eshop.vo.orders;

import java.math.BigDecimal;

import com.qzdsoft.vo.PayType;
import com.qzdsoft.vo.RefundType;

public class OrderRefundInfo {
	
	private String refundNo;
	private String refundType;
	private String refundReason;
	private String orderNo;
	private String orderId;
	private String payType;
	private String goodsTotalPrice;
	private BigDecimal carrige;
	private BigDecimal endCarrige;
	private BigDecimal onlinePayMoney;
	private BigDecimal balancePayMoney;
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	public String getRefundType() {
		if(refundType!=null) {
			if(refundType.equals(RefundType.REFUND_CODE)) {
				refundType = RefundType.REFUND.getValue();
			}else{
				refundType = RefundType.REFUND_GOODS.getValue();
			}
		}
		return refundType;
	}
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPayType() {
		if(payType!=null) {
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
	
	
	
	
}
