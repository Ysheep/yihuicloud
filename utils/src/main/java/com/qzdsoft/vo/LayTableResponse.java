package com.qzdsoft.vo;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;

public class LayTableResponse<T> {
	
	private int code;//code
	
	private String msg="";//消息
	
	private long count;//数据总数
	
	private List<T> data = new ArrayList<T>();
	
	public LayTableResponse(List<T> data) {
		this.data = data;
		if(data instanceof Page) {
			Page page = (Page)data;
			this.count = page.getTotal();
			this.code = 0;
		}
	}
	
	public static LayTableResponse nullDataPage() {
	    return new LayTableResponse<>(new ArrayList<>());
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
