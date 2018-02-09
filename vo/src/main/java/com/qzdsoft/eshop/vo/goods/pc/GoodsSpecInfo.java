package com.qzdsoft.eshop.vo.goods.pc;

public class GoodsSpecInfo {
	
	private Integer specImgId;
	private String specName;
	private String imageUrl;
	private Integer accessoryId;
	private Integer specId;
	
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public Integer getSpecImgId() {
		return specImgId;
	}
	public void setSpecImgId(Integer specImgId) {
		this.specImgId = specImgId;
	}
	public Integer getAccessoryId() {
		return accessoryId;
	}
	public void setAccessoryId(Integer accessoryId) {
		this.accessoryId = accessoryId;
	}
}
