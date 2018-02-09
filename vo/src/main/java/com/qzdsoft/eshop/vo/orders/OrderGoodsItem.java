package com.qzdsoft.eshop.vo.orders;

import java.math.BigDecimal;

public class OrderGoodsItem {
	
	private Integer skuId;
	private Integer goodsId;
	private Integer num;
	private String goodName;
	private BigDecimal price;
	private String specs;
	private String image;
	private BigDecimal totalMoney;
	private BigDecimal endPayMoney;
	
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public BigDecimal getEndPayMoney() {
		return endPayMoney;
	}
	public void setEndPayMoney(BigDecimal endPayMoney) {
		this.endPayMoney = endPayMoney;
	}
	
	
}
