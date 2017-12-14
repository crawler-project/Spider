package news;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * TODO 开源中国新闻资讯（热门综合资讯）抓取(https://www.oschina.net/news)
 * 
 * @author 寇爽
 * @date 2017年12月11日晚
 * @version 1.8
 */
public class OschinaNews2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url = "https://www.oschina.net/news";
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
		// 由于“热门综合咨询”和“软件更新”的class属性相同，所以下面语句会把软件更新的元素也获取进来
		Elements items = document.select(".news-list-simple-item .item");
		// System.out.println(items.size());// 20
		String host = "https://www.oschina.net";
		// items.size()c除2的原因是为了只输出“热门综合咨询”的内容
		// 如果需要输出的是“软件更新”栏目的内容的话只需要把for循环改为for (int i = 10; i <items.size();
		// i++)
		for (int i = 0; i < items.size()/2; i++) {
			Element item = items.get(i);
			// 标题
			String title = item.select("a").first().text();
			// System.out.println(title);

			// 日期(.info .mr:类名info下的类名为mr的类)
			String publishdate = item.select(".info .mr").first().text();
			// System.out.println(publishdate);

			// 新闻链接地址
			String title_href = item.select("a").first().attr("href");
			if (!title_href.startsWith("https://")) {
				title_href = host + title_href;
			}
			// System.out.println(title_href);
			// 评论数(ancestor child: 查找某个元素下子元素，比如：可以用.body p 查找在"body"元素下的所有 p元素)
			String issues = item.select(".info span").last().text();
			// System.out.println(issues);
			System.out.println("链接：        " + title_href);
			System.out.println("标题：        " + title);
			System.out.println("发布时间： " + publishdate);
			System.out.println("评论数：     " + issues);
			System.out.println();
			System.out.println();
		}
	}

}
