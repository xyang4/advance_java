package com.xyang.crawler4j.hnz;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xyang.utils.HttpRequestResult;
import com.xyang.utils.HttpRequestUtils;

public class QiyeInfoGrab extends BaseGrab {
	private static String qiye_url = "qiye/allqy.asp";
	private final static String icbc_url="http://www.qichacha.com/search?key=";
	public static void grabManufacturerInfo() {
		// TODO Auto-generated method stub
		Set<String> qiyeLinkurls = grabMFLinkurlsByPage(base_url + qiye_url, null);
		System.out.println(qiyeLinkurls);
		grabMFInfoByLinkurl(base_url + "");
	}
	/**
	 * @描述 经产品库入口进入查询企业信息（有联系方式）
	 * @param string
	 * @param object
	 * @return
	 */
	public static Set<String> grabMFLinkurlBypageViGoodsRukou(String url, Integer pageNo) {
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
	/**
	 * @param 根据企业链接地址查询企业信息详情
	 */
	private static void grabMFInfoByLinkurl(String qiyeUrl) {
		// TODO Auto-generated method stub
		System.out.println(qiyeUrl);
	}
	private static void getQiyeInfoByName(String qiyeName){
		HttpRequestResult result = HttpRequestUtils.doGet(icbc_url+qiyeName, chartset);
		if (200 == result.getCode()) {
			Document doc = Jsoup.parse(result.getContent());
			Elements root = doc.select(".searchlist");
		}
	}
}
