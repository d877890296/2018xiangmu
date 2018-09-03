package com.goods.netrequst;

import com.goods.sortlsitview.AjaxShopModel;
import com.net.http.AjaxCallBack;
import com.xfkc.caimai.application.MyApplication;

public class NetRequst {
	static NetRequst netRequst;


	// 实例化一次
	public synchronized static NetRequst getInstance() {
		if (null == netRequst) {
			netRequst = new NetRequst();
		}
		return netRequst;
	}



	// ======================================================

	/****
	 *
	 * @param callback
	 * @return
	 */
	public String getAllCategory(AjaxCallBack<AjaxShopModel> callback,String topCategoryId) {
		String TAG = "getAllCategory";
		String url = UrlFactory.GetUrl("/api/happycommune/getAllCategory","topCategoryId",topCategoryId);
		Logger.e(TAG, url);
		MyApplication.getInstance().getJson.getJson(url, AjaxShopModel.class, callback);
		return url;
	}


	/****
	 *二级列表
	 * @param callback
	 * @return
	 */
	public String getSecoCategory(AjaxCallBack<AjaxShopModel> callback,String pageNum,String pageSize,String categoryId) {
		String TAG = "getAllCategory";
		String url = UrlFactory.GetUrl("/api/happycommune/getSecoCategory","pageNum",pageNum,"pageSize",pageSize,"categoryId",categoryId);
		Logger.e(TAG, url);
		MyApplication.getInstance().getJson.getJson(url, AjaxShopModel.class, callback);
		return url;
	}

}
