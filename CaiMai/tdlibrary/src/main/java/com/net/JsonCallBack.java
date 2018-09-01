package com.net;

/***
 * 
 * time on 2017 ,07,17
 * 
 * in Beijing lyy
 * 
 * 
 * @author Administrator
 *
 */

public class JsonCallBack {
	static JsonCallBack jsonCallBack;

	public synchronized static JsonCallBack getInstance() {
		if (null == jsonCallBack) {
			jsonCallBack = new JsonCallBack();
		}
		return jsonCallBack;
	}

	public OnJsonCallBack onJsonCallBack;
	
	

	public OnJsonCallBack getOnJsonCallBack() {
		
		return onJsonCallBack;
	}

	public void setOnJsonCallBack(OnJsonCallBack onJsonCallBack) {
		this.onJsonCallBack = onJsonCallBack;
	}

	public interface OnJsonCallBack {
		public void jsonBack(String jsonStr);
	}

}
