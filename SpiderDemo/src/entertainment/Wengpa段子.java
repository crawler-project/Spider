package entertainment;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Wengpa段子 {

	public static void main(String[] args) throws IOException {
		String url = "http://wengpa.com/duanzi/";
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select("#content >article");
		System.out.println(items.size());
		for (Element item : items) {
			//标题
			String title=item.select(".entry-title a").text();
			//内容
			String content=item.select(".entry-content p").text();
			System.out.println("标题：    "+title);
			System.out.println("内容        "+content);
			System.out.println();
			System.out.println();
		}
	}

}
