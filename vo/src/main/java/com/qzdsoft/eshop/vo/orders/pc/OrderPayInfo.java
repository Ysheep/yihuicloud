/**
 * 
 */
package com.qzdsoft.eshop.vo.orders.pc;

import java.math.BigDecimal;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author Administrator 2018年2月6日
 * @see
 * @since 1.0
 */
public class OrderPayInfo
{
    
	private Integer orderId;
    private String orderNo;
    private BigDecimal payMoney;
    private String wxPayUrl;
    private Integer payType;
    /**
     * @return the orderNo
     */
    public String getOrderNo()
    {
        return orderNo;
    }
    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }
    /**
     * @return the payMoney
     */
    public BigDecimal getPayMoney()
    {
        return payMoney;
    }
    /**
     * @param payMoney the payMoney to set
     */
    public void setPayMoney(BigDecimal payMoney)
    {
        this.payMoney = payMoney;
    }
    /**
     * @return the wxPayUrl
     */
    public String getWxPayUrl()
    {
        return wxPayUrl;
    }
    /**
     * @param wxPayUrl the wxPayUrl to set
     */
    public void setWxPayUrl(String wxPayUrl)
    {
        this.wxPayUrl = wxPayUrl;
    }
    
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
    
    
}
