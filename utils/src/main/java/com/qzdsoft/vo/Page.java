package com.qzdsoft.vo;

import java.util.List;

public class Page<T> {
	public static final Integer PAGESIZE = 8;
	public Integer perSize = PAGESIZE;// 每页条数
	private Integer nowPager; // 当前页
	private Integer totalPageNum; // 总页数
	private long totalRecordNum; // 总条数
	private List<T> data; // 获取的信息列表
	private boolean firstPage;
	private boolean lastPage;
	
	
	public Page(Integer perSize, Integer nowPager, Integer totalPageNum, long totalRecordNum, List<T> data) {
		this.perSize = perSize;
		this.nowPager = nowPager;
		this.totalPageNum = totalPageNum;
		this.totalRecordNum = totalRecordNum;
		this.data = data;
		this.firstPage = nowPager == 1?true:false;
		this.lastPage = nowPager >= totalPageNum?true:false;
	}


	public Integer getPerSize() {
		return perSize;
	}


	public void setPerSize(Integer perSize) {
		this.perSize = perSize;
	}


	public Integer getNowPager() {
		return nowPager;
	}


	public void setNowPager(Integer nowPager) {
		this.nowPager = nowPager;
	}


	public Integer getTotalPageNum() {
		return totalPageNum;
	}


	public void setTotalPageNum(Integer totalPageNum) {
		this.totalPageNum = totalPageNum;
	}


	public long getTotalRecordNum() {
		return totalRecordNum;
	}


	public void setTotalRecordNum(long totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
	}


	public List<T> getData() {
		return data;
	}


	public void setData(List<T> data) {
		this.data = data;
	}


	public boolean isFirstPage() {
		return firstPage;
	}


	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}


	public boolean isLastPage() {
		return lastPage;
	}


	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	
	

	
	
	
	
//	private List<T> data = new ArrayList<>();
//	private long iTotalDisplayRecords;//实际的总行数,和iTotalRecords保持一致即可
//	private long iTotalRecords;//实际的总行数
//	private String sEcho;
//	private PageRequest pageRequest;
//	private long total;
//
//	public Page( List<T> data, long iTotalDisplayRecords,String sEcho,PageRequest pageRequest) {
//		this.data = data;
//		this.iTotalDisplayRecords = iTotalDisplayRecords;
//		this.iTotalRecords = iTotalDisplayRecords;
//		this.sEcho = sEcho;
//		this.pageRequest = pageRequest;
//		this.total = iTotalDisplayRecords;
//	}
//	public long getTotal(){
//		return total;
//	}
//	public List<T> getData() {
//		return data;
//	}
//
//	public long getiTotalDisplayRecords() {
//		return iTotalDisplayRecords;
//	}
//
//	public long getiTotalRecords() {
//		return iTotalRecords;
//	}
//
//	public String getsEcho() {
//		return sEcho;
//	}
//
//	public int getCurrentPage(){
//		return pageRequest.getPage();
//	}
//
//	public int getPageSize() {
//		return pageRequest.getPageSize();
//	}
//
//	public int getTotalPage() {
//		return getPageSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getPageSize());
//	}
}
