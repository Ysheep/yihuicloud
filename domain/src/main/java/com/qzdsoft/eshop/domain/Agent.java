package com.qzdsoft.eshop.domain;

import java.math.BigDecimal;
import javax.persistence.*;

public class Agent {
    /**
     * 代理商id
     */
    @Id
    @Column(name = "agent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agentId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 姓名
     */
    private String name;

    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 返点
     */
    @Column(name = "abonus_rate")
    private BigDecimal abonusRate;

    /**
     * 推荐人id
     */
    @Column(name = "recommender_id")
    private Integer recommenderId;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 微信id
     */
    @Column(name = "wx_id")
    private String wxId;

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
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取返点
     *
     * @return abonus_rate - 返点
     */
    public BigDecimal getAbonusRate() {
        return abonusRate!=null?abonusRate.setScale(2, BigDecimal.ROUND_FLOOR):BigDecimal.ZERO;
    }

    /**
     * 设置返点
     *
     * @param abonusRate 返点
     */
    public void setAbonusRate(BigDecimal abonusRate) {
        this.abonusRate = abonusRate;
    }

    /**
     * 获取推荐人id
     *
     * @return recommender_id - 推荐人id
     */
    public Integer getRecommenderId() {
        return recommenderId;
    }

    /**
     * 设置推荐人id
     *
     * @param recommenderId 推荐人id
     */
    public void setRecommenderId(Integer recommenderId) {
        this.recommenderId = recommenderId;
    }

    /**
     * 获取微信id
     *
     * @return wx_id - 微信id
     */
    public String getWxId() {
        return wxId;
    }

    /**
     * 设置微信id
     *
     * @param wxId 微信id
     */
    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
    
    
}