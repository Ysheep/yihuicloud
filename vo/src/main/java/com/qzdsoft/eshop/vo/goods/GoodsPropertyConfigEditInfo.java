package com.qzdsoft.eshop.vo.goods;

import java.util.List;

import com.qzdsoft.eshop.vo.goods.pc.GoodsSpecInfo;

public class GoodsPropertyConfigEditInfo {
	
	private Integer goodsId;
	private List<GoodsSpecInfo> specs;
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public List<GoodsSpecInfo> getSpecs() {
		return specs;
	}
	public void setSpecs(List<GoodsSpecInfo> specs) {
		this.specs = specs;
	}
	
	
	
	
}
