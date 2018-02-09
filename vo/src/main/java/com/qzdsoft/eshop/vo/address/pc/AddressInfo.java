package com.qzdsoft.eshop.vo.address.pc;

public class AddressInfo {
	
	private Integer addressId;
	private String linkMan;
	private String mobile;
	private String address;
	private Integer isDefault;
    private Integer provinceId;
    private String provinceStr;
    private Integer cityId;
    private String cityStr;
    private Integer districtId;
    private String districtStr;
    private String zip;
	
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getProvinceStr() {
		return provinceStr;
	}
	public void setProvinceStr(String provinceStr) {
		this.provinceStr = provinceStr;
	}
	public String getCityStr() {
		return cityStr;
	}
	public void setCityStr(String cityStr) {
		this.cityStr = cityStr;
	}
	public String getDistrictStr() {
		return districtStr;
	}
	public void setDistrictStr(String districtStr) {
		this.districtStr = districtStr;
	}
}
