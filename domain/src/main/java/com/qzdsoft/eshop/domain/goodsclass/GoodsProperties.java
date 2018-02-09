package com.qzdsoft.eshop.domain.goodsclass;

import javax.persistence.*;

@Table(name = "goods_properties")
public class GoodsProperties {
    @Id
    @Column(name = "property_id")
    private Integer propertyId;

    @Column(name = "class_id")
    private Integer classId;

    private String name;

    @Column(name = "delete_status")
    private Integer deleteStatus;

    /**
     * @return property_id
     */
    public Integer getPropertyId() {
        return propertyId;
    }

    /**
     * @param propertyId
     */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * @return class_id
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * @param classId
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
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
     * @return delete_status
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * @param deleteStatus
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}