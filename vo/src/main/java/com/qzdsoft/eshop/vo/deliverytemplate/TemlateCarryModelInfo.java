package com.qzdsoft.eshop.vo.deliverytemplate;

import java.util.List;

public class TemlateCarryModelInfo {
	
	private Integer goodsId;
	private Integer goodsNum;
	private Integer templateId;
	private Integer carryType;
	private Integer pricingMethod;
	private Integer freeDelivery;
	private List<CarryWayInfo> carryWays;
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public Integer getCarryType() {
		return carryType;
	}
	public void setCarryType(Integer carryType) {
		this.carryType = carryType;
	}
	public Integer getPricingMethod() {
		return pricingMethod;
	}
	public void setPricingMethod(Integer pricingMethod) {
		this.pricingMethod = pricingMethod;
	}
	public Integer getFreeDelivery() {
		return freeDelivery;
	}
	public void setFreeDelivery(Integer freeDelivery) {
		this.freeDelivery = freeDelivery;
	}
	public List<CarryWayInfo> getCarryWays() {
		return carryWays;
	}
	public void setCarryWays(List<CarryWayInfo> carryWays) {
		this.carryWays = carryWays;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	
}
