package com.xfkc.caimai.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


import android.text.format.DateFormat;

/****
 * 随机id 有效性 处理 加密处理
 * 
 * @author HR
 *
 */
public class RandomID {

	private static int length = 10;
	private static String Id;
	private static StringBuffer sb = new StringBuffer();
	private static Set<String> arr = new HashSet<String>();
	private static Random random = new Random();
	private static String word = "0123456789ABCDEFGHIJKLMNOPQRSTVUWSYZabcdefghijklmnopqrstvuwsyz";
	private static String key = "HrSw";

	public static String getId(int length) {
		RandomID.length = length;
		if (RandomID.length > word.length()) {
			RandomID.length = 16;
		}
		randomNumber();
		try {
			String time = getlongtimeString();
			String fristKey = time.substring(0, time.length() / 2);
			String secondKey = time.substring(time.length() / 2, time.length());
			String tempStr=sb.toString();
			Id = tempStr+fristKey+key+secondKey;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Id;
	}

	/***
	 * 将字符串转为字符串时间戳
	 * 
	 * 
	 * @return
	 */
	public static String getlongtimeString() {
		String date = DateFormat.format("yyyy年MM月dd日hh时mm分ss秒", new Date()).toString();
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
		Date d = null;
		try {
			d = sdf.parse(date);
			long l = d.getTime();
			re_time = String.valueOf(l);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re_time;
	}

	public static void randomNumber() {
		int randomInt = random.nextInt(word.length());
		String ch = word.charAt(randomInt) + "";
		// if(arr.size()>=32){
		// return;
		// }else{
		// arr.add(ch);
		// randomNumber();
		// }
		if (!sb.toString().contains(ch)) {
			sb.append(ch);
			if (sb.toString().length() >= RandomID.length) {
				return;
			}
			randomNumber();
		} else {
			randomNumber();
		}
	}
}
