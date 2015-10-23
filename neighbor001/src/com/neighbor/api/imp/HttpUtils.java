package com.neighbor.api.imp;

import java.lang.reflect.Type;

import org.apache.http.Header;

import com.neighbor.update.Version;
import com.neighbor.utils.LogUtis;
import com.neighor.neighbor001.AppConfig;
import com.neighor.neighbor001.NeighborApplecation;


public class HttpUtils {

	
	public static abstract class BaseHttpResponseHandler<T> {

		public abstract void onSuccess(int statusCode, Header[] headers,
				String rawJsonResponse, T response);

		public abstract void onFailure(int statusCode, Header[] headers,
				Throwable throwable, String rawJsonData, T errorResponse);

		public void onProgress(int bytesWritten, int totalSize) {

		}

		public void onStart() {

		}

		public void onFinish() {

		}

	}
	/**
	 * 获取服务器端版本信息
	 * 
	 * @param type
	 * @param versionCode
	 *            应用版本号
	 * @param callback
	 */
	public static void getAppVersion(int type, int versionCode,String channel,
			final ICallBack<Version> callback) {
		/*RequestParams params = new RequestParams();
		params.put("type", type);
		params.put("versionCode", versionCode);
		params.put("channel", channel);

		Type t = new TypeToken<Version>() {
		}.getType();
		BaseHttpResponseHandler<Version> handler = new BaseHttpResponseHandler<Version>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Version response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Version errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(NeighborApplecation.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_SERVER_VERSION), params,
				handler, t);*/

	}
	
	public static String getAbsoluteUrl(String url) {
		StringBuffer sb = new StringBuffer(AppConfig.GET_API).append(url);
		return sb.toString();
	}


}