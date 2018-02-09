package com.qzdsoft.eshop.vo.goods;

import com.qzdsoft.vo.BaseTableQueryInfo;

public class GoodsQueryInfo extends BaseTableQueryInfo{
private String name;
	
	private Integer isRecommend;
	
	private Integer isPackage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(Integer isPackage) {
		this.isPackage = isPackage;
	}
}
