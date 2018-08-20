package com.xfkc.caimai.base;

import java.io.File;

import android.os.Environment;

/***
 * 
 * 
 * 
 * 配置文件
 * 
 * @author lyy
 *
 */
public class LmsConfig {

	/***
	 * 文件根目录
	 * 
	 */
	public static String rootFile = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/goodsApp"
			+ File.separator;
	/**
	 * 头像保存路径
	 */
	public static String userHeadFilePath = rootFile + "userphotos" + File.separator;
	/**
	 * 二维码保存路径
	 */
	public static String qrcodeFilePath = rootFile + "qrcodephotos" + File.separator;

	// 外网端口ip
	public static String NATIVE_IPPORT = "file:///android_asset";// 本地端口ip
	// 外网端口ip
	//public static String IpAddress ="192.168.1.172:8083/app2017";// 本地端口ip
	public static String IpAddress ="140.143.153.89:8085/app2018/app";// 本地端口ip
	public static String FileService ="http://140.143.153.89:8085/app2018/";// 本地端口ip
}
