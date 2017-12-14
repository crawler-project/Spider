package news;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *TODO  和人民网首页焦点图的抓取的唯一区别是网址改变
 * @author 寇爽
 * @date 2017年12月14日
 * @version 1.8
 */
public class P人民网科技栏目焦点图 {

	public static void main(String[] args) throws IOException {
		String url = "http://scitech.people.com.cn/";
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select("#focus_list >ul >li ");
		System.out.println(items.size());
		for (Element item : items) {
			// 标题
			String title = item.select(".show a").text();
			// 新闻链接地址
			String title_href = item.select("a").first().attr("href");
			// 图片链接地址
			String picture_href = "http://www.people.com.cn/" + item.select("img").first().attr("src");
			System.out.println("链接地址：" + title_href);// 通过链接地址就可以获取文章内容，在APP中也可以直接通过此链接跳转到相应新闻网页
			System.out.println("标题：        " + title);
			System.out.println("图片链接地址： " + picture_href);
			System.out.println();
			System.out.println();

		}

	}
}
