package com.qzdsoft.eshop.vo.res;

import com.qzdsoft.eshop.domain.Resource;

public class ResourceInfo extends Resource {
	private String pName;
	private String typeName;
	

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getTypeName() {
		if(super.getType()!=null) {
			switch(super.getType()){
			case 0:
				typeName = "目录菜单";
				break;
			case 1:
				typeName = "功能菜单";
			}
		}
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
