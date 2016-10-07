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
		
//		String url = "shachong/";
//		String url = "zsview/fl.asp";
		String url = "cclist/ym.asp";
//		String url = "infomore.asp?lb=%C9%B1%CA%F3%BC%C1&sid=16";
//		Set<String> set = null;
//		if(url.contains(".asp")){
//			if(url.contains("&sid=")){
//				set = grabGoodsLinkurlByCatgoryAndPage2(base_url+url,1);
//			}else{
//				set = grabGoodsLinkurlByCatgoryAndPage1(base_url+url,2);
//				if(null==set){
//					set=grabGoodsLinkurlByCatgoryAndPage4(base_url+url,null);
//				}
//			}
//		}else{
//			set = grabGoodsLinkurlByCatgoryAndPage(base_url+url,1);
//		}
		// 厂商：农药 肥料 种子
//		Set<String> set = grabMFLinkurlBypageViGoodsRukou(base_url+"zhongzi",null);
//		System.out.println(set.size());
		// 产品 ：除草剂
//		1. url 为 http://www.haonongzi.com/shajun/的 
//		Set<String> set = grabGoodsLinkurlByCatgoryAndPage(base_url+"shachong",null);
//		Set<String> set = grabGoodsLinkurlByCatgoryAndPage1(base_url+"zsview/fl.asp",2);
		//2. url 为 http://www.haonongzi.com/infomore.asp?page=3&lb=%C9%B1%CA%F3%BC%C1&sid=16
//		Set<String> set = grabGoodsLinkurlByCatgoryAndPage2(base_url+"infomore.asp?lb=%C9%B1%CA%F3%BC%C1&sid=16",2);
//		//循环查找
//		System.out.println(set.size());
//		=========================================================
//		System.out.println(set.size());
		grabGoodsDetailsInfoByLinkurl(base_url+"info/dhhg-hnz/product_549027.html");
		
	}
	private static Set<String> grabGoodsLinkurlByCatgoryAndPage4(String category_url, Integer pageNo) {
		category_url += ("?page=" + (null == pageNo ? 1 : pageNo));
		Set<String> set = null;
		HttpRequestResult result = HttpRequestUtils.doGet(category_url, chartset);
		if (200 == result.getCode()) {
			set = new HashSet<>();
			Document doc = Jsoup.parse(result.getContent());
			for(Element e : doc.select(".zz")){
				String tmp = e.attr("href");
				if(tmp.startsWith("../info/")){
					set.add(tmp.substring(3));
				}
			}
			//页数处理
			int pageNoTotal = 0;
			for(Element e : doc.select(".b3")){
				if(e.text().startsWith("末")){
					String tmp =e.attr("href");
					pageNoTotal=Integer.valueOf(tmp.substring(tmp.indexOf("=")+1));
				}
			}
			System.out.println(pageNoTotal);
		}
		return set;
	}
	private static Set<String> grabGoodsLinkurlByCatgoryAndPage1(String category_url,Integer pageNo) {
		category_url += ("?page=" + (null == pageNo ? 1 : pageNo));
		Set<String> set = null;
		HttpRequestResult result = HttpRequestUtils.doGet(category_url, chartset);
		if (200 == result.getCode()) {
			set = new HashSet<>();
			Document doc = Jsoup.parse(result.getContent());
			for(Element e : doc.select(".b4")){
				String tmp = e.attr("href");
				if(tmp.startsWith("../info/")){
					set.add(tmp.substring(3));
				}
			}
			//TODO 页数处理
			int pageNoTotal = 0;
			for(Element e : doc.select(".b3")){
				if(e.text().startsWith("最")){
					String tmp =e.attr("href");
					pageNoTotal=Integer.valueOf(tmp.substring(tmp.indexOf("=")+1));
				}
			}
			System.out.println(pageNoTotal);
		}
		return set;
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
			temp = temp.substring(temp.indexOf("共"));
			int totalPage = 0;//总页数
			int total=0;;//总记录条数
//			totalPage = Integer.valueOf(temp.split("页")[2].replace("共", "").trim());
			String[] strArr = temp.substring(temp.indexOf("共")+1, temp.indexOf("个产品")).split("页");
			totalPage = Integer.valueOf(strArr[0]);
			total = Integer.valueOf(strArr[1]);
			System.out.println("总记录数:"+total+" 总页数:"+totalPage);
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
			String changjiaStr = root.select(".b1").text();//厂家名称
			String qiyeLinkUrl = url.replace(url.substring(url.lastIndexOf("/")+1),"index.html");
			String imgUrl = root.select(".list5").select("img").attr("src").replace("../", "").trim();
			String shuomingStr = null;//产品说明
			Elements shuomingE = root.select("span");
			for(Element ee : shuomingE){
				if(ee.attr("style").equals("font-size:14px")){
					shuomingStr = ee.text();
					break;
				}
			}
			System.out.println(imgUrl+" "+changjiaStr+" "+shuomingStr.length());
			//TODO 联系方式处理
			System.out.println("厂家连接： "+qiyeLinkUrl);
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
		category_url += ("default.asp?page=" + (null == pageNo ? 1 : pageNo));
		Set<String> set = null;
		HttpRequestResult result = HttpRequestUtils.doGet(category_url, chartset);
		if (200 == result.getCode()) {
			set = new HashSet<>();
			Document doc = Jsoup.parse(result.getContent());
			for(Element e : doc.select(".b4")){
				set.add(e.attr("href").substring(3));
			}
			//TODO 页数处理
			int pageNoTotal = 0;
			for(Element e : doc.select(".b3")){
				if(e.text().startsWith("最")){
					String tmp =e.attr("href");
					pageNoTotal=Integer.valueOf(tmp.substring(tmp.indexOf("=")+1));
				}
			}
			System.out.println("总页数 ："+pageNoTotal);
		}
		return set;
	}
}
