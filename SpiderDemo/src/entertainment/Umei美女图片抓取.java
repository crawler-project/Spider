package entertainment;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**  
 *TODO Umei美女图片抓取 http://www.umei.cc/meinvtupian/
 *这里只抓去了第一页
 * @author 寇爽
 * @date 2017年12月14日
 * @version 1.8
 */
public class Umei美女图片抓取 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url = "http://www.umei.cc/meinvtupian/";
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select(".TypeList >ul >li");
		System.out.println(items.size());
		for (Element item : items) {
			// 标签
			String title = item.select(".TypePicTags").text();
			// 日期(类名“txtInfo gray”有空格 所以不用.txtInfo gray的方式而用div[class=txtInfo
			// gray])
			String date = item.select("div[class=txtInfo gray] em").last().text();
			// 查看次数
			String watchnums = item.select("div[class=txtInfo gray] em").first().text();
			// 图片链接地址
			String picture_href = item.select("img[src$=.jpg]").first().attr("src");
			// 全部内容链接地址
			String content_href = item.select("a").first().attr("href");
			System.out.println("标签：                     " + title);
			System.out.println("日期                         " + date);
			System.out.println("查看次数：              " + watchnums);
			System.out.println("图片链接地址：       " + picture_href);
			System.out.println("全部内容链接地址：" + content_href);
			System.out.println();
			System.out.println();
		}
	}

}
