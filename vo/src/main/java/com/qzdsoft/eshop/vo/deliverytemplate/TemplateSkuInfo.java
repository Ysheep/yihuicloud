package com.qzdsoft.eshop.vo.deliverytemplate;

import java.util.List;

public class TemplateSkuInfo {
	
	private Integer skuId;
	private Integer templateId;
	private Integer carryType;
	private Integer pricingMethod;
	private List<CarryWayInfo> carryWays;
	
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public List<CarryWayInfo> getCarryWays() {
		return carryWays;
	}
	public void setCarryWays(List<CarryWayInfo> carryWays) {
		this.carryWays = carryWays;
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
	
	
}
