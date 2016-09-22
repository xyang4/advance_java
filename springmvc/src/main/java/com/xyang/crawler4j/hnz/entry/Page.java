package com.xyang.crawler4j.hnz.entry;

public class Page {
	private int currentPage;// 当前页
	private int total;// 总记录数
	private int numPerPage;// 每页多少条

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
}
