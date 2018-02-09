package com.qzdsoft.eshop.vo.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsAddInfo {
	
	private Integer goodsId;
	
	private String name;
	
	private String descript;
	
	private String decode;
	
	private String classId;
	
	private String initiprice;
	
	private Integer pictureMainId;
	
	private Integer templateId;
	
	private Integer vedio;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	private Integer isPackage;//是否套餐
	
	private Integer systemId;
	
	private List<Integer> ids;
	
	private List<GoodsAddDetail> items = new ArrayList<GoodsAddDetail>();

	private String detail;
	
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getDecode() {
		return decode;
	}

	public void setDecode(String decode) {
		this.decode = decode;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getInitiprice() {
		return initiprice;
	}

	public void setInitiprice(String initiprice) {
		this.initiprice = initiprice;
	}

	public List<GoodsAddDetail> getItems() {
		return items;
	}

	public void setItems(List<GoodsAddDetail> items) {
		this.items = items;
	}

	public Integer getPictureMainId() {
		return pictureMainId;
	}

	public void setPictureMainId(Integer pictureMainId) {
		this.pictureMainId = pictureMainId;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(Integer isPackage) {
		this.isPackage = isPackage;
	}

	public Integer getVedio() {
		return vedio;
	}

	public void setVedio(Integer vedio) {
		this.vedio = vedio;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	
	
}
