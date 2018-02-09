package com.qzdsoft.vo;

import java.io.Serializable;
import java.util.List;

/**
 * A vo representing a jQgrid's jsonReader property. 
 */
public class JqgridResponse<T extends Serializable> {

	/**
	 * Current page
	 */
	private Long page;
	
	/**
	 * Total pages
	 */
	private Long total;
	
	/**
	 * Total number of records
	 */
	private Long records;
	
	/**
	 * Contains the actual data
	 */
	private List<T> rows;

	public JqgridResponse() {}
	
	public JqgridResponse(Long page, Long total, Long records,
			List<T> rows) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
		this.rows = rows;
	}

	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getRecords() {
		return records;
	}
	public void setRecords(Long records) {
		this.records = records;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "JqgridResponse [page=" + page + ", total=" + total
				+ ", records=" + records + "]";
	}
}