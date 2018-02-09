package com.qzdsoft.eshop.vo.goods.pc;

import java.math.BigDecimal;

public class GoodsSearchInfo{
	
	 private Integer goodsId;
	 
	 private String name;
	 
	 private BigDecimal price;
	 
	 private String imageUrl;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	 
	 

}
