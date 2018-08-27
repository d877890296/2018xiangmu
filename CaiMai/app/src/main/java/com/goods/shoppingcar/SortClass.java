package com.goods.shoppingcar;

import com.goods.details.ShoppingCarModel;
import com.hyf.tdlibrary.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class SortClass {
	/**
	 * 按日期 流量大小排序 冒泡排序
	 * 
	 * @param list
	 * @return
	 */
	public static List<ShoppingCarModel> SortHaveData(List<ShoppingCarModel> list) {
		List<ShoppingCarModel> sortData = new ArrayList<ShoppingCarModel>();
		String date = "";
		String secondDate = "";
		int size = list.size();
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				date = list.get(i).getGcId();
				secondDate = list.get(j).getGcId();
				long time_one = Long.parseLong(date);
				// 2014-06-30 05:41:04
				long time_two = Long.parseLong(secondDate);
				if (time_one < time_two) {
					ShoppingCarModel temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		sortData = list;
		return sortData;

	}

	

	/***
	 * 
	 * 去掉字符串中的非字符信息
	 * 
	 * 2017-04-26 18:31
	 * 
	 * 2017-04-26 18:28
	 * 
	 * @param str
	 * @return
	 */
	public static String getNewString(String str) {
		if (Tools.IsEmpty(str)) {
			return "0";
		}

		String newstr = "";
		Pattern p = Pattern.compile("[^0-9]");
		Matcher m = p.matcher(str);
		newstr = m.replaceAll("");
		return newstr;
	}

	/***
	 * 获得年月日的long数据
	 * 
	 * @param time
	 * @return
	 */
	public static Long getYmdLongTime(String time) {
		if (time == null || time.equals("")) {
			return 0L;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = sdf.parse(time);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return 0L;
	}
}
