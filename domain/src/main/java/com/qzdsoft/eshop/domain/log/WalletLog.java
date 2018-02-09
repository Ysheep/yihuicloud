package com.qzdsoft.eshop.domain.log;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "wallet_log")
public class WalletLog {
    @Id
    @Column(name = "log_id")
    private Integer logId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "pay_time")
    private Date payTime;

    @Column(name = "split_money")
    private BigDecimal splitMoney;

    @Column(name = "is_freeze")
    private Integer isFreeze;

    /**
     * 0 分润收入 1购买支出
     */
    private Integer type;

    /**
     * 状态 0未到账 1已到账
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * @return log_id
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return pay_time
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * @param payTime
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * @return split_money
     */
    public BigDecimal getSplitMoney() {
        return splitMoney;
    }

    /**
     * @param splitMoney
     */
    public void setSplitMoney(BigDecimal splitMoney) {
        this.splitMoney = splitMoney;
    }

    /**
     * @return is_freeze
     */
    public Integer getIsFreeze() {
        return isFreeze;
    }

    /**
     * @param isFreeze
     */
    public void setIsFreeze(Integer isFreeze) {
        this.isFreeze = isFreeze;
    }

    /**
     * 获取0 分润收入 1购买支出
     *
     * @return type - 0 分润收入 1购买支出
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0 分润收入 1购买支出
     *
     * @param type 0 分润收入 1购买支出
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取状态 0未到账 1已到账
     *
     * @return status - 状态 0未到账 1已到账
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0未到账 1已到账
     *
     * @param status 状态 0未到账 1已到账
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}