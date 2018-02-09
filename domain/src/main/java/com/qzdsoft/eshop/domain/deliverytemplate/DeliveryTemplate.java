package com.qzdsoft.eshop.domain.deliverytemplate;

import java.util.Date;
import javax.persistence.*;

@Table(name = "delivery_template")
public class DeliveryTemplate {
    @Id
    @Column(name = "template_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer templateId;

    private String name;

    private String addr;

    @Column(name = "free_delivery")
    private Short freeDelivery;

    /**
     * 计价方式(1:按件 2:按重量 3:按体积)
     */
    @Column(name = "pricing_method")
    private Short pricingMethod;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "delete_status")
    private Short deleteStatus;

    @Column(name = "is_incl_postage_byif")
    private Short isInclPostageByif;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * @return free_delivery
     */
    public Short getFreeDelivery() {
        return freeDelivery;
    }

    /**
     * @param freeDelivery
     */
    public void setFreeDelivery(Short freeDelivery) {
        this.freeDelivery = freeDelivery;
    }

    /**
     * 获取计价方式(1:按件 2:按重量 3:按体积)
     *
     * @return pricing_method - 计价方式(1:按件 2:按重量 3:按体积)
     */
    public Short getPricingMethod() {
        return pricingMethod;
    }

    /**
     * 设置计价方式(1:按件 2:按重量 3:按体积)
     *
     * @param pricingMethod 计价方式(1:按件 2:按重量 3:按体积)
     */
    public void setPricingMethod(Short pricingMethod) {
        this.pricingMethod = pricingMethod;
    }

    /**
     * @return add_time
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return delete_status
     */
    public Short getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * @param deleteStatus
     */
    public void setDeleteStatus(Short deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * @return is_incl_postage_byif
     */
    public Short getIsInclPostageByif() {
        return isInclPostageByif;
    }

    /**
     * @param isInclPostageByif
     */
    public void setIsInclPostageByif(Short isInclPostageByif) {
        this.isInclPostageByif = isInclPostageByif;
    }

	@Override
	public String toString() {
		return "DeliveryTemplate [templateId=" + templateId + ", name=" + name + ", addr=" + addr + ", freeDelivery="
				+ freeDelivery + ", pricingMethod=" + pricingMethod + ", addTime=" + addTime + ", deleteStatus="
				+ deleteStatus + ", isInclPostageByif=" + isInclPostageByif + "]";
	}
    
    
}