package com.qzdsoft.vo;

public class BaseTableQueryInfo {
	
    private boolean first;//是否初始化时查询，初始化查询返回无数据
    
	private int page;//查询页码
	
	private int limit;//查询行数

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

    public boolean isFirst()
    {
        return first;
    }

    public void setFirst(boolean first)
    {
        this.first = first;
    }

	
}
