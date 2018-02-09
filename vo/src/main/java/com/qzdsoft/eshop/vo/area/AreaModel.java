package com.qzdsoft.eshop.vo.area;

import java.util.ArrayList;
import java.util.List;

public class AreaModel{
	private Integer areaId;
    private String name;
    private Integer pid;
    private Integer level;
	private List<Provinces> provinces = new ArrayList<Provinces>();
	
	public List<Provinces> getProvinces() {
		return provinces;
	}
	public void setProvinces(List<Provinces> provinces) {
		this.provinces = provinces;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "AreaModel [areaId=" + areaId + ", name=" + name + ", pid=" + pid + ", level=" + level + ", provinces="
				+ provinces + "]";
	}
	
	
	
}
