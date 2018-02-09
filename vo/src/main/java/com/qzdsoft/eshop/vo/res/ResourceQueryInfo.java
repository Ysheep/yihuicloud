package com.qzdsoft.eshop.vo.res;

import com.qzdsoft.vo.BaseTableQueryInfo;
/**
 * 资源查询vo对象
 * @author pc-20170422
 *
 */
public class ResourceQueryInfo extends BaseTableQueryInfo{
	
	private String name;
	private String url;
	private String type;
	public String getName() {
		if(null != name && "" != name) {
			name = name.trim();
		}
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
	}
	public String getUrl() {
		if(null != url && "" != url) {
			url =  url.trim();
		}
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ResourceQueryInfo [name=" + name + ", url=" + url + ", type=" + type + "]";
	}
	
	
}
