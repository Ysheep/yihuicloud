package com.qzdsoft.eshop.vo.goods;

import java.util.List;

import com.qzdsoft.vo.TypeInfo;

public class GoodsPropertiesSpecInfo {

	private Integer propertyId;
	
	private String propertyName;
	
	private List<TypeInfo> spec;

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public List<TypeInfo> getSpec() {
		return spec;
	}

	public void setSpec(List<TypeInfo> spec) {
		this.spec = spec;
	}
	
	
}
