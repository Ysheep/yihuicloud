package com.qzdsoft.eshop.vo.sys;

import java.util.List;

import com.qzdsoft.eshop.domain.DictionaryInfo;

public class DictionaryEditInfo {

	private String typecode;
	
	private String typevalue;
	
	private List<Integer> ids;
	
	private List<DictionaryInfo> details;

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getTypevalue() {
		return typevalue;
	}

	public void setTypevalue(String typevalue) {
		this.typevalue = typevalue;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<DictionaryInfo> getDetails() {
		return details;
	}

	public void setDetails(List<DictionaryInfo> details) {
		this.details = details;
	}
	
	
	
}
