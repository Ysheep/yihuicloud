package com.qzdsoft.eshop.domain.orders;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "pay_log")
public class PayLog {
    /**
     * 支付日志id
     */
    @Id
    @Column(name = "paylog_id")
    private Integer paylogId;

    @Column(name = "order_no")
    private String orderNo;

    /**
     * 商户订单号
     */
    @Column(name = "mer_order_no")
    private String merOrderNo;

    /**
     * 回调时间
     */
    @Column(name = "resp_time")
    private Date respTime;

    /**
     * 返回状态码
     */
    @Column(name = "resp_status")
    private String respStatus;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 支付金额
     */
    private BigDecimal price;

    @Column(name = "resp_info")
    private String respInfo;

    /**
     * 获取支付日志id
     *
     * @return paylog_id - 支付日志id
     */
    public Integer getPaylogId() {
        return paylogId;
    }

    /**
     * 设置支付日志id
     *
     * @param paylogId 支付日志id
     */
    public void setPaylogId(Integer paylogId) {
        this.paylogId = paylogId;
    }

    /**
     * @return order_no
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取商户订单号
     *
     * @return mer_order_no - 商户订单号
     */
    public String getMerOrderNo() {
        return merOrderNo;
    }

    /**
     * 设置商户订单号
     *
     * @param merOrderNo 商户订单号
     */
    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }

    /**
     * 获取回调时间
     *
     * @return resp_time - 回调时间
     */
    public Date getRespTime() {
        return respTime;
    }

    /**
     * 设置回调时间
     *
     * @param respTime 回调时间
     */
    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }

    /**
     * 获取返回状态码
     *
     * @return resp_status - 返回状态码
     */
    public String getRespStatus() {
        return respStatus;
    }

    /**
     * 设置返回状态码
     *
     * @param respStatus 返回状态码
     */
    public void setRespStatus(String respStatus) {
        this.respStatus = respStatus;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取支付金额
     *
     * @return price - 支付金额
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置支付金额
     *
     * @param price 支付金额
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return resp_info
     */
    public String getRespInfo() {
        return respInfo;
    }

    /**
     * @param respInfo
     */
    public void setRespInfo(String respInfo) {
        this.respInfo = respInfo;
    }
}