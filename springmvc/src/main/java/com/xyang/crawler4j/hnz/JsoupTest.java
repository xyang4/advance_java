package com.xyang.crawler4j.hnz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.xyang.crawler4j.hnz.entry.Page;
import com.xyang.crawler4j.hnz.entry.PlantMsg;
import com.xyang.crawler4j.hnz.entry.PlantsCategory;
import com.xyang.utils.CodingUtils;
import com.xyang.utils.HttpRequestResult;
import com.xyang.utils.HttpRequestUtils;

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
	private static String index_url = "index.htm";
	private static String plant_prefix = "plants/";
	private static String jxs_more = "jxsmore.asp";
	private static String jxs_url = "jxscha.htm";
	private static String title;
	// private static Map<String, Object> map = new HashMap<>();// 存储一级分类信息
	private static int total = 0;

	public static void main(String[] args) throws Exception {

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { try { plantNewsExe(); } catch
		 * (Exception e) { e.printStackTrace(); } } }).start();
		 */
		// ===== 获取经销商信息
		// agentExe();
		// jxshaInfotest();
		// specificPlantMsgCrawlerByUrl(base_url + "news/20160513/152111.html");
//		getAreaNameWithThreadTest();
		grabJxshaInfoUrlsByProvince(CodingUtils.toBrowserCode("山西", null));// 山西

	}

	private static void getAreaNameWithThreadTest() {
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Map<String, Set<String>> map = null;
					try {
						map = grabAreaName(base_url + jxs_url);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(map);
				}
			}).start();
		}
	}
	private static void plantNewsExe() throws Exception {
		// grabPlantNewsMsgByCate2thUrl(base_url + plant_prefix+ "dadou.asp");
		// 先获取二级子类的url
		Map<String, PlantsCategory> map = grabPlantCategoryUrl2th(base_url
				+ plant_prefix + "dadou.asp");
		System.out.println(title);
		for (String names : map.keySet()) {
			// Set<PlantsCategory> category2th =
			// map.get(category).getChild();//二级分类
			Set<String> urls = map.get(names).getChildsUrls();
			System.out.println("一级分类:" + names + " 二级个数:" + urls.size());
			for (String url : urls) {
				grabPlantNewsMsgByCate2thUrl(url);
			}
		}
	}

	private static void agentExe() throws Exception {
		// 1.根据大区分别获得省份 按map<'大区名','省份名set'>
		Map<String, Set<String>> areaMap = grabAreaName(base_url + jxs_url);
		System.out.println(areaMap);
		System.out
				.println("==============================================================================");
		for (String name : areaMap.keySet()) {
			Set<String> names = areaMap.get(name);
			System.out.println("###    大区名称:" + name + "   ###");
			for (String province : names) {
				/*
				 * new Thread(new Runnable(){
				 * 
				 * @Override public void run() { try {
				 * grabJxshaInfoUrlsByProvince(CodingUtils.toBrowserCode(p,
				 * null)); } catch (Exception e) { e.printStackTrace(); }
				 * }}).start(); Thread.sleep(1000);
				 */
				grabJxshaInfoUrlsByProvince(CodingUtils.toBrowserCode(province,
						null));// 山西
			}
		}
		// 2.按省份获取信息
	}

	/**
	 * @描述 按大区获取省份名称
	 * @param string
	 * @return
	 * @throws IOException
	 */
	private static Map<String, Set<String>> grabAreaName(String indexUrl)
			throws IOException {
		Map<String, Set<String>> map = new HashMap<>();
		HttpRequestResult result = HttpRequestUtils.doGet(indexUrl, "gb2312");
		if (200 == result.getCode()) {
			// Document doc = Jsoup.connect(indexUrl).get();
			Document doc = Jsoup.parse(result.getContent());
			Elements root = doc.select(".list1");
			for (Element e : root) {
				String bigAreaName = e.select(".aa5").html();
				Set<String> set = new HashSet<>();
				for (Element p : e.select(".b7")) {
					set.add(p.attr("href").split("=")[1]);
				}
				map.put(bigAreaName, set);
			}
		}
		return map;
	}

	/**
	 * @param categoryUrl
	 *            二级分类链接
	 * @throws Exception
	 */
	private static void grabPlantNewsMsgByCate2thUrl(String categoryUrl)
			throws Exception {
		Set<String> plantNewsUrls = grabPlantNewsUrls(categoryUrl);
		Set<PlantMsg> plantMsg = grabPlantMsgByNewsUrl(plantNewsUrls);
		// TODO 入库
	}

	/**
	 * @描述 根据省份经销商信息获取
	 * @throws Exception
	 */
	private static void grabJxshaInfoUrlsByProvince(final String province)
			throws Exception {
		for (int i = 0; i < 200; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Page<Set<String>> jxhLinksPage = grabJxshaInfoLinkUrl(base_url+ jxs_more + "?lb=" + province);
						System.out.println(jxhLinksPage.getTotal() + " "+ jxhLinksPage.getEntry().size());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

		}
	}

	/**
	 * @描述 根据二级地址获取所有的咨询链接 url
	 * @param url
	 *            作物二级分类url
	 * @throws Exception
	 */
	private static Set<String> grabPlantNewsUrls(String url) throws Exception {
		Page<Set<String>> pages = grabPlantNewsUrlByPageNO(url, null);
		Set<String> allUrls = new HashSet<>();
		int total = pages.getTotal();
		int currentNo = pages.getCurrentNo();
		for (int i = 1; i <= total; i++) {
			Set<String> urls = null;
			if (i == currentNo) {
				urls = pages.getEntry();
			} else {
				urls = grabPlantNewsUrlByPageNO(url, i).getEntry();
			}
			if (null != urls) {
				allUrls.addAll(urls);
			}
			System.out.println("获取 newsurl  当前页:" + i + " total:" + total
					+ " 条数：" + urls.size());
		}
		return allUrls;
	}

	/**
	 * @描述 获取经销商信息链接地址
	 * @param provinceUrl
	 * @return
	 * @throws Exception
	 */
	private static Page<Set<String>> grabJxshaInfoLinkUrl(String provinceUrl)
			throws Exception {
		Set<String> jxhLink = new HashSet<String>();
		HttpRequestResult result = HttpRequestUtils.doGet(provinceUrl, "gb2312");
		Page<Set<String>> page = null;
		if (200 == result.getCode()) {
			page = new Page<>();
			Document doc = Jsoup.parse(result.getContent());
			Element e1 = doc.select("#aleft1>table").get(2);
			// 分页信息获取
			Elements e_page = doc.select("#aleft1>table").get(3)
					.select(".STYLE24");
			for (Element e2 : e1.select("tbody>tr:gt(0)>td:eq(1)")) {
				String href = e2.select("a").attr("href");
				jxhLink.add(href.substring(0, href.indexOf("&")));// 仅存储id
			}
			page.setEntry(jxhLink);
			page.setTotal(Integer.valueOf(e_page.select("b:eq(2)").text()));
		}
		return page;
	}

	/**
	 * @描述 获取分类信息
	 * @return
	 * @throws IOException
	 */
	private static Map<String, PlantsCategory> grabPlantCategoryUrl2th(
			String url) throws IOException {
		// 1.获取农作物种植技术大全
		Document doc = Jsoup.connect(url).get();
		Element nzwjsdq = doc.select("body>table").get(5);
		// 标题
		title = nzwjsdq.select("tbody>tr").get(0).text().trim();
		// 一级子类
		Elements root = nzwjsdq.select("tbody>tr").get(2).select("table");
		Map<String, PlantsCategory> map = null;
		if (!root.isEmpty()) {
			map = new HashMap<>();
		}
		for (Element e : root) {
			PlantsCategory parent = new PlantsCategory();
			parent.setTitle(e.text());
			Set<PlantsCategory> child = new HashSet<PlantsCategory>();
			Set<String> childUrls = new HashSet<>();
			String title_1th = e.select(".STYLE4").text();
			for (Element e2 : e.select(".ab")) {
				String url2th = base_url + plant_prefix
						+ e2.select("a").attr("href");
				childUrls.add(url2th);
				// TODO 二级子类信息
				PlantsCategory c = new PlantsCategory();
				c.setTitle(e2.text());
				c.setUrl(url2th);
				child.add(c);
			}
			parent.setChild(child);
			parent.setChildsUrls(childUrls);
			map.put(title_1th, parent);
		}
		return map;
	}

	/**
	 * @描述 根据页数获取每页的newUrl
	 * @param plantUrl
	 *            作物咨讯地址
	 * @param pageNo
	 *            页数
	 * @return
	 * @throws Exception
	 */
	public static Page<Set<String>> grabPlantNewsUrlByPageNO(String plantUrl,
			Integer pageNo) throws Exception {
		pageNo = pageNo == null ? 1 : pageNo;
		Document root = Jsoup.connect(plantUrl + "?page=" + pageNo).get();
		Elements areaInfo = root
				.select("body>table:eq(9)>tbody>tr>td:eq(0)>table>tbody>tr>td>table");
		// ===== 具体newsUrl获取
		Set<String> newsUrls = null;
		Elements e = areaInfo.get(0).select("a");
		if (e.size() > 0) {
			newsUrls = new HashSet<>();
		}
		for (Element element : e) {
			newsUrls.add(base_url + element.attr("href").substring(3));// ../news/20160513/152111.html
		}
		// ===== 分页信息
		// Elements pageInfo = areaInfo.get(1).select("tbody>tr:eq(1)>td");
		Element pageNoE = areaInfo.select(".styless2").get(0);
		String pageStr = pageNoE.text();
		int currentNo = Integer.valueOf(pageStr.substring(3,
				pageStr.indexOf("页/")));
		int total = Integer.valueOf(pageStr.substring(pageStr.indexOf("/") + 1,
				pageStr.length() - 1));
		Page<Set<String>> page = new Page<>(currentNo, total);
		page.setEntry(newsUrls);
		return page;
	}

	/**
	 * @描述 根据咨询url获取具体的咨询信息
	 * @param string
	 * @throws Exception
	 */
	private static PlantMsg grabPlantMsgByNewsUrl(String newsUrl)
			throws Exception {
		Document doc = Jsoup.connect(newsUrl).get();
		Elements root = doc.select("#aleft1").select("table>tbody")
				.select("table");
		// 1.标题
		String title = root.get(0).text();
		String time = root.get(1).select("tr:eq(0)").text();
		String contentHtml = root.get(1).html();
		String contentText = root.get(1).select("tr:eq(2)").text().trim();
		// 2.具体内容操作
		PlantMsg newsMsg = new PlantMsg();
		newsMsg.setTitle(title);
		newsMsg.setPublishTimeStr(time.substring(time.indexOf("发布日期：") + 5,
				time.indexOf("来源")).trim());
		newsMsg.setContentHtml(contentHtml);
		newsMsg.setContnet(contentText);
		return newsMsg;
	}

	// TODO 待优化加入多线程操作
	private static Set<PlantMsg> grabPlantMsgByNewsUrl(Set<String> urls)
			throws Exception {
		Set<PlantMsg> plantMsgs = null;
		if (null != urls && urls.size() > 0) {
			plantMsgs = new HashSet<>();
		}
		for (String url : urls) {
			plantMsgs.add(grabPlantMsgByNewsUrl(url));
		}
		return plantMsgs;
	}
}
