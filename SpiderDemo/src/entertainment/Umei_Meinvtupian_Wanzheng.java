package entertainment;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * TODO 该类可以完整获取所有图片的链接地址
 * 
 * @author 寇爽
 * @date 2017年12月16日
 * @version 1.8
 */
public class Umei_Meinvtupian_Wanzheng {

	public static void main(String[] args) throws IOException {
		ArrayList<String> content_href = Umei_Meitupian_Utils.getContent_href("http://www.umei.cc/meinvtupian/");
		// content_href中的链接数
		int nums = content_href.size();
		for (int i = 0; i < 8; i++) {
			// int picturenums =
			// UmeiMeitupianUtils.getPicturenums(content_href.get(i));
			String urlwithoutExtensions = Umei_Meitupian_Utils.removeUrlExtensions(content_href.get(i));
			for (int j = 0; j < 8; j++) {
				String url = urlwithoutExtensions + "_" + j + ".htm";
				Document document = Jsoup.connect(url)
						.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
				String picture_href = document.select("#ArticleId22 img").attr("src");
				System.out.println(picture_href);

			}

		}
	}
}
