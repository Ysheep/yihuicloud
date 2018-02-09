package com.qzdsoft.eshop.vo.deliverytemplate;

import java.math.BigDecimal;

public class CarryWayEditInfo {
	private Integer carryId;
	private String areaId;//地区
	private Integer isDefault;//是否默认
	private Integer carryWay;//运送方式
	private BigDecimal firstAmount;//首费
	private BigDecimal secondAmount;//续费
	private BigDecimal firstSum;//首件、重、积
	private BigDecimal secondSum;//续件、重、积
	
	public Integer getCarryId() {
		return carryId;
	}
	public void setCarryId(Integer carryId) {
		this.carryId = carryId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getCarryWay() {
		return carryWay;
	}
	public void setCarryWay(Integer carryWay) {
		this.carryWay = carryWay;
	}
	public BigDecimal getFirstAmount() {
		return firstAmount;
	}
	public void setFirstAmount(BigDecimal firstAmount) {
		this.firstAmount = firstAmount;
	}
	public BigDecimal getSecondAmount() {
		return secondAmount;
	}
	public void setSecondAmount(BigDecimal secondAmount) {
		this.secondAmount = secondAmount;
	}
	public BigDecimal getFirstSum() {
		return firstSum;
	}
	public void setFirstSum(BigDecimal firstSum) {
		this.firstSum = firstSum;
	}
	public BigDecimal getSecondSum() {
		return secondSum;
	}
	public void setSecondSum(BigDecimal secondSum) {
		this.secondSum = secondSum;
	}
	
	
}
