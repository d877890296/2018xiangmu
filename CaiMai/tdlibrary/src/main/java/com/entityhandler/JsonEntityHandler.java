
package com.entityhandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import com.json.BtJsonParse;
import com.net.JsonCallBack;

public class JsonEntityHandler {

	Class<?> clazz;

	public void SetClassZ(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Object handleEntity(HttpEntity entity, EntityCallBack callback, String charset) throws IOException {
		if (entity == null)
			return null;

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];

		long count = entity.getContentLength();
		long curCount = 0;
		int len = -1;
		InputStream is = entity.getContent();
		while ((len = is.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
			curCount += len;
			if (callback != null)
				callback.callBack(count, curCount, false);
		}
		if (callback != null)
			callback.callBack(count, curCount, true);
		byte[] data = outStream.toByteArray();
		outStream.close();
		is.close();
		String jsonString = new String(data, charset);
		if (JsonCallBack.getInstance().getOnJsonCallBack() != null) {
			JsonCallBack.getInstance().getOnJsonCallBack().jsonBack(jsonString);
		}
		Object jsonObject = BtJsonParse.parseJsonString(jsonString, clazz);

		return jsonObject;
	}

	public Object handleRelust(String params, EntityCallBack callback, String charset) throws IOException {
		if (params == null)
			return null;

		if (callback != null)
			callback.callBack(0, 100, true);
		byte[] data = params.getBytes();

		String jsonString = new String(data, charset);
		if (JsonCallBack.getInstance().getOnJsonCallBack() != null) {
			JsonCallBack.getInstance().getOnJsonCallBack().jsonBack(jsonString);
		}
		Object jsonObject = BtJsonParse.parseJsonString(jsonString, clazz);

		return jsonObject;
	}

}
