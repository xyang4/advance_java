package com.xyang.crawler4j.hnz.entry;

import java.util.Date;

/**
 * @author IBM
 * @描述 作物资讯实体类
 */
public class PlantMsg {
	private String imageUrl;// 图片地址
	private String title;// 资讯标题
	private String contnet;// 资讯内容
	private Date publishTime;
	private String publishTimeStr;// 发布时间
	private String source;// 资讯来源
	private PlantsCategory plantCategory;// 所属作物分类

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContnet() {
		return contnet;
	}

	public void setContnet(String contnet) {
		this.contnet = contnet;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "PlantMsg [imageUrl=" + imageUrl + ", title=" + title + ", contnet=" + contnet + ", publishTime="
				+ publishTime + ", publishTimeStr=" + publishTimeStr + ", source=" + source + ", plantCategory="
				+ plantCategory + "]";
	}

	public PlantsCategory getPlantCategory() {
		return plantCategory;
	}

	public void setPlantCategory(PlantsCategory plantCategory) {
		this.plantCategory = plantCategory;
	}

	public String getPublishTimeStr() {
		return publishTimeStr;
	}

	public void setPublishTimeStr(String publishTimeStr) {
		this.publishTimeStr = publishTimeStr;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
