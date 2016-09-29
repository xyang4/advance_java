package com.xyang.crawler4j.hnz;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xyang.utils.HttpRequestResult;
import com.xyang.utils.HttpRequestUtils;

public class GoodsInfoGrab extends BaseGrab {
	public static void main(String[] args) throws IOException {
		// grabTupuInfo();
		// grabManufacturerInfo();
		grabGoodsInfo();
	}
	public static void grabGoodsInfo() {
		// 1.获取农资产品分类 一二级URL后缀
//		Map<String, Set<String>> map = grabGoodsCategoryLinkurl(base_url + "infolist.htm");
//		for (String key : map.keySet()) {
//			System.out.println(key + " " + map.get(key));
//		}
		// 厂商：农药 肥料 种子
//		Set<String> set = grabMFLinkurlBypageViGoodsRukou(base_url+"zhongzi",null);
//		System.out.println(set.size());
		// 产品 ：除草剂
//		1. url 为 http://www.haonongzi.com/shajun/的
//		Set<String> set = grabGoodsLinkurlByCatgoryAndPage(base_url+"chucao",null);
//		//循环查找
//		System.out.println(set.size());
//		=========================================================
		
		
		
		
		//2. url 为 http://www.haonongzi.com/infomore.asp?page=3&lb=%C9%B1%CA%F3%BC%C1&sid=16
		Set<String> set = grabGoodsLinkurlByCatgoryAndPage2(base_url+"infomore.asp?page=3&lb=%C9%B1%CA%F3%BC%C1&sid=16",2);
		System.out.println(set.size());
//		grabGoodsLinkurlByCatgoryAndPage2(base_url+"chucao",null);
//		grabGoodsDetailsInfoByLinkurl(base_url+"/info/dhhg-hnz/product_549027.html");
	}
	private static Set<String> grabGoodsLinkurlByCatgoryAndPage2(String category_url,Integer pageNo) {
		category_url += ("&page=" + (null == pageNo ? 1 : pageNo));
		Set<String> set = null;
		HttpRequestResult result = HttpRequestUtils.doGet(category_url, chartset);
		if (200 == result.getCode()) {
			set = new HashSet<>();
			Document doc = Jsoup.parse(result.getContent());
			for(Element e : doc.select(".b1")){
				set.add(e.attr("href"));
			}
			//TODO 页数获取
			Elements pageE = doc.select(".list1").get(0).select("tbody>tr:eq(1)").select("table:eq(2)");
			String temp = pageE.text();
			int totalPage = 0;
			totalPage = Integer.valueOf(temp.split("页")[2].replace("共", "").trim());
			System.out.println(totalPage);
		}
		return set;
		
	}
	private static void grabGoodsDetailsInfoByLinkurl(String url) {
		HttpRequestResult result = HttpRequestUtils.doGet(url, chartset);
		if (200 == result.getCode()) {
			Document doc = Jsoup.parse(result.getContent());
			Elements root = doc.ownerDocument().select("#aleft1");
			Elements e = root.select(".STYLE333");
			//TODO 实体封装
			String zhichiStr=e.get(0).text();//厂家支持
			String dailiC = e.get(1).text();//代理条件
			String shuomingStr = null;//产品说明
			String changjiaStr = root.select(".b1").text();
			String imgUrl = root.select(".list5").select("img").attr("src").substring(6);
			System.out.println("厂家 " +changjiaStr);
			System.out.println("图片 " +imgUrl);
			System.out.println("厂家支持 " +zhichiStr);
			System.out.println("代理条件 "+dailiC);
			Elements shuomingE = root.select("span");
			for(Element ee : shuomingE){
				if(ee.attr("style").equals("font-size:14px")){
					shuomingStr = ee.text();
					break;
				}
			}
			System.out.println("产品说明 "+shuomingStr);
		}
	}
	private static Map<String, Set<String>> grabGoodsCategoryLinkurl(String url) {
		Map<String, Set<String>> map = new HashMap<>();
		HttpRequestResult result = HttpRequestUtils.doGet(url, chartset);
		if (200 == result.getCode()) {
			Document doc = Jsoup.parse(result.getContent());
			Elements root = doc.ownerDocument().select("#leibie>table");
			Elements element1th = root.select(".b3");
			Elements element2th = root.select(".ab");
			// // 一级
			Set<String> set1th = new HashSet<>();
			for (Element e : element1th) {
				set1th.add(e.attr("href"));
			}
			map.put("1th", set1th);
			// 二级
			Set<String> set2th = new HashSet<>();
			for (Element e : element2th) {
				set2th.add(e.attr("href"));
			}
			map.put("2th", set2th);
		}
		return map;
	}
	
	public static Set<String> grabGoodsLinkurlByCatgoryAndPage(String category_url,Integer pageNo){
		category_url += ("/default.asp?page=" + (null == pageNo ? 1 : pageNo));
		Set<String> set = null;
		HttpRequestResult result = HttpRequestUtils.doGet(category_url, chartset);
		if (200 == result.getCode()) {
			set = new HashSet<>();
			Document doc = Jsoup.parse(result.getContent());
			for(Element e : doc.select(".b4")){
				set.add(e.attr("href").substring(3));
			}
		}
		return set;
	}
}
