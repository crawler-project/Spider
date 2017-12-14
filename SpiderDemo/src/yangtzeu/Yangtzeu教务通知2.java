package yangtzeu;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * TODO 获取长江大学教务处 >> 教务新闻 >> 教务通知(http://jwc.yangtzeu.edu.cn/list/47.aspx)
 * 获取所有的数据
 * 
 * @author 寇爽
 * @date 2017年12月12日下午5点
 * @version 1.8
 */
public class Yangtzeu教务通知2 {

	public static void main(String[] args) throws IOException {
		// 总页数
		int nums = getNums("http://jwc.yangtzeu.edu.cn/list/47.aspx");
		// 总页数过多，下面测试就只测试了7页，如果需要获取全部数据改变循环条件即可
		for (int i = 0; i <= 7; i++) {

			if (i == 0) {
				String url = "http://jwc.yangtzeu.edu.cn/list/47.aspx";
				System.out.println("************当前执行：" + (i + 1) + "页，数据数：" + getItems(url) + "************");
				getData(url);
				System.out.println("*******************第" + (i + 1) + "页执行完毕*******************");
			} else {
				String j = "" + i;
				String url = "http://jwc.yangtzeu.edu.cn/list/" + "47-" + j + ".aspx";
				System.out.println("************当前执行：" + (i + 1) + "页，数据数：" + getItems(url) + "************");
				getData(url);
				System.out.println("*******************第" + i + "页执行完毕*******************");
			}

		}

	}

	/**
	 * 获取页数
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static int getNums(String url) throws IOException {
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select("#pages option");
		System.out.println();
		return items.size();
	}

	/**
	 * 获取页面新闻数
	 * 
	 * @return
	 */
	public static int getItems(String url) throws IOException {
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select("#list_r > ul li");
		return items.size();
	}

	/**
	 * 获取页面数据的方法
	 * 
	 * @param url
	 * @throws IOException
	 */
	public static void getData(String url) throws IOException {
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

		// #id: 通过ID查找元素，比如：#logo
		Elements items = document.select("#list_r > ul li");
		// System.out.println(items.size());
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
