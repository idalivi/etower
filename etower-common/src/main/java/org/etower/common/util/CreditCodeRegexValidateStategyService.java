package org.etower.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

public class CreditCodeRegexValidateStategyService {

	private static List<String> levitPatterns;

	static synchronized private void init() {
		if (levitPatterns == null) {
			levitPatterns = new ArrayList<String>();
		} else {
			return;
		}
		// 手机号、生日号、跟公司业务相关的号码
//		levitPatterns.add("^(0|13|15|18|168|400|800)[0-9]*$");
//		levitPatterns.add("^\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$");
//		levitPatterns
//				.add("^\\d*(1688|2688|2088|2008|5188|10010|10001|666|888|668|686|688|866|868|886|999)\\d*$");
//		// 重复号码，镜子号码
//		levitPatterns.add("^(<a>\\d)(\\d)(\\d)\\1\\2\\3$");
//		levitPatterns.add("^(\\d)(\\d)(\\d)\\3\\2\\1$");
//		// AABB
//		levitPatterns.add("^\\d*(\\d)\\1(\\d)\\2\\d*$");
//		// AAABBB
//		levitPatterns.add("^\\d*(\\d)\\1\\1(\\d)\\2\\2\\d*$");
//		// ABABAB
//		levitPatterns.add("^(\\d)(\\d)\\1\\2\\1\\2\\1\\2$");
//		// ABCABC
//		levitPatterns.add("^(\\d)(\\d)(\\d)\\1\\2\\3$");
//		// ABBABB
//		levitPatterns.add("^(\\d)(\\d)\\2\\1\\2\\2$");
//		// AABAAB
//		levitPatterns.add("^(\\d)\\1(\\d)\\1\\1\\2$");
//
//		// 4-8 位置重复
//		levitPatterns.add("^\\d*(\\d)\\1{2,}\\d*$");
//		// 4位以上 位递增或者递减（7890也是递增）
//		levitPatterns
//				.add("(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)|9(?=0)){2,}|(?:0(?=9)|9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){2,})\\d");
//		
//
//		// 不能以 518 、918 结尾
//		levitPatterns.add("^[0-9]*(518|918)$");
		
		// 匹配6位顺增
		levitPatterns.add("(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){5}\\d");
		// 匹配6位顺降
		levitPatterns.add("(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){5}\\d");
		// 匹配6位顺增或顺降
		levitPatterns.add("(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){5}|(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){5})\\d");		
		// 匹配4-9位连续的数字
		levitPatterns.add("(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){3,}|(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){3,})\\d");
		// 匹配4位以上的重复数字
		levitPatterns.add("([\\d])\\1{3,}");
		// 匹配日期类型的数字
//		levitPatterns.add("(19|20)[\\d]{2}(1[0-2]|0?[1-9])(31|2[0-9]|1[0-9]|0?[0-9])");
		// 手机号码类
//		levitPatterns.add("(13[0-9]|15[0-9]|18[0-9])([\\d]{2,4}){2}");
		// 匹配AABBB类型的
//		levitPatterns.add("([\\d])\\1{1,}([\\d])\\2{2,}");
		levitPatterns.add("([0-3]|[5-9])\\1{1,}([0-3]|[5-9])\\2{2,}");
		// 匹配ABBCABB类型的
//		levitPatterns.add("(([\\d]){1,}([\\d]){1,})\\1{1,}");
		levitPatterns.add("(([0-3]|[5-9]){1,}([0-3]|[5-9]){1,})\\1{1,}");
		// 匹配AABBC,ABCCEE类型的
//		levitPatterns.add("([\\d])\\1{1,}([\\d])\\2{1,}");
		levitPatterns.add("([0-3]|[5-9])\\1{1,}([0-3]|[5-9])\\2{1,}");
	}

	public static boolean isAllow(String input) {
		Assert.notNull(input);
		return !RegexUtil.contains(input, levitPatterns);
	}

	static {
		init();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = RandomNumber.generateRandomNumber(10000000, 99999999, 100);
		int x = 0,y = 0;
		for(int i : array) {
			if(isAllow(String.valueOf(i))) {
				x++;
				System.out.println(isAllow(String.valueOf(i)));
				System.out.println("允许："+i);
				System.out.println("=============================");
			} else {
				y++;
				System.out.println(isAllow(String.valueOf(i)));
				System.out.println("不允许："+i);
				System.out.println("=============================");
			}
		}
		System.out.println("允许："+x);
		System.out.println("不允许："+y);
	}
}
