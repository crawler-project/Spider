package entertainment;

public class GetNumbersFromString{
	public static int GetNumbers(String str1) {

		String str2 = "";
		if (str1 != null && !"".equals(str1)) {
			for (int i = 0; i < str1.length(); i++) {
				if (str1.charAt(i) >= 48 && str1.charAt(i) <= 57) {
					str2 += str1.charAt(i);
				}
			}
		}
		int nums=Integer.parseInt(str2);
		return nums;
	}
}
