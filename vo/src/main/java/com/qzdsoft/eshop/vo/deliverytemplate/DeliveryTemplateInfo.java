package com.qzdsoft.eshop.vo.deliverytemplate;

import java.util.List;

public class DeliveryTemplateInfo{
	private Integer templateId;
    private String name;
    private String addr;
    private Short freeDelivery;
    private Short pricingMethod;
    private String addTime;
    private Short deleteStatus;
    private Short isInclPostageByif;
	private String pricingName;
	private List<CarryWayInfo> carryWays;
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Short getFreeDelivery() {
		return freeDelivery;
	}
	public void setFreeDelivery(Short freeDelivery) {
		this.freeDelivery = freeDelivery;
	}
	public Short getPricingMethod() {
		return pricingMethod;
	}
	public void setPricingMethod(Short pricingMethod) {
		this.pricingMethod = pricingMethod;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Short getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(Short deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public Short getIsInclPostageByif() {
		return isInclPostageByif;
	}
	public void setIsInclPostageByif(Short isInclPostageByif) {
		this.isInclPostageByif = isInclPostageByif;
	}
	public String getPricingName() {
		return pricingName;
	}
	public void setPricingName(String pricingName) {
		this.pricingName = pricingName;
	}
	public List<CarryWayInfo> getCarryWays() {
		return carryWays;
	}
	public void setCarryWays(List<CarryWayInfo> carryWays) {
		this.carryWays = carryWays;
	}
	@Override
	public String toString() {
		return "DeliveryTemplateInfo [templateId=" + templateId + ", name=" + name + ", addr=" + addr
				+ ", freeDelivery=" + freeDelivery + ", pricingMethod=" + pricingMethod + ", addTime=" + addTime
				+ ", deleteStatus=" + deleteStatus + ", isInclPostageByif=" + isInclPostageByif + ", pricingName="
				+ pricingName + ", carryWays=" + carryWays + "]";
	}
	
	
	
	
	
}
