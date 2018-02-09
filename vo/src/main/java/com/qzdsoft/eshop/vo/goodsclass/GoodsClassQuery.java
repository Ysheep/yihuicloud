/**
 * 
 */
package com.qzdsoft.eshop.vo.goodsclass;

import com.qzdsoft.vo.BaseTableQueryInfo;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月14日
 * @see
 * @since 1.0
 */
public class GoodsClassQuery extends BaseTableQueryInfo{

	private String name;
	
	private Integer status;
	
	private Integer classId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}
