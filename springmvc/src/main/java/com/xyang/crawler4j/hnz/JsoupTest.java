package com.xyang.crawler4j.hnz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.drew.lang.StringUtil;
import com.xyang.crawler4j.hnz.entry.PlantsCategory;

/*
 * 
 * 
 * 
 * 
 * 1.技术类信息
 http://www.haonongzi.com/jxscha.htm
 需求：
 a. 一级分类 二级分类，爬取每个小分类下的文章，包括标题、正文、图片等

 2.经商类信息
 */
public class JsoupTest {
	private static String base_url = "http://www.haonongzi.com/";
	private static String plant_url = "plants/";
	private static String jxs_url = "jxsmore.asp";
	private static Map<String, PlantsCategory> map = new HashMap<String, PlantsCategory>();// 存储一级分类信息
	private static int total = 0;

	public static void main(String[] args) throws Exception {
		// plantMsgCrawler(base_url+plant_url);
		// ===== 获取经销商信息
		String province = "山东";
		// http://www.haonongzi.com/jxsmore.asp?lb=
		Set<String> set = new HashSet<>();
		for (int i = 0; i < 100; i++) {
//			new Thread(new Runnable() {
//				public void run() {
//					// TODO
//				}
//			}).start();
			Set<String> jxhLinks = crawlerJxschaMsg(base_url + jxs_url + "?lb=", "%C9%BD%CE%F7");
			set.addAll(jxhLinks);
		}
		double capaciy =  set.size()/total;
		System.out.println(set);
		System.out.println("total:" + total + " size:" + set.size() + " capaciy:" + capaciy);
	}

	@SuppressWarnings("unused")
	private static void plantMsgCrawler(String url) throws IOException {
		// 1.获取大的分类信息
		crawlerPlantCategory(url, "gaoliang.asp");
		// 2.根据二级分类获取资讯信息
		crawlerPlantMsg(url, "lizi.asp");
	}

	// 经销商信息获取
	@SuppressWarnings("unused")
	private static Set<String> crawlerJxschaMsg(String url, String province) throws Exception {
		Set<String> jxhLink = new HashSet<String>();
		// TODO 地址转移处理
		// String strTest = URLDecoder.decode(province, "UTF-8");
		// System.out.println(strTest);
		// TODO Auto-generated method stub
		Document doc = Jsoup.connect(url).get();
		Element e1 = doc.select("#aleft1>table").get(2);

		// 分页信息获取
		Elements e_page = doc.select("#aleft1>table").get(3).select(".STYLE24");
		total = Integer.valueOf(e_page.select("b:eq(2)").text());
		for (Element e2 : e1.select("tbody>tr:gt(0)>td:eq(1)")) {
			String href = e2.select("a").attr("href");
			// System.out.println(str.substring(0,str.indexOf("&amp")));
			jxhLink.add(href.substring(0, href.indexOf("&")));// 仅存储id
		}
		return jxhLink;

	}

	/**
	 * @描述 获取分类信息
	 * @param baseUrl
	 * @return
	 * @throws IOException
	 */
	private static Map<String, PlantsCategory> crawlerPlantCategory(String baseUrl, String specific)
			throws IOException {
		Document doc = Jsoup.connect(baseUrl + specific).get();
		// 1.获取农作物种植技术大全
		Element nzwjsdq = doc.select("body>table").get(5);
		// System.out.println(nzwjsdq.html());
		// 标题
		System.out.println("大标题:\n" + nzwjsdq.select("tbody>tr").get(0).text());
		// 一级子类
		Elements e1 = nzwjsdq.select("tbody>tr").get(2).select("table");
		// System.out.println("一级子类:\n"+e1.html());
		// System.out.println("二级子类：\n"+e1.select("tbody>tr:eq(0)").text());
		// System.out.println("二级子类：\n" + e1.select(".STYLE4").text());
		// 先获取tbody
		int i = 0;
		for (Element e : e1) {
			PlantsCategory parent = new PlantsCategory();
			parent.setTitle(e.text());
			Set<PlantsCategory> child = new HashSet<PlantsCategory>();
			String title_1 = e.select(".STYLE4").text();
			System.out.println("---------------" + i++ + " " + title_1 + "---------------");
			int j = 0;
			for (Element e2 : e.select(".ab")) {
				// 二级子类信息
				PlantsCategory c = new PlantsCategory();
				c.setTitle(e2.text());
				c.setUrl(baseUrl + e2.select("a").attr("href"));
				child.add(c);
				System.out.println(j++ + " title:" + c.getTitle() + " url:" + c.getUrl());
			}
			parent.setChild(child);
			map.put(title_1, parent);
		}
		return map;
	}

	// 作物资讯信息爬取
	public static void crawlerPlantMsg(String baseUrl, String spectificUrl) {
		// TODO
		System.out.println(spectificUrl);
	}
}
