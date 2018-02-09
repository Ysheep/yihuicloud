package com.qzdsoft.eshop.vo.area;

public class DistrictList{
	
	private Integer areaId;

    private String name;

    private Integer pid;

    private Integer level;

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
		return "DistrictList [areaId=" + areaId + ", name=" + name + ", pid=" + pid + ", level=" + level + "]";
	}

	
    
    
}
