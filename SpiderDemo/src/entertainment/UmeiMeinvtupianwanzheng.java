package entertainment;

import java.io.IOException;
import java.util.ArrayList;

public class UmeiMeinvtupianwanzheng {

	public static void main(String[] args) throws IOException {
		ArrayList<String> content_href = UmeiMeitupianUtils.getContent_href("http://www.umei.cc/meinvtupian/");
		for (String string : content_href) {
			System.out.println(string);
		}
	}
}
