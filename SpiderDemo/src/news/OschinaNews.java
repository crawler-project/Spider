package news;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * TODO 开源中国新闻资讯（全部新闻栏目）抓取(https://www.oschina.net/news)
 * 
 * @author 寇爽
 * @date 2017年12月11日晚
 * @version 1.8
 */
public class OschinaNews {

	public static void main(String[] args) throws IOException {
		String url = "https://www.oschina.net/news";
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select("#all-news .item");
		System.out.println("当前页面新闻数目：" + items.size());
		String host = "https://www.oschina.net";
		for (Element item : items) {
			// 过滤广告
			if (!item.attr("data-tracepid").isEmpty()) {
				continue;
			}
			// 标题
			String title = item.select("a").first().text();

			// 新闻链接地址
			String title_href = item.select("a").first().attr("href");
			if (!title_href.startsWith("https://")) {
				title_href = host + title_href;
			}

			// 新闻描述(div[class=sc sc-text text-gradient wrap summary]：类名为“sc
			// sc-text text-gradient wrap summary”的元素)
			String desc = item.select("div[class=sc sc-text text-gradient wrap summary]").text();

			// 作者头像(img[class=avatar]:类名为avatar的图片)
			String author_image = item.select("img[class=avatar]").attr("src");
			// String author_image = item.select("img").first().attr("src");

			Element mr = item.select(".from .mr").get(0);
			// 作者
			String author = mr.select("a").text();
			// 从span[class=mr]中移除a标签，输出的即为发布时间
			mr.select("a").remove();
			String published = mr.text();

			System.out.println("链接：        " + title_href);
			System.out.println("标题：        " + title);
			System.out.println("作者：        " + author);
			System.out.println("发布时间： " + published);
			System.out.println("详细信息： " + desc);
			System.out.println();
			System.out.println();
			/*
			 * String number = item.select(".from .mr").last().text();
			 * System.out.println(number);
			 */

		}

	}

}
