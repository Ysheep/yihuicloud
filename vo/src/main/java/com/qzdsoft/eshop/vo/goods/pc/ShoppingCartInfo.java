package com.qzdsoft.eshop.vo.goods.pc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * 购物车
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2018年1月30日
 * @see
 * @since 1.0
 */
public class ShoppingCartInfo {
	
	private Integer totalNum;
	private BigDecimal totalPeice;
	private List<ShoppingCartItemInfo> items = new ArrayList<ShoppingCartItemInfo>();
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public BigDecimal getTotalPeice() {
		return totalPeice;
	}
	public void setTotalPeice(BigDecimal totalPeice) {
		this.totalPeice = totalPeice;
	}
	public List<ShoppingCartItemInfo> getItems() {
		return items;
	}
	public void setItems(List<ShoppingCartItemInfo> items) {
		this.items = items;
	}
	
	
	
}
