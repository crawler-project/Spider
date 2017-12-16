package entertainment;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UmeiMeitupianUtils {
	/**
	 * 获取首页某个图片详细内容的网址的方法
	 * 
	 * @param url
	 *            要传入的网址
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> getContent_href(String url) throws IOException {
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select(".TypeList >ul >li");
		System.out.println(items.size());
		ArrayList<String> picture_href = new ArrayList<>();
		// 存放图片详细内容地址
		ArrayList<String> content_href = new ArrayList<>();
		for (Element item : items) {
			// 图片链接地址
			picture_href.add(item.select("img[src$=.jpg]").first().attr("src"));
			// 全部内容链接地址
			content_href.add(item.select("a").first().attr("href"));
		}
		return content_href;
	}
}
