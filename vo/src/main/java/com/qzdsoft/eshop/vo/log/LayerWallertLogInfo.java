package com.qzdsoft.eshop.vo.log;

import java.util.List;

public class LayerWallertLogInfo {
	
	private Integer page;//当前页
	
	private Integer limit;
	
	private Integer pageTotal;//总页数
	
	private List<WalletLogInfo> wallet;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public List<WalletLogInfo> getWallet() {
		return wallet;
	}

	public void setWallet(List<WalletLogInfo> wallet) {
		this.wallet = wallet;
	}
	
	

}
