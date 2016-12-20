package com.xyang.crawler4j.hnz;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xyang.utils.HttpRequestResult;
import com.xyang.utils.HttpRequestUtils;

/**
 * @author zhangjie
 * @描述 百科信息抓取
 */
public class BaikeInfoGrab extends BaseGrab {
	private static String baike_url = "baike/";
	private static Set<String> category_suffix_url;

	public static void main(String[] args) {
//		if (null == category_suffix_url) {
//			initCategorySU();
//		}
//		Set<String> set = grabBaikeLinkurlByPageAndCategorySU("nybkmore.asp", 2);
//		// TODO 1.按页数循环处理，2.入库处理
//		System.out.println(set.size());
		grabBaikeDetailInfoByLinkurl(base_url + "news/20160618/105156.html");
	}

	private static void grabBaikeDetailInfoByLinkurl(String linkurl_suffix) {
		HttpRequestResult result = HttpRequestUtils.doGet(linkurl_suffix,chartset);
		if (200 == result.getCode()) {
			Document doc = Jsoup.parse(result.getContent());
			Elements root = doc.select("#aleft1>table>tbody>tr");
//			Elements titleE = root.select("table:eq(0)");
//			System.out.println(titleE.html());
			String title = root.get(0).text();
			Element contentE = root.get(1);
			String temp = contentE.select(".STYLE22").text();
			String publishTime = temp.substring(temp.indexOf("发布日期：")+5,temp.indexOf("来源"));
			String content = contentE.select("font").text().trim();
			System.out.println(content);
			// TODO 实体组装，入库处理
		}
	}

	private static void initCategorySU() {
		HttpRequestResult result = HttpRequestUtils.doGet(base_url + baike_url,
				chartset);
		if (200 == result.getCode()) {
			Document doc = Jsoup.parse(result.getContent());
			Elements root = doc.select("#aleft").select(".b2");
			if (!root.isEmpty()) {
				category_suffix_url = new HashSet<>();
			}
			for (Element e : root) {
				category_suffix_url.add(e.attr("href").substring(3));
			}
		}
	}

	private static Set<String> grabBaikeLinkurlByPageAndCategorySU(String category_url, Integer pageNo) {
		category_url += ("?page=" + (null == pageNo ? 1 : pageNo));
		Set<String> set = null;
		HttpRequestResult result = HttpRequestUtils.doGet(base_url+ category_url, chartset);
		if (200 == result.getCode()) {
			Document doc = Jsoup.parse(result.getContent());
			// TODO 百科分类名称
			String categoryName = doc.select(".x2").text().trim();
			Elements root = doc.select(".x3");
			if (!root.isEmpty()) {
				set = new HashSet<>();
			}
			for (Element e : root) {
				String temp = e.attr("href");
				if (temp.startsWith("news/")) {
					set.add(temp);
				}
			}
			// 页数解析
			Elements tempE = doc.select(".styless2").select("span");
			// TODO 总页数处理
			int totalPage = 0;
			for (Element e : tempE) {
				String s = e.text();
				if (s.startsWith("当前第")) {// 当前第2页/201页
					totalPage = Integer.valueOf(s.substring(s.indexOf("/") + 1,
							s.length() - 1));
					break;
				}
			}
		}
		return set;
	}
}
