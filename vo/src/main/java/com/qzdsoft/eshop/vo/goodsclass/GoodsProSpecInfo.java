package com.qzdsoft.eshop.vo.goodsclass;

import java.util.ArrayList;
import java.util.List;

public class GoodsProSpecInfo {

	private Integer propertyId;
	
	private List<GoodsSpecEditInfo> items = new ArrayList<GoodsSpecEditInfo>();
	
	private List<Integer> ids;

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public List<GoodsSpecEditInfo> getItems() {
		return items;
	}

	public void setItems(List<GoodsSpecEditInfo> items) {
		this.items = items;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	
}
