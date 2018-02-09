package com.qzdsoft.eshop.domain.express;

import java.util.Date;
import javax.persistence.*;

@Table(name = "express_info")
public class ExpressInfo {
    @Id
    @Column(name = "express_id")
    private Integer expressId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "express_no")
    private String expressNo;

    @Column(name = "express_company_code")
    private String expressCompanyCode;

    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * @return express_id
     */
    public Integer getExpressId() {
        return expressId;
    }

    /**
     * @param expressId
     */
    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
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

    /**
     * @return delivery_time
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * @param deliveryTime
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}