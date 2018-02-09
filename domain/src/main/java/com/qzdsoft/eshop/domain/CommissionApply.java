package com.qzdsoft.eshop.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "commission_apply")
public class CommissionApply {
    /**
     * 分销提现id
     */
    @Id
    @Column(name = "apply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applyId;

    /**
     * 代理商id
     */
    @Column(name = "agent_id")
    private Integer agentId;

    /**
     * 申请时间
     */
    @Column(name = "apply_time")
    private Date applyTime;

    /**
     * 审核时间
     */
    @Column(name = "check_time")
    private Date checkTime;

    /**
     * 1 正在申请 2 审核通过 3 已经打款 4 打款成功 5打款失败
     */
    private Short status;

    /**
     * 提现金额
     */
    private BigDecimal money;

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
     * 获取代理商id
     *
     * @return agent_id - 代理商id
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * 设置代理商id
     *
     * @param agentId 代理商id
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * 获取申请时间
     *
     * @return apply_time - 申请时间
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置申请时间
     *
     * @param applyTime 申请时间
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取审核时间
     *
     * @return check_time - 审核时间
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * 设置审核时间
     *
     * @param checkTime 审核时间
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * 获取1 正在申请 2 审核通过 3 已经打款 4 打款成功 5打款失败
     *
     * @return status - 1 正在申请 2 审核通过 3 已经打款 4 打款成功 5打款失败
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置1 正在申请 2 审核通过 3 已经打款 4 打款成功 5打款失败
     *
     * @param status 1 正在申请 2 审核通过 3 已经打款 4 打款成功 5打款失败
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取提现金额
     *
     * @return money - 提现金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置提现金额
     *
     * @param money 提现金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}