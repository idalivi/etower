package org.etower.common.util;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomNumber {

	/**
	 * 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组
	 * 
	 * @param min
	 *            最小数字（包含该数）
	 * @param max
	 *            最大数字（不包含该数）
	 * @param size
	 *            指定产生随机数的个数
	 */
	public static int[] generateRandomNumber(int min, int max, int size) {
		if (min >= max || (max - min) < size) {
			return null;
		}
		// 种子你可以随意生成，但不能重复
		int[] seed = new int[max - min];

		for (int i = min; i < max; i++) {
			seed[i - min] = i;
		}
		int[] ranArr = new int[size];
		Random ran = new Random();

		for (int i = 0; i < size; i++) {
			int j = ran.nextInt(seed.length - i);
			ranArr[i] = seed[j];
			seed[j] = seed[seed.length - 1 - i];
		}
		return ranArr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ranArr = generateRandomNumber(100000, 999999, 99997);
		String str = Arrays.toString(ranArr);
		// AABB
		String regEx = "^\\d*(\\d)\\1(\\d)\\2\\d*$"; 
		System.out.println(str);
		boolean b = Pattern.compile(regEx).matcher("12778843").find();
		System.out.println(b);
	}

}
