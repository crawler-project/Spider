package page_list;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * TODO 1,CSDN根据页码进行翻页 比如http://blog.csdn.net/qq_34337272/article/list/1代表第一页
 * 所以只要获取总页数后在循环遍历每一页就可以获取到所有的数据
 * 
 * @author 寇爽
 * @date 2017年12月11日
 * @version 1.8
 */
public class CSDN_BLOG {
	public static void main(String[] args) throws IOException {

		Integer total_page_number = getTotalPageNumber();
		System.out.println("总页数：" + total_page_number);
		String url = "http://blog.csdn.net/qq_34337272/article/list/";
		for (int current_page = 1; current_page <= total_page_number; current_page++) {
			System.out.println("-------------------第" + current_page + "页开始-------------------------");
			getData(url + current_page);
			System.out.println("-------------------第" + current_page + "页结束-------------------------");
		}
	}

	/**
	 * 获取总页数的方法
	 * 
	 * @return total_page_number（总页数）
	 * @throws IOException
	 */
	public static Integer getTotalPageNumber() throws IOException {
		// 后缀数字为几就访问的是第几页
		String url = "http://blog.csdn.net/qq_34337272/article/list/1";

		Document document = Jsoup.connect(url)
				.userAgent(
						"ozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36")
				.get();
		// "#papelist > span":id为pagelist的元素下的所有span元素
		// 获取的page类似33条 共3页，所以下面需要使用正则表达式匹配总页数
		String page = document.select("#papelist > span").text();
		// System.out.println("page:"+page);//输出结果：page:33条 共3页
		// 使用正则表达式匹配总页数
		Pattern pattern = Pattern.compile("(.*?)条 共(.*?)页");
		Matcher matcher = pattern.matcher(page);
		int total_count = 0, total_page_number = 0;
		if (matcher.find()) {
			total_count = Integer.parseInt(matcher.group(1)); // 总记录数
			total_page_number = Integer.parseInt(matcher.group(2));// 总页数
		} else {
			System.out.println("未获取到总页数！");
			System.exit(-1);
		}
		return total_page_number;
	}

	/***
	 * 获取数据
	 * 
	 * @param url
	 * @throws IOException
	 */
	public static void getData(String url) throws IOException {

		Document document = Jsoup.connect(url)
				.userAgent(
						"ozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36")
				.get();

		// 获取列表所在位置
		Elements items = document.select("#article_list > div");

		// System.out.println(items.size());

		// 遍历每一个博客
		for (Element item : items) {
			// 获取标题
			String title = item.select("h1 span a").text();
			// 获取标题地址
			String href = "http://blog.csdn.net" + item.select("h1 span a").attr("href");
			// 使用正则匹配其他数据 样例：2017-12-05 19:14 阅读(23) 评论(0)
			String other_info = item.select(".article_manage").text();
			Pattern pattern = Pattern.compile("(.*?) 阅读\\((.*?)\\) 评论\\((.*?)\\)");
			Matcher matcher = pattern.matcher(other_info);
			String pubdate = "";
			Integer read_number = 0, comment_number = 0;
			if (matcher.find()) {
				pubdate = matcher.group(1); // 发布时间
				read_number = Integer.parseInt(matcher.group(2)); // 阅读数
				comment_number = Integer.parseInt(matcher.group(3)); // 评论数
			}
			System.out.println("标题:" + title + " 发布时间:" + pubdate + " 阅读量:" + read_number + " 评论量:" + comment_number);
		}
	}
}
