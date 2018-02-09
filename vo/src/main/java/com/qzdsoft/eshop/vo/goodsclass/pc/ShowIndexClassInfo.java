package com.qzdsoft.eshop.vo.goodsclass.pc;

import java.util.ArrayList;
import java.util.List;

import com.qzdsoft.eshop.vo.goods.pc.GoodsListInfo;

public class ShowIndexClassInfo {
	
	public Integer code;//键
	public String value;//值
	public String imageurl;
	
	private List<GoodsListInfo> goods = new ArrayList<GoodsListInfo>();
	

	public List<GoodsListInfo> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsListInfo> goods) {
		this.goods = goods;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	
}
