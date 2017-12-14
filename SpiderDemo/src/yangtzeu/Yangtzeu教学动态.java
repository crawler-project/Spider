package yangtzeu;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * TODO 长江大学教务处 >> 教务新闻 >> 教学动态(http://jwc.yangtzeu.edu.cn/jwnews/jwxw/bzsw/index.html)
 * 这里只获取了第一页的数据
 * 获取方法和获取教务通知的方法一样
 * @author 寇爽
 * @date 2017年12月13日上午9点
 * @version 1.8
 */
public class Yangtzeu教学动态 {

	public static void main(String[] args) throws IOException {
		String url = "http://jwc.yangtzeu.edu.cn/jwnews/jwxw/jxdt/";
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select("#list_r > ul li");
		System.out.println(items.size());
		for (int i = 0; i < items.size(); i++) {
			Element item = items.get(i);
			// 标题
			String title = item.select("a").first().text();
			// System.out.println(title);

			// 日期(.info .mr:类名info下的类名为mr的类)
			String publishdate = item.select("span").first().text();
			System.out.println(publishdate);

			// 新闻链接地址
			String title_href = item.select("a").first().attr("href");

			System.out.println("链接地址：" + title_href);
			System.out.println("标题：        " + title);
			System.out.println("发布时间： " + publishdate);
			System.out.println();
			System.out.println();
		}
	}
}
