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

import com.xyang.utils.CodingUtils;
import com.xyang.utils.HttpRequestResult;
import com.xyang.utils.HttpRequestUtils;

/**
 * @author zhangjie
 * @category 图谱信息抓取
 */
public class TupuInfoGrab extends BaseGrab {
	private static String tupu_url = "shop/tuku/";
public static void main(String[] args)throws Exception {
	grabTupuInfo();
}
	public static void grabTupuInfo() throws IOException {
		// 1.按 大田作物图库 经济作物图库 杂草图库 果树图库四大类抓取，考虑到将来做成定时任务，或消息队列的需要按此划分
		Map<String, Set<String>> map = grabTupuCateLinkUrl(base_url + tupu_url
				+ "tuku_se.asp");
		// 2.获取图谱链接
		for (String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
//		String suffix = "jingji.asp";
//		// String suffix="tuku_se.asp?seKey=梨";
//		Set<String> tupuInfoLinkurls = grabTupuInfoLinkUrlsByCategorySuffix(base_url
//				+ tupu_url + suffix);
//		System.out.println(tupuInfoLinkurls);
//		// 3.根据链接获取图谱具体信息
//		String tupuSuffix_url = "20140118/143337.html";
//		grabTupuInfoByLinkurl(base_url + tupu_url + tupuSuffix_url);

	}

	private static void grabTupuInfoByLinkurl(String linkurl) {
		HttpRequestResult result = HttpRequestUtils.doGet(linkurl, chartset);
		if (200 == result.getCode()) {
			Document doc = Jsoup.parse(result.getContent());
			Elements root = doc.select(".list7");
			String title = root.select("font").text();
			String content = root.select("span").text();
			String contentHtml = root.select("p").html();
			System.out.println(title);
			System.out.println(content);
			System.out.println(contentHtml);
		}
	}

	private static Set<String> grabTupuInfoLinkUrlsByCategorySuffix(String url)
			throws IOException {
		Set<String> set = null;
		if (url.contains("seKey=")) {
			// 中文转换
			String[] temp = url.split("=");
			url = temp[0] + "=" + CodingUtils.toBrowserCode(temp[1], chartset);
		}
		HttpRequestResult result = HttpRequestUtils.doGet(url, chartset);
		if (200 == result.getCode()) {
			set = new HashSet<>();
			Document doc = Jsoup.parse(result.getContent());
			Elements root = doc.select("#am_left>table>tbody>tr:eq(2)");
			for (Element e : root.select(".ab")) {
				set.add(e.attr("href"));
			}
			// TODO 页面处理
			String pageStr = root.select(".STYLE13").text();// 已收录2488个经济田图库，1/139页
			System.out.println(pageStr);
		}
		return set;
	}

	private static Map<String, Set<String>> grabTupuCateLinkUrl(String url) {
		Map<String, Set<String>> map = null;
		HttpRequestResult result = HttpRequestUtils.doGet(url, chartset);
		if (200 == result.getCode()) {
			map = new HashMap<>();
			Document doc = Jsoup.parse(result.getContent());
			// 1.作物url前缀获取
			Elements cropCategoryE = doc.select(".ac");
			Set<String> cropCategorySuffixUrls = new HashSet<>();
			for (Element e : cropCategoryE) {
				cropCategorySuffixUrls.add(e.attr("href"));
			}

			Elements rootE = doc.select("#am_right>table");
			Set<String> cropSuffixUrls = new HashSet<>();
			for (Element e : rootE.get(0).select("a")) {
				cropSuffixUrls.add(e.attr("href"));
			}
			//病虫害
			for (Element e : rootE.get(1).select("a")) {
				cropSuffixUrls.add(e.attr("href"));
			}
			System.out.println(cropSuffixUrls.size());
		}
		return map;
	}
}
