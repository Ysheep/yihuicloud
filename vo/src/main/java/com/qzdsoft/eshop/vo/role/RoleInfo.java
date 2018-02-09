/**
 * 
 */
package com.qzdsoft.eshop.vo.role;

import com.qzdsoft.eshop.domain.Role;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月14日
 * @see
 * @since 1.0
 */
public class RoleInfo extends Role{
	
	private String isFunction;

	public String getIsFunction() {
		if(super.getAllFunction()!=null) {
			switch(super.getAllFunction()) {
			case 1:
				isFunction = "是";
				break;
			case 0:
				isFunction = "否";
			}
		}
		return isFunction;
	}

	public void setIsFunction(String isFunction) {
		this.isFunction = isFunction;
	}
	
}
