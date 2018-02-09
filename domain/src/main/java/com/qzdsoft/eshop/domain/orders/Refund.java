package com.qzdsoft.eshop.domain.orders;

import java.math.BigDecimal;
import javax.persistence.*;

public class Refund {
    @Id
    @Column(name = "refund_id")
    private Integer refundId;

    @Column(name = "refund_no")
    private String refundNo;

    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 微信退款订单号
     */
    @Column(name = "wx_refund")
    private String wxRefund;

    /**
     * 0七天理由退款 1其他
     */
    @Column(name = "refund_reason")
    private String refundReason;

    /**
     * 退款金额
     */
    @Column(name = "refund_fee")
    private BigDecimal refundFee;

    /**
     * 0退款 1退货退款
     */
    private Short type;

    @Column(name = "express_no")
    private String expressNo;

    @Column(name = "express_company_code")
    private String expressCompanyCode;

    /**
     * @return refund_id
     */
    public Integer getRefundId() {
        return refundId;
    }

    /**
     * @param refundId
     */
    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    /**
     * @return refund_no
     */
    public String getRefundNo() {
        return refundNo;
    }

    /**
     * @param refundNo
     */
    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    /**
     * @return company_id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取微信退款订单号
     *
     * @return wx_refund - 微信退款订单号
     */
    public String getWxRefund() {
        return wxRefund;
    }

    /**
     * 设置微信退款订单号
     *
     * @param wxRefund 微信退款订单号
     */
    public void setWxRefund(String wxRefund) {
        this.wxRefund = wxRefund;
    }

    /**
     * 获取0七天理由退款 1其他
     *
     * @return refund_reason - 0七天理由退款 1其他
     */
    public String getRefundReason() {
        return refundReason;
    }

    /**
     * 设置0七天理由退款 1其他
     *
     * @param refundReason 0七天理由退款 1其他
     */
    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    /**
     * 获取退款金额
     *
     * @return refund_fee - 退款金额
     */
    public BigDecimal getRefundFee() {
        return refundFee;
    }

    /**
     * 设置退款金额
     *
     * @param refundFee 退款金额
     */
    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    /**
     * 获取0退款 1退货退款
     *
     * @return type - 0退款 1退货退款
     */
    public Short getType() {
        return type;
    }

    /**
     * 设置0退款 1退货退款
     *
     * @param type 0退款 1退货退款
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * @return express_no
     */
    public String getExpressNo() {
        return expressNo;
    }

    /**
     * @param expressNo
     */
    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    /**
     * @return express_company_code
     */
    public String getExpressCompanyCode() {
        return expressCompanyCode;
    }

    /**
     * @param expressCompanyCode
     */
    public void setExpressCompanyCode(String expressCompanyCode) {
        this.expressCompanyCode = expressCompanyCode;
    }
}