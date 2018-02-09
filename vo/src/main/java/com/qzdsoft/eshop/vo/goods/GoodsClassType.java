package com.qzdsoft.eshop.vo.goods;

public class GoodsClassType {
	
	public String code;//键
	public String value;//值
	public Integer type;
	
	
	
	public GoodsClassType(String code, String value, Integer type) {
		super();
		this.code = code;
		this.value = value;
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
