package com.qzdsoft.eshop.domain.goodsclass;

import javax.persistence.*;

@Table(name = "goods_properties_spec")
public class GoodsPropertiesSpec {
    @Id
    @Column(name = "spec_id")
    private Integer specId;

    /**
     * 属性id
     */
    @Column(name = "property_id")
    private Integer propertyId;

    /**
     * 值
     */
    private String name;

    /**
     * 状态 0 正常 1删除
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

    /**
     * @return spec_id
     */
    public Integer getSpecId() {
        return specId;
    }

    /**
     * @param specId
     */
    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    /**
     * 获取属性id
     *
     * @return property_id - 属性id
     */
    public Integer getPropertyId() {
        return propertyId;
    }

    /**
     * 设置属性id
     *
     * @param propertyId 属性id
     */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * 获取值
     *
     * @return name - 值
     */
    public String getName() {
        return name;
    }

    /**
     * 设置值
     *
     * @param name 值
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取状态 0 正常 1删除
     *
     * @return delete_status - 状态 0 正常 1删除
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置状态 0 正常 1删除
     *
     * @param deleteStatus 状态 0 正常 1删除
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}