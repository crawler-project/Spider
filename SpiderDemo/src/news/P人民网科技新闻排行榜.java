package news;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *TODO 人民网科技新闻排行榜抓取，这里只抓取了新闻标题和链接。
 * @author 寇爽
 * @date 2017年12月13日
 * @version 1.8
 */
public class P人民网科技新闻排行榜 {

	public static void main(String[] args) throws IOException {
		String url = "http://scitech.people.com.cn/";
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select(".ph_list >li");
		System.out.println(items.size());
		for (Element item : items) {
			// 标题
			String title = item.select("a").text();
			// 链接地址
			String title_href = url + item.select("a").first().attr("href");
			System.out.println("链接地址：" + title_href);// 通过链接地址就可以获取文章内容，在APP中也可以直接通过此链接跳转到相应新闻网页
			System.out.println("标题：        " + title);
			System.out.println();
			System.out.println();

		}
	}

}
