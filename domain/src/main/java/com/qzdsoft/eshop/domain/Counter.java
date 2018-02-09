package com.qzdsoft.eshop.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Counter {
    @Id
    @Column(name = "counter_id")
    private Integer counterId;
    /**
     * 批次id
     */
    @Column(name = "batch_id")
    private Integer batchId;
    /**
     * 代理商id
     */
    @Column(name = "agent_id")
    private Integer agentId;

    /**
     * 货柜编码
     */
    @Column(name = "counter_code")
    private String counterCode;

    /**
     * 店名
     */
    @Column(name = "shop_name")
    private String shopName;

    /**
     * 地址
     */
    private String address;
    
    /**
     * 返点
     */
    @Column(name = "abonus_rate")
    private BigDecimal abonusRate;
    
    /**
     * 0不可以，1可用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ctime;

    /**
     * @return counter_id
     */
    public Integer getCounterId() {
        return counterId;
    }

    /**
     * @param counterId
     */
    public void setCounterId(Integer counterId) {
        this.counterId = counterId;
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
     * 获取货柜编码
     *
     * @return counter_code - 货柜编码
     */
    public String getCounterCode() {
        return counterCode;
    }

    /**
     * 设置货柜编码
     *
     * @param counterCode 货柜编码
     */
    public void setCounterCode(String counterCode) {
        this.counterCode = counterCode;
    }

    /**
     * 获取店名
     *
     * @return shop_name - 店名
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置店名
     *
     * @param shopName 店名
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取0不可以，1可用
     *
     * @return status - 0不可以，1可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0不可以，1可用
     *
     * @param status 0不可以，1可用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

	public BigDecimal getAbonusRate() {
		return abonusRate;
	}

	public void setAbonusRate(BigDecimal abonusRate) {
		this.abonusRate = abonusRate;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
    
    
}