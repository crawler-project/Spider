package news;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class W网易财经专栏头条新闻 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url = "http://money.163.com/";
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select("ul[class=topnews_nlist] >li");
		// System.out.println(items.size());//6
		Elements items2 = document.select("ul[class=topnews_nlist mb20 no_border] >li");
		// System.out.println(items2.size());//6
		items.addAll(items2);
		// System.out.println(items.size());//12
		int i = 1;
		for (Element item : items) {
			// 头条新闻分两栏，每栏第一条新闻h2标签其他h3标签 ，我这里只是加了一个判断，肯定还有更好的办法。
			if (i == 1 || i == 7) {
				// 标题
				String title = item.select("h2").text();
				System.out.println(title);
				i++;
			} else {
				// 标题
				String title = item.select("h3").text();
				System.out.println(title);
				i++;
			}
			// 标题链接地址
			String title_href = item.select("a").first().attr("href");
			System.out.println(title_href);
		}
	}

}
