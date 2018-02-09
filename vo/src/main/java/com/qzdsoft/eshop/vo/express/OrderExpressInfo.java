/**
 * 
 */
package com.qzdsoft.eshop.vo.express;

import java.util.List;
import java.util.Map;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年12月26日
 * @see
 * @since 1.0
 */
public class OrderExpressInfo {
	
	private Integer expressId;
	private String expressNo;
	private String delivertTime;
	private String expressName;
	private String expressCode;
	private List<Map<String,String>> traces;
	public Integer getExpressId() {
		return expressId;
	}
	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getDelivertTime() {
		return delivertTime;
	}
	public void setDelivertTime(String delivertTime) {
		this.delivertTime = delivertTime;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public List<Map<String, String>> getTraces() {
		return traces;
	}
	public void setTraces(List<Map<String, String>> traces) {
		this.traces = traces;
	}
	
	
}
