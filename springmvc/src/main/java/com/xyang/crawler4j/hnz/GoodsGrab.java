package com.xyang.crawler4j.hnz;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xyang.utils.CodingUtils;
import com.xyang.utils.HttpRequestResult;
import com.xyang.utils.HttpRequestUtils;

public class GoodsGrab {
	private static String base_url = "http://www.haonongzi.com/";
	private static String tupu_url = "shop/tuku/";
	private static String chartset = "gb2312";
	private static String qiye_url = "qiye/allqy.asp";

	public static void main(String[] args) throws IOException {
		// grabTupuInfo();
		// grabManufacturerInfo();
		grabGoodsInfo();
	}

	private static void grabGoodsInfo() {
		// 1.获取农资产品分类 一二级URL后缀
//		Map<String, Set<String>> map = grabGoodsCategoryLinkurl(base_url + "infolist.htm");
//		for (String key : map.keySet()) {
//			System.out.println(key + " " + map.get(key).size());
//		}
		// 厂商：农药 肥料 种子
		Set<String> set = grabMFLinkurlBypageViGoodsRukou(base_url+"zhongzi",null);
		System.out.println(set.size());
		// 产品 ：除草剂
	}

	/**
	 * @描述 经产品库入口进入查询企业信息（有联系方式）
	 * @param string
	 * @param object
	 * @return
	 */
	private static Set<String> grabMFLinkurlBypageViGoodsRukou(String url, Integer pageNo) {
		url +="/default.asp?page="+(null==pageNo?0:pageNo);
		Set<String> set = null;
		HttpRequestResult result = HttpRequestUtils.doGet(url, chartset);
		if (200 == result.getCode()) {
			set = new HashSet<>();
			Document doc = Jsoup.parse(result.getContent());
			Elements linkE = doc.select(".b1");
			int i=0;
			for(Element e : linkE){
				set.add(e.attr("href"));
				System.out.println(i+++" "+e.attr("href"));
			}
			Elements pageE = doc.select("body>table:eq(7)").select("table:eq(1)");
			System.out.println(pageE.text());
			//分页信息获取
		}
		return set;
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

	private static void grabManufacturerInfo() {
		// TODO Auto-generated method stub
		Set<String> qiyeLinkurls = grabMFLinkurlsByPage(base_url + qiye_url, null);
		System.out.println(qiyeLinkurls);
		grabMFInfoByLinkurl(base_url + "");

	}

	/**
	 * @param 根据企业链接地址查询企业信息详情
	 */
	private static void grabMFInfoByLinkurl(String qiyeUrl) {
		// TODO Auto-generated method stub
		System.out.println(qiyeUrl);
	}

	private static Set<String> grabMFLinkurlsByPage(String url, Integer pageNo) {
		Set<String> set = null;
		if (null != pageNo) {
			url += "?page=" + pageNo;
		}
		HttpRequestResult result = HttpRequestUtils.doGet(url, chartset);
		if (200 == result.getCode()) {
			Document doc = Jsoup.parse(result.getContent());
			Elements elements = doc.select(".ab");
			if (!elements.isEmpty()) {
				set = new HashSet<>();
			}
			for (Element e : elements) {
				String href = e.attr("href");
				if (StringUtils.isNotEmpty(href) && href.startsWith("../info")) {
					set.add(href);
				}
			}
		}
		return set;
	}

	private static void grabTupuInfo() throws IOException {
		// 1.按 大田作物图库 经济作物图库 杂草图库 果树图库四大类抓取，考虑到将来做成定时任务，或消息队列的需要按此划分
		Map<String, Set<String>> map = grabTupuCateLinkUrl(base_url + tupu_url + "tuku_se.asp");
		// 2.获取图谱链接
		for (String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
		String suffix = "jingji.asp";
		// String suffix="tuku_se.asp?seKey=梨";
		Set<String> tupuInfoLinkurls = grabTupuInfoLinkUrlsByCategorySuffix(base_url + tupu_url + suffix);
		System.out.println(tupuInfoLinkurls);
		// 3.根据链接获取图谱具体信息
		String tupuSuffix_url = "20140118/143337.html";
		grabTupuInfoByLinkurl(base_url + tupu_url + tupuSuffix_url);

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

	private static Set<String> grabTupuInfoLinkUrlsByCategorySuffix(String url) throws IOException {
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
			map.put("作物大类", cropCategorySuffixUrls);
			Elements cropE = doc.select(".ss2a");
			Set<String> cropSuffixUrls = new HashSet<>();
			for (Element e : cropE) {
				cropSuffixUrls.add(e.attr("href"));
			}
			map.put("作物", cropSuffixUrls);
			Elements bingchongE = doc.select(".ss2");
			Set<String> bingchongSuffixUrls = new HashSet<>();
			for (Element e : bingchongE) {
				bingchongSuffixUrls.add(e.attr("href"));
			}
			map.put("病虫害", bingchongSuffixUrls);
		}
		return map;
	}
}
