package com.qzdsoft.eshop.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "commission_pay_log")
public class CommissionPayLog {
    /**
     * 支付日志id
     */
    @Id
    @Column(name = "paylog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paylogId;

    /**
     * 支付类型 0微信，1支付宝
     */
    @Column(name = "pay_type")
    private Short payType;

    /**
     * 分销提现id
     */
    @Column(name = "apply_id")
    private Integer applyId;

    /**
     * 订单id
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 状态 0失败1成功
     */
    private Short status;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    @Column(name = "resp_msg")
    private String respMsg;

    @Column(name = "resp_time")
    private Date respTime;

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
     * 获取支付类型 0微信，1支付宝
     *
     * @return pay_type - 支付类型 0微信，1支付宝
     */
    public Short getPayType() {
        return payType;
    }

    /**
     * 设置支付类型 0微信，1支付宝
     *
     * @param payType 支付类型 0微信，1支付宝
     */
    public void setPayType(Short payType) {
        this.payType = payType;
    }

    /**
     * 获取分销提现id
     *
     * @return apply_id - 分销提现id
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * 设置分销提现id
     *
     * @param applyId 分销提现id
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取订单id
     *
     * @return order_no - 订单id
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单id
     *
     * @param orderNo 订单id
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取状态 0失败1成功
     *
     * @return status - 状态 0失败1成功
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置状态 0失败1成功
     *
     * @param status 状态 0失败1成功
     */
    public void setStatus(Short status) {
        this.status = status;
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
     * @return resp_msg
     */
    public String getRespMsg() {
        return respMsg;
    }

    /**
     * @param respMsg
     */
    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    /**
     * @return resp_time
     */
    public Date getRespTime() {
        return respTime;
    }

    /**
     * @param respTime
     */
    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }
}