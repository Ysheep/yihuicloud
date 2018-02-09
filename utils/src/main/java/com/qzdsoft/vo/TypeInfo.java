package com.qzdsoft.vo;

public class TypeInfo {
	
	public String code;//键
	public String value;//值
	public TypeInfo() {
		
	}
	public TypeInfo(String code, String value) {
		super();
		this.code = code;
		this.value = value;
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
	
}
