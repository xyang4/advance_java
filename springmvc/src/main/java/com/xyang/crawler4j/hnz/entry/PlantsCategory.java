package com.xyang.crawler4j.hnz.entry;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 作物分类实体类
 *
 */
public class PlantsCategory {
	private String title;
	private String url;
	private PlantsCategory parent;// 父类
	private Set<PlantsCategory> child;// 子类
	private Set<String> childsUrls;//子类地址集

	public Set<String> getChildsUrls() {
		return childsUrls;
	}

	public void setChildsUrls(Set<String> childsUrls) {
		this.childsUrls = childsUrls;
	}

	private Date createTime;// 创建时间
	private String createTimeStr;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "PlantsCategory [title=" + title + ", url=" + url + ", parent=" + parent + ", child=" + child
				+ ", createTime=" + createTime + ", createTimeStr=" + createTimeStr + "]";
	}

	public String getUrl() {
		return url;
	}

	public PlantsCategory getParent() {
		return parent;
	}

	public void setParent(PlantsCategory parent) {
		this.parent = parent;
	}

	public Set<PlantsCategory> getChild() {
		return child;
	}

	public void setChild(Set<PlantsCategory> child) {
		this.child = child;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
}
