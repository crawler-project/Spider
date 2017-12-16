package entertainment;

public class StringSplit {

	public static void main(String[] args) {
		//测试
		StringBuffer stringBuffer=new StringBuffer("http://www.umei.cc/meinvtupian/meinvxiezhen/87271.htm");
		String string = "http://www.umei.cc/meinvtupian/meinvxiezhen/87271.htm";
		int dot=stringBuffer.lastIndexOf(".");
		String str=stringBuffer.substring(0, dot);
		System.out.println(str);
		//调用工具类的方法测试
		String str2=Umei_Meitupian_Utils.removeUrlExtensions(string);
		System.out.println(str2);
	}

}
