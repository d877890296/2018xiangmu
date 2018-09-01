package com.goods.netrequst;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentHashMap;


import com.net.Des3;
import com.xfkc.caimai.application.MyApplication;
import com.xfkc.caimai.config.Constant;

public class UrlFactory {
	String rootUrl;
	// lexue.net.cn/lms

	public static String RootUrl =  Constant.BASE_URL;

	public UrlFactory(String rootUrl) {
		this.rootUrl = rootUrl;
		urlParams = new ConcurrentHashMap<String, String>();
	}

	protected ConcurrentHashMap<String, String> urlParams;

	public void put(String key, String value) {
		if (key != null && value != null) {
			urlParams.put(key, value);
		}

	}

	public static String GetUrl(String action, String args) {
		String result = RootUrl;
		result += (action + args);
		return result;
	}

	public static String GetUrl(String action, String... args) {
		String result = RootUrl;
		result += (action + "?");
		if (args == null || args.length <= 0) {
			return result;
		}
		String code = "code=";
		String result2 = "";
		int count = args.length / 2;
		try {
			for (int i = 0; i < count; i++) {
				String argvalue = null;
				if (i < count - 1) {
					argvalue = URLEncoder.encode(args[i * 2 + 1], "UTF-8");
					result2 += ("&" + args[i * 2] + "=" + argvalue);
				} else {
					result2 += messageFormat();
					argvalue = URLEncoder.encode(args[i * 2 + 1], "UTF-8");
					result2 += ("&" + args[i * 2] + "=" + argvalue);
				}
			}
			Logger.e("-result2--", result2);
			// code += Des3.encode(result2.substring(1, result2.length()));
			return result + result2.substring(1, result2.length());
			// return result + code;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	/***
	 * 
	 * 考试专用
	 * 
	 * @param args
	 * @return
	 */
	public static String PostUrl(String... args) {
		String result = "";
		int count = args.length / 2;
		try {
			for (int i = 0; i < count; i++) {
				String argvalue = null;
				if (i < count - 1) {
					argvalue = args[i * 2 + 1];
					result += ("&" + args[i * 2] + "=" + argvalue);
				} else {
					result += messageFormat();
					argvalue = args[i * 2 + 1];
					StringBuffer buffer = new StringBuffer();
					for (int j = 0; j < argvalue.length(); j++) {
						char ch = argvalue.charAt(j);
						if (ch == '&' || ch == '=') {
							buffer.append(ch);
						} else {
							buffer.append(ch);
						}
					}
					result += buffer.toString();
				}
			}

			// result = Des3.encode(result);
			// result = Des3.encode(result);
			return result;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/***
	 * 
	 * 其他
	 * 
	 * @param args
	 * @return
	 */
	public static String postMethods(String... args) {
		String result = "";
		if (args == null || args.length <= 0) {
			return result;
		}
		int count = args.length / 2;
		try {
			for (int i = 0; i < count; i++) {
				String argvalue = null;
				if (i < count - 1) {
					argvalue = URLEncoder.encode(args[i * 2 + 1], "UTF-8");
					result += ("&" + args[i * 2] + "=" + argvalue);
				} else {
					result += messageFormat();
					argvalue = URLEncoder.encode(args[i * 2 + 1], "UTF-8");
					result += ("&" + args[i * 2] + "=" + argvalue);
				}
			}

			result = Des3.encode(result);
			return result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String messageFormat() {
		String result = "&";
		result += ("messageFormat=json");
		result += ("&" + "v" + "=" + "" + MyApplication.getInstance().GetVersionCode() + ".0");
		return result;
	}
}
