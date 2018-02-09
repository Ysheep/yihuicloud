package com.qzdsoft.eshop.vo.area;

import java.util.ArrayList;
import java.util.List;

public class Provinces {
	private Integer areaId;

    private String name;

    private Integer pid;

    private Integer level;
	private List<Citys> citys = new ArrayList<Citys>();

	public List<Citys> getCitys() {
		return citys;
	}

	public void setCitys(List<Citys> citys) {
		this.citys = citys;
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
		return "Provinces [areaId=" + areaId + ", name=" + name + ", pid=" + pid + ", level=" + level + ", citys="
				+ citys + "]";
	}
	
}
