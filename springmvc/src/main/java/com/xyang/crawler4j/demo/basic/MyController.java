package com.xyang.crawler4j.demo.basic;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class MyController {
	public static void main(String[] args) throws Exception {
		String crawlStorageFolder = "/data/crawl";;
		int numberOfCrawlers = 1;
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setMaxDepthOfCrawling(1);
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,pageFetcher);
		
		CrawlController controller = new CrawlController(config, pageFetcher,robotstxtServer);
		controller.addSeed("http://www.haonongzi.com/jxscha.htm");

		controller.start(MyCrawler.class, numberOfCrawlers);
	}
}
