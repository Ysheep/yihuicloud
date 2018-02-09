package com.qzdsoft.eshop.vo.goods;

import java.util.ArrayList;
import java.util.List;

public class GoodsParamInfo {
	
	private Integer goodsId;
	
	private List<GoodsParamEditInfo> items = new ArrayList<GoodsParamEditInfo>();
	
	private List<Integer> ids;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public List<GoodsParamEditInfo> getItems() {
		return items;
	}

	public void setItems(List<GoodsParamEditInfo> items) {
		this.items = items;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	
	

}
