package com.qzdsoft.eshop.vo.orders.pc;

import java.math.BigDecimal;

public class GoodsOrderInfo {
	
	private Integer skuId;
	private Integer goodsId;
	private Integer num;
	private String goodName;
	private BigDecimal price;
	private BigDecimal receivableTotal;//应收 单价*数量
	private BigDecimal actualTotal;//实收
	private String specs;
	private String image;
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
	public BigDecimal getReceivableTotal() {
		return receivableTotal;
	}
	public void setReceivableTotal(BigDecimal receivableTotal) {
		this.receivableTotal = receivableTotal;
	}
	public BigDecimal getActualTotal() {
		return actualTotal;
	}
	public void setActualTotal(BigDecimal actualTotal) {
		this.actualTotal = actualTotal;
	}
	
	
}
