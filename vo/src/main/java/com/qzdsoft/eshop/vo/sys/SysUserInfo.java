package com.qzdsoft.eshop.vo.sys;

import java.math.BigDecimal;

import com.qzdsoft.eshop.domain.SysUser;

public class SysUserInfo extends SysUser {
	private String role;
//	private String isAll;//0:非全部 1：全部
	private String status;//0：正常 1：冻结
	private BigDecimal  taxRate;
	private String parentphone;
	private String parentName;
	private BigDecimal balance;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		if(super.getDeleteStatus()!=null) {
			switch(super.getDeleteStatus()) {
			case 0:
				status = "正常";
				break;
			case 1:
				status = "冻结";
				break;
			}
		}
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getTaxRate() {
		return taxRate!=null?taxRate.setScale(2, BigDecimal.ROUND_FLOOR):BigDecimal.ZERO;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	public String getParentphone() {
		return parentphone;
	}
	public void setParentphone(String parentphone) {
		this.parentphone = parentphone;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public BigDecimal getBalance() {
		return balance!=null?balance.setScale(2, BigDecimal.ROUND_FLOOR):BigDecimal.ZERO;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
