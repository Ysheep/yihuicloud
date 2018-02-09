package com.qzdsoft.eshop.vo.goods.pc;

import java.util.Calendar;
import java.util.Date;

import com.qzdsoft.vo.BaseTableQueryInfo;

public class GoodsSearchQueryInfo extends BaseTableQueryInfo{
	
	private String keyWord;//关键字
	private Integer classId;//分类id
	private Integer systemId;//系统类别id
	private Integer isPackage;//是否套餐
	private Date startDate ;
	private Date endDate ;
	
	
	
	public String getKeyWord() {
		if(keyWord!=null && keyWord!="") {
			keyWord = keyWord.trim();
		}
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public Date getStartDate() {
		startDate = Calendar.getInstance().getTime();
		return startDate ;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		endDate  = Calendar.getInstance().getTime();
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	public Integer getIsPackage() {
		return isPackage;
	}
	public void setIsPackage(Integer isPackage) {
		this.isPackage = isPackage;
	}
	
	
}
