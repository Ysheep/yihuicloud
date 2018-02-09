package com.qzdsoft.eshop.vo.area;

import java.util.ArrayList;
import java.util.List;

public class Citys{
	
	private Integer areaId;

    private String name;

    private Integer pid;

    private Integer level;
    private List<DistrictList> districtList = new ArrayList<DistrictList>();
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
	public List<DistrictList> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<DistrictList> districtList) {
		this.districtList = districtList;
	}

	@Override
	public String toString() {
		return "Citys [areaId=" + areaId + ", name=" + name + ", pid=" + pid + ", level=" + level + ", districtList="
				+ districtList + "]";
	}

	
    
    
}
