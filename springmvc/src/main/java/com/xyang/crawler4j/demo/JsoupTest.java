package com.xyang.crawler4j.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
	private static String hnz_index_url = "http://www.haonongzi.com/plants/gaoliang.asp";

	public static void main(String[] args) throws IOException {
		Map<String, Category> map = new HashMap<String, Category>();// 存储一级分类信息
		Document doc = Jsoup.connect(hnz_index_url).get();
		// 1.获取农作物种植技术大全
		Element nzwjsdq = doc.select("body>table").get(5);
		// System.out.println(nzwjsdq.html());
		// //标题
		System.out
				.println("text:\n" + nzwjsdq.select("tbody>tr").get(0).text());
		System.out.println("-----------------------------");
		// 一级子类
		Elements e1 = nzwjsdq.select("tbody>tr").get(2).select("table");
		// System.out.println("一级子类:\n"+e1.html());
		// System.out.println("二级子类：\n"+e1.select("tbody>tr:eq(0)").text());
		System.out.println("二级子类：\n" + e1.select(".STYLE4").text());
		// 先获取tbody
		for (Element e : e1) {
			Category parent = new Category();
			parent.setTitle(e.text());

			Set<Category> child = new HashSet<Category>();
			String str = e.select(".STYLE4").text();
			System.out.println(str);
			for (Element e2 : e.select(".ab")) {
				// 二级子类信息
				Category c = new Category();
				c.setTitle(e2.text());
				c.setUrl(e2.select("a").attr("href"));
				child.add(c);
				System.out.println("2:" + child);
			}
			parent.setChild(child);
			System.out.println("1:" + parent.getChild().size());
			map.put(str, parent);
		}
		System.out.println(map.size());
	}
}
