package com.goods.netrequst;

import java.util.HashMap;
import java.util.Map;



import android.content.Context;
import android.widget.Toast;

import com.dev.customview.MyToast;

import com.hyf.tdlibrary.utils.Tools;

import com.net.http.AjaxCallBack;
import com.xfkc.caimai.application.MyApplication;

/****
 * 
 * 灵异解析json 类
 * 
 * 根据获取头部 键值 取得 子键值对应的值
 * 
 * date 2017,08,30 晚B1102
 * 
 * @author lyy
 *
 */
public class GetJson {
	static GetJson getJson;
	private Context mContext;
	private OnGetJsonCallBack onGetJsonCallBack;
	private Map<String, RequstHandler> mapRequst;
	public void setOnGetJsonCallBack(OnGetJsonCallBack onGetJsonCallBack) {
		this.onGetJsonCallBack = onGetJsonCallBack;
	}

	// 实例化一次
	public synchronized static GetJson getInstance() {
		if (null == getJson) {
			getJson = new GetJson();
		}
		return getJson;
	}

	public void init(Context mContext) {
		this.mContext = mContext;
		mapRequst = new HashMap<String, RequstHandler>();
	}

	/****
	 * 解析json
	 * 
	 * @param url
	 *            链接地址
	 * @param whoClass
	 *            注解 泛型类
	 * @param whoJsonObjCallback
	 *            返回解析结果
	 */
	public void getJson(String url, Class<?> whoClass,
			AjaxCallBack<?> whoJsonObjCallback) {
		// 测试数据
		// String jsonObj =
		// "{'code':200,'errorMsg':'','data':{'user':'liyuanyou'}}";
		// String url =
		// "http://192.168.1.206:8083/app2017/note/noteList?USER001=24c87890d28e42d08766474b304ada0a";
		// LesApp.getInstance().jsonHttp.getJsonObj(jsonObj,
		// PublicFatherJsonObj.class, publicFatherJsonObjCallback);

		// LesApp.getInstance().jsonHttp.getJson(url, PublicFatherJsonObj.class,
		// publicFatherJsonObjCallback);
		RequstHandler requstHandler = new RequstHandler(url, whoClass,
				whoJsonObjCallback);
		requstHandler.getHttp();
		mapRequst.put(url, requstHandler);
	}

	public void isExist(String url) {
		RequstHandler requstHandler = mapRequst.get(url);
		if (requstHandler != null) {
			requstHandler.reset();
		}
		requstHandler = null;
		System.gc();
	}

	public class RequstHandler {
		String url;
		/** 需要解析的类 **/
		public Class<?> whoClass;
		/** 需要解析的类的返回事件 **/
		public AjaxCallBack<?> whoJsonObjCallback;

		public RequstHandler(String url, Class<?> whoClass,
				AjaxCallBack<?> whoJsonObjCallback) {
			this.url = url;
			this.whoClass = whoClass;
			this.whoJsonObjCallback = whoJsonObjCallback;
		}

		public void reset() {

			url = null;
			whoClass = null;
			whoJsonObjCallback = null;

		}

		public void getHttp() {
			MyApplication.getInstance().jsonHttp.getJson(this.url,
					PublicFatherJsonObj.class, publicFatherJsonObjCallback);
		}

		/****
		 * 
		 * 积累的请求结果
		 * 
		 */
		public AjaxCallBack<PublicFatherJsonObj> publicFatherJsonObjCallback = new AjaxCallBack<PublicFatherJsonObj>() {
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				Failure("up：" + strMsg);
				isExist(url);
			}

			@Override
			public void onSuccess(PublicFatherJsonObj t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				TisNull(t);
				int code = t.retCode;
				if (code == 0) {
					String errorMsg = t.message;
					android.widget.Toast.makeText(mContext, errorMsg + "", Toast.LENGTH_LONG)
							.show();
					MyApplication.getInstance().jsonHttp.getJsonObj("{}", whoClass,
							whoJsonObjCallback);
				} else {
					String jsonObj = t.data;
					if (Tools.IsEmpty(jsonObj)) {
						android.widget.Toast.makeText(mContext,
							"", Toast.LENGTH_LONG).show();
						return;
					}
					MyApplication.getInstance().jsonHttp.getJsonObj(jsonObj, whoClass,
							whoJsonObjCallback);

				}

				isExist(url);
				return;
			}
		};

		/***
		 * 失败
		 */
		public void Failure(String strMsg) {
			android.widget.Toast.makeText(mContext,
					strMsg, Toast.LENGTH_LONG).show();
			if (onGetJsonCallBack != null) {
				onGetJsonCallBack.onState(false);
			}
		}

		/***
		 * 判断 T 泛型是否为空
		 * 
		 * @param t
		 */
		public void TisNull(Object t) {
			if (t == null) {
				MyToast.showMyToast(mContext,"错误", 0);
				if (onGetJsonCallBack != null) {
					onGetJsonCallBack.onState(false);
				}
				return;
			}
		}
	}

	public interface OnGetJsonCallBack {
		void onState(boolean isSuccess);
	}

}
