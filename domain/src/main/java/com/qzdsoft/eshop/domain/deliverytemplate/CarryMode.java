package com.qzdsoft.eshop.domain.deliverytemplate;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "carry_mode")
public class CarryMode {
    @Id
    @Column(name = "carry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carryId;

    @Column(name = "template_id")
    private Integer templateId;

    /**
     * 运送地区(存id,格式为'省-市-区',以'|'分隔)
     */
    private String region;

    @Column(name = "first_piece")
    private Integer firstPiece;

    @Column(name = "first_weight")
    private BigDecimal firstWeight;

    @Column(name = "first_bulk")
    private BigDecimal firstBulk;

    @Column(name = "first_amount")
    private BigDecimal firstAmount;

    @Column(name = "second_piece")
    private Integer secondPiece;

    @Column(name = "second_weight")
    private BigDecimal secondWeight;

    @Column(name = "second_bulk")
    private BigDecimal secondBulk;

    @Column(name = "second_amount")
    private BigDecimal secondAmount;

    @Column(name = "carry_way")
    private Integer carryWay;

    @Column(name = "is_default")
    private Short isDefault;

    /**
     * @return carry_id
     */
    public Integer getCarryId() {
        return carryId;
    }

    /**
     * @param carryId
     */
    public void setCarryId(Integer carryId) {
        this.carryId = carryId;
    }

    /**
     * @return template_id
     */
    public Integer getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId
     */
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    /**
     * 获取运送地区(存id,格式为'省-市-区',以'|'分隔)
     *
     * @return region - 运送地区(存id,格式为'省-市-区',以'|'分隔)
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置运送地区(存id,格式为'省-市-区',以'|'分隔)
     *
     * @param region 运送地区(存id,格式为'省-市-区',以'|'分隔)
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return first_piece
     */
    public Integer getFirstPiece() {
        return firstPiece;
    }

    /**
     * @param firstPiece
     */
    public void setFirstPiece(Integer firstPiece) {
        this.firstPiece = firstPiece;
    }

    /**
     * @return first_weight
     */
    public BigDecimal getFirstWeight() {
        return firstWeight;
    }

    /**
     * @param firstWeight
     */
    public void setFirstWeight(BigDecimal firstWeight) {
        this.firstWeight = firstWeight;
    }

    /**
     * @return first_bulk
     */
    public BigDecimal getFirstBulk() {
        return firstBulk;
    }

    /**
     * @param firstBulk
     */
    public void setFirstBulk(BigDecimal firstBulk) {
        this.firstBulk = firstBulk;
    }

    /**
     * @return first_amount
     */
    public BigDecimal getFirstAmount() {
        return firstAmount;
    }

    /**
     * @param firstAmount
     */
    public void setFirstAmount(BigDecimal firstAmount) {
        this.firstAmount = firstAmount;
    }

    /**
     * @return second_piece
     */
    public Integer getSecondPiece() {
        return secondPiece;
    }

    /**
     * @param secondPiece
     */
    public void setSecondPiece(Integer secondPiece) {
        this.secondPiece = secondPiece;
    }

    /**
     * @return second_weight
     */
    public BigDecimal getSecondWeight() {
        return secondWeight;
    }

    /**
     * @param secondWeight
     */
    public void setSecondWeight(BigDecimal secondWeight) {
        this.secondWeight = secondWeight;
    }

    /**
     * @return second_bulk
     */
    public BigDecimal getSecondBulk() {
        return secondBulk;
    }

    /**
     * @param secondBulk
     */
    public void setSecondBulk(BigDecimal secondBulk) {
        this.secondBulk = secondBulk;
    }

    /**
     * @return second_amount
     */
    public BigDecimal getSecondAmount() {
        return secondAmount;
    }

    /**
     * @param secondAmount
     */
    public void setSecondAmount(BigDecimal secondAmount) {
        this.secondAmount = secondAmount;
    }

    /**
     * @return carry_way
     */
    public Integer getCarryWay() {
        return carryWay;
    }

    /**
     * @param carryWay
     */
    public void setCarryWay(Integer carryWay) {
        this.carryWay = carryWay;
    }

    /**
     * @return is_default
     */
    public Short getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     */
    public void setIsDefault(Short isDefault) {
        this.isDefault = isDefault;
    }

	@Override
	public String toString() {
		return "CarryMode [carryId=" + carryId + ", templateId=" + templateId + ", region=" + region + ", firstPiece="
				+ firstPiece + ", firstWeight=" + firstWeight + ", firstBulk=" + firstBulk + ", firstAmount="
				+ firstAmount + ", secondPiece=" + secondPiece + ", secondWeight=" + secondWeight + ", secondBulk="
				+ secondBulk + ", secondAmount=" + secondAmount + ", carryWay=" + carryWay + ", isDefault=" + isDefault
				+ "]";
	}
    
    
}