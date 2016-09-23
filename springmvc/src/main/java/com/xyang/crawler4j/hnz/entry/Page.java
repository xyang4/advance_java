package com.xyang.crawler4j.hnz.entry;

public class Page<T> extends BasePage {
	private T entry;

	public T getEntry() {
		return entry;
	}

	public Page() {
	}

	public Page(int currentNo, int total) {
		this.currentNo = currentNo;
		this.total = total;
	}

	public void setEntry(T entry) {
		this.entry = entry;
	}
}
