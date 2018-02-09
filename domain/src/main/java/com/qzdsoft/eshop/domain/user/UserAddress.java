package com.qzdsoft.eshop.domain.user;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_address")
public class UserAddress {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 详情地址
     */
    @Column(name = "address_info")
    private String addressInfo;

    private String mobile;

    private String phone;

    private String zip;

    @Column(name = "true_name")
    private String trueName;

    @Column(name = "delete_status")
    private Short deleteStatus;

    @Column(name = "add_time")
    private Date addTime;

    /**
     * 省
     */
    @Column(name = "province_id")
    private Integer provinceId;

    /**
     * 市
     */
    @Column(name = "city_id")
    private Integer cityId;

    /**
     * 区/县
     */
    @Column(name = "district_id")
    private Integer districtId;

    /**
     * 是否默认 1默认 0非默认
     */
    private Integer isdefault;

    /**
     * @return address_id
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取详情地址
     *
     * @return address_info - 详情地址
     */
    public String getAddressInfo() {
        return addressInfo;
    }

    /**
     * 设置详情地址
     *
     * @param addressInfo 详情地址
     */
    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return true_name
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * @param trueName
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName;
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
     * 获取省
     *
     * @return province_id - 省
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省
     *
     * @param provinceId 省
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取市
     *
     * @return city_id - 市
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置市
     *
     * @param cityId 市
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取区/县
     *
     * @return district_id - 区/县
     */
    public Integer getDistrictId() {
        return districtId;
    }

    /**
     * 设置区/县
     *
     * @param districtId 区/县
     */
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    /**
     * 获取是否默认 1默认 0非默认
     *
     * @return isdefault - 是否默认 1默认 0非默认
     */
    public Integer getIsdefault() {
        return isdefault;
    }

    /**
     * 设置是否默认 1默认 0非默认
     *
     * @param isdefault 是否默认 1默认 0非默认
     */
    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }
}