package com.qzdsoft.eshop.vo.sys;

import com.qzdsoft.vo.BaseTableQueryInfo;

public class UserQueryInfo extends BaseTableQueryInfo {
	private String sreachKey;

	public String getSreachKey() {
		return sreachKey;
	}

	public void setSreachKey(String sreachKey) {
		this.sreachKey = sreachKey;
	}

	@Override
	public String toString() {
		return "UserQueryInfo [sreachKey=" + sreachKey + "]";
	}
	
}
