package com.qzdsoft.eshop.domain.goods;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="goods_param")
public class GoodsParams {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id")
	private Integer paramId;
	
	@Column(name = "goods_id")
	private Integer goodsId;
	
	private String name;
	
	private String value;
	
	@Column(name = "delete_status")
	private Integer deleteStatus;

	public Integer getParamId() {
		return paramId;
	}

	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	
}
