package com.qzdsoft.eshop.vo.res;

import java.io.Serializable;
/**
 * ztree树数据结构对象
 * @author pc-20170422
 *
 */
public class ZtreeNode implements Serializable {
	private Integer id;
	private Integer pId;
	private String name;
	private String open = "false";
	private String checked ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
	public ZtreeNode() {
	}
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		if(checked != null) {
			this.checked = "true";
		}else{
			this.checked = "false";
		}
		
	}
	@Override
	public String toString() {
		return "ZtreeNode [id=" + id + ", pId=" + pId + ", name=" + name + ", open=" + open + ", checked=" + checked
				+ "]";
	}
	
	
}
