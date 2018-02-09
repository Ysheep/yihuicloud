package com.qzdsoft.eshop.vo.orders;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qzdsoft.vo.InvoiceStatus;
import com.qzdsoft.vo.OrderStatus;
import com.qzdsoft.vo.PayType;
import com.qzdsoft.vo.SplitStatus;

public class OrdersInfo {
	
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
	private String status;
	private String splitStatus;
	private String invoice;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date ctime;
	
	
	
	
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
		if(invoice!=null) {
			switch (invoice) {
			case InvoiceStatus.NO_INVOICE_CODE:
				invoice = InvoiceStatus.NO_INVOICE.getValue();
				break;
			case InvoiceStatus.PERSONAL_INVOICE_CODE:
				invoice = InvoiceStatus.PERSONAL_INVOICE.getValue();
				break;
			case InvoiceStatus.COMPANY_INVOICE_CODE:
				invoice = InvoiceStatus.COMPANY_INVOICE.getValue();
				break;
			}
		}
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
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
	
	
}
