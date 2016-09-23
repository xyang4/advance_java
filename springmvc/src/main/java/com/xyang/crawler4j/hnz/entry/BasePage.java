package com.xyang.crawler4j.hnz.entry;

public class BasePage {
	protected int currentNo;// 当前页
	protected int total;// 总记录数
	protected int numPerPage;// 每页多少条

	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public int getCurrentNo() {
		return currentNo;
	}

	public void setCurrentNo(int currentNo) {
		this.currentNo = currentNo;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
}
