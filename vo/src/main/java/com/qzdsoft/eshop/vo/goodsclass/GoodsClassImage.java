package com.qzdsoft.eshop.vo.goodsclass;

import java.util.ArrayList;
import java.util.List;

public class GoodsClassImage {
	
	public Integer code;//键
	public String value;//值
	public String imageurl;
	
	private List<GoodsClassImage> childs = new ArrayList<GoodsClassImage>();
	
	public GoodsClassImage(Integer code, String value, String imageurl) {
		this.code = code;
		this.value = value;
		this.imageurl = imageurl;
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
	public List<GoodsClassImage> getChilds() {
		return childs;
	}
	public void setChilds(List<GoodsClassImage> childs) {
		this.childs = childs;
	}
	
	
	
}
