package com.qzdsoft.eshop.vo.advertisement;

import com.qzdsoft.vo.BaseTableQueryInfo;

public class AdvertisementQueryInfo extends BaseTableQueryInfo {
	
	private String goodsName;
	private String stime;
	private String etime;
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	
	
}
