package com.xyang.crawler4j.demo;

import java.util.Date;
import java.util.Set;

/**
 * @author 分类实体类
 *
 */
public class Category {
	private String title;
	private String url;
	private Category parent;// 父类
	private Set<Category> child;// 子类
	private Date createTime;// 创建时间

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Category [title=" + title + ", url=" + url + ", parent="
				+ parent + ", child=" + child + ", createTime=" + createTime
				+ "]";
	}

	public String getUrl() {
		return url;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChild() {
		return child;
	}

	public void setChild(Set<Category> child) {
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
}
