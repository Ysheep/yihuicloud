package com.qzdsoft.eshop.vo.goods;

import java.math.BigDecimal;

public class GoodsAddDetail {
	
	private Integer skuId;
	//属性配置
	private String specIds;
	//价格
	private BigDecimal price;
	//图片地址
	private String picturePath;
	
	
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public String getSpecIds() {
		return specIds;
	}
	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	

}
