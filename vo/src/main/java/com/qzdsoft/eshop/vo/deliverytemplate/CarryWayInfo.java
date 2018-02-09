package com.qzdsoft.eshop.vo.deliverytemplate;

import java.math.BigDecimal;

import com.qzdsoft.vo.CarryWay;

public class CarryWayInfo{
	
	 	private Integer carryId;

	    private Integer templateId;

	    /**
	     * 运送地区(存id,格式为'省-市-区',以'|'分隔)
	     */
	    private String region;

	    private Integer firstPiece;

	    private BigDecimal firstWeight;

	    private BigDecimal firstBulk;

	    private BigDecimal firstAmount;

	    private Integer secondPiece;

	    private BigDecimal secondWeight;

	    private BigDecimal secondBulk;

	    private BigDecimal secondAmount;

	    private Integer carryWay;

	    private Short isDefault;
	
	    private String RegionList;
	    private String carryName;
		public Integer getCarryId() {
			return carryId;
		}
		public void setCarryId(Integer carryId) {
			this.carryId = carryId;
		}
		public Integer getTemplateId() {
			return templateId;
		}
		public void setTemplateId(Integer templateId) {
			this.templateId = templateId;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public Integer getFirstPiece() {
			return firstPiece;
		}
		public void setFirstPiece(Integer firstPiece) {
			this.firstPiece = firstPiece;
		}
		public BigDecimal getFirstWeight() {
			return firstWeight;
		}
		public void setFirstWeight(BigDecimal firstWeight) {
			this.firstWeight = firstWeight;
		}
		public BigDecimal getFirstBulk() {
			return firstBulk;
		}
		public void setFirstBulk(BigDecimal firstBulk) {
			this.firstBulk = firstBulk;
		}
		public BigDecimal getFirstAmount() {
			return firstAmount;
		}
		public void setFirstAmount(BigDecimal firstAmount) {
			this.firstAmount = firstAmount;
		}
		public Integer getSecondPiece() {
			return secondPiece;
		}
		public void setSecondPiece(Integer secondPiece) {
			this.secondPiece = secondPiece;
		}
		public BigDecimal getSecondWeight() {
			return secondWeight;
		}
		public void setSecondWeight(BigDecimal secondWeight) {
			this.secondWeight = secondWeight;
		}
		public BigDecimal getSecondBulk() {
			return secondBulk;
		}
		public void setSecondBulk(BigDecimal secondBulk) {
			this.secondBulk = secondBulk;
		}
		public BigDecimal getSecondAmount() {
			return secondAmount;
		}
		public void setSecondAmount(BigDecimal secondAmount) {
			this.secondAmount = secondAmount;
		}
		public Integer getCarryWay() {
			return carryWay;
		}
		public void setCarryWay(Integer carryWay) {
			if(carryWay == Integer.parseInt(CarryWay.EMS_CODE)) {
				this.carryName = CarryWay.EMS.getValue();
			}else if(carryWay == Integer.parseInt(CarryWay.EXPRESS_CODE)) {
				this.carryName = CarryWay.EXPRESS.getValue();
			}else{
				this.carryName = CarryWay.POSTAGE.getValue();
			}
			this.carryWay = carryWay;
		}
		public Short getIsDefault() {
			return isDefault;
		}
		public void setIsDefault(Short isDefault) {
			this.isDefault = isDefault;
		}
		public String getRegionList() {
			return RegionList;
		}
		public void setRegionList(String regionList) {
			RegionList = regionList;
		}
		public String getCarryName() {
			return carryName;
		}
		public void setCarryName(String carryName) {
			this.carryName = carryName;
		}
		@Override
		public String toString() {
			return "CarryWayInfo [carryId=" + carryId + ", templateId=" + templateId + ", region=" + region
					+ ", firstPiece=" + firstPiece + ", firstWeight=" + firstWeight + ", firstBulk=" + firstBulk
					+ ", firstAmount=" + firstAmount + ", secondPiece=" + secondPiece + ", secondWeight=" + secondWeight
					+ ", secondBulk=" + secondBulk + ", secondAmount=" + secondAmount + ", carryWay=" + carryWay
					+ ", isDefault=" + isDefault + ", RegionList=" + RegionList + ", carryName=" + carryName + "]";
		}
	
															
	
	
}
