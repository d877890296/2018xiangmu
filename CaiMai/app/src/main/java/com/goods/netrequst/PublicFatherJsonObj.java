package com.goods.netrequst;


import com.json.NetJsonFiled;

/****
 * 父类json 解析
 * 
 * @author lyy
 *
 */
public class PublicFatherJsonObj {
	@NetJsonFiled
	public int retCode;
	// 失败的提示信息
	@NetJsonFiled
	public String message;
	@NetJsonFiled
	public String data;
}
