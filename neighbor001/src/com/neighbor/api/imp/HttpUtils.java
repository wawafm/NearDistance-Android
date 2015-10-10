/**
 * 
 *//*
*//**
 * @author xzh
 *
 *//*
package com.neighbor.api.imp;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.Header;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ZZHttpClient;
import com.sina.weibo.sdk.utils.NetworkHelper;

*//**
 * 
 * @author fighting
 * @email huazhao520@163.com
 * @date 2015年4月16日
 * @company zz
 * @version 1.0
 * @description
 *//*
public class HttpUtils {

	public static final String ENCODING_MP3 = "mp31";
	public static final String ENCODING_OGG = "ogg2";

	private static ZZHttpClient httpClient = new ZZHttpClient();
	private static RequestCache mCache = RequestCache.get(WawaApplication
			.getInstance());

	public static String getAbsoluteUrl(String url) {
		StringBuffer sb = new StringBuffer(AppConfig.GET_API).append(url);
		return sb.toString();
	}

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

	// ----------------------------------------------以上接口，版本更新以后，全部弃用

	*//**
	 * http请求接口 get
	 * 
	 * @param ctx
	 *            上下文
	 * @param url
	 *            CmsSite/a/sys/user/mfavorites
	 * @param params
	 *            请求参数
	 * @param responseHandler
	 *            回调接口
	 * @param t
	 *            gson type 例：Type t = new TypeToken<List<UserInfo>>() {
	 *            }.getType();
	 * @return
	 *//*

	public static <T> RequestHandle get(final Context ctx, final String url,
			RequestParams params,
			final BaseHttpResponseHandler<T> responseHandler, final Type t) {
		// 平台标识
		params.put("platform", "wwandroid");

		// 获得登录的用户id
		String username = SharePreferenceUtil.getLoginName(ctx);
		params.put("uid",username);
		
		LogUtis.log("posturl:===" + getFullUri(url, params));
		System.out.println("posturl:===" + getFullUri(url, params));
		
		RequestHandle ret = httpClient.get(ctx, url, params,
				new BaseJsonHttpResponseHandler<T>() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String rawJsonResponse, T response) {
						LogUtis.log("onSuccess:" + rawJsonResponse);
						mCache.put(url, rawJsonResponse);
						responseHandler.onSuccess(statusCode, headers,
								rawJsonResponse, response);

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable throwable, String rawJsonData,
							T errorResponse) {
						LogUtis.log("----raw:" + rawJsonData);
						if (0 == statusCode) {
							String result = mCache.getAsString(url);
							if (result != null) {
								Gson gson = new Gson();

								T res = gson.fromJson(result, t);
								responseHandler.onSuccess(1, null, rawJsonData,
										res);

							} else {
								responseHandler.onFailure(statusCode, headers,
										throwable, rawJsonData, errorResponse);
							}
						} else
							responseHandler.onFailure(statusCode, headers,
									throwable, rawJsonData, errorResponse);

					}

					@Override
					protected T parseResponse(String rawJsonData,
							boolean isFailure) throws Throwable {

						Gson gson = new Gson();

						T ret = gson.fromJson(rawJsonData, t);

						return ret;
					}

					@Override
					public void onCancel() {

						super.onCancel();
					}

					@Override
					public void onFinish() {

						super.onFinish();
						responseHandler.onFinish();
					}

					@Override
					public void onRetry(int retryNo) {

						super.onRetry(retryNo);
					}

					@Override
					public void onStart() {

						super.onStart();
						responseHandler.onStart();
					}

				});

		return ret;
	}

	private static String getFullUri(String url, RequestParams params) {
		StringBuilder sb = new StringBuilder(url);
		sb.append(params.toString());
		return sb.toString();

	}

	*//**
	 * http请求接口 post
	 * 
	 * @param ctx
	 *            上下文
	 * @param url
	 *            CmsSite/a/sys/user/mfavorites
	 * @param params
	 *            请求参数
	 * @param responseHandler
	 *            回调接口
	 * @param t
	 *            gson type 例：Type t = new TypeToken<List<UserInfo>>() {
	 *            }.getType();
	 * @return
	 *//*
	public static <T> RequestHandle post(final Context ctx, final String url,
			RequestParams params,

			final BaseHttpResponseHandler<T> responseHandler, final Type t) {

		// 平台标识
		params.put("platform", "wwandroid");
		LogUtis.log("posturl:===" + getFullUri(url, params));
		String username = SharePreferenceUtil.getLoginName(ctx);

		params.put("uid", TextUtils.isEmpty(username) ? null : username);

		RequestHandle ret = httpClient.post(ctx, url, params,

		new BaseJsonHttpResponseHandler<T>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, T response) {
				
				LogUtis.log("resp:" + rawJsonResponse);
				mCache.put(url, rawJsonResponse);
				responseHandler.onSuccess(statusCode, headers, rawJsonResponse,
						response);

			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				// TODO Auto-generated method stub
				super.onProgress(bytesWritten, totalSize);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData, T errorResponse) {

				if (0 == statusCode) {
					String result = mCache.getAsString(url);
					if (result != null) {
						Gson gson = new Gson();

						T res = gson.fromJson(result, t);
						responseHandler.onSuccess(1, null, result, res);

					} else {
						responseHandler.onFailure(statusCode, headers,
								throwable, rawJsonData, errorResponse);
					}
				} else

					responseHandler.onFailure(statusCode, headers, throwable,
							rawJsonData, errorResponse);
			}

			@Override
			protected T parseResponse(String rawJsonData, boolean isFailure)
					throws Throwable {
				LogUtis.log("rawdata:" + rawJsonData);
				Gson gson = new Gson();

				T ret = gson.fromJson(rawJsonData, t);

				return ret;
			}

			@Override
			public void onCancel() {

				super.onCancel();
			}

			@Override
			public void onFinish() {

				super.onFinish();
				responseHandler.onFinish();
			}

			@Override
			public void onRetry(int retryNo) {

				super.onRetry(retryNo);
			}

			@Override
			public void onStart() {

				super.onStart();
				responseHandler.onStart();
			}

		});

		return ret;
	}

	*//**
	 * 收藏
	 * 
	 * @param ctx
	 * @param obj
	 * @param callback
	 *//*
	public static void collection(Context ctx, ICollectionShareObject obj,
			ICallBack<Result> callback) {
		if (Util.isVisitors(ctx)) {
			Util.startLogin(ctx);
		} else {

			String username = SharePreferenceUtil.getLoginUser(ctx).getId();

			collection(username, obj.getId(), obj.getCType(),
					obj.getSourceType(), "", "", "", "", 0, callback);
		}

	}

	*//**
	 * 分享
	 * 
	 * @param ctx
	 * @param obj
	 * @param callback
	 *//*
	public static void shareToMusicCircle(Context ctx,
			ICollectionShareObject obj, String title, String area,
			String content, String fphoto, int iszy, ICallBack<Result> callback) {
		if (Util.isVisitors(ctx)) {
			Util.startLogin(ctx);
		} else {

			String username = SharePreferenceUtil.getLoginUser(ctx).getId();

			collection(username, obj.getId(), obj.getStype(),
					obj.getSourceType(), title, content, area, fphoto, iszy,
					callback);
		}

	}

	*//**
	 * 取消收藏
	 * 
	 * @param ctx
	 * @param obj
	 * @param callback
	 *//*
	public static void cancelCollection(Context ctx,
			ICollectionShareObject obj, final ICallBack<Result> callback) {
		if (Util.isVisitors(ctx)) {
			Util.startLogin(ctx);

		} else {
			String username = SharePreferenceUtil.getLoginUser(ctx).getId();

			cancelCollection(username, obj.getId(), obj.getSourceType(),
					obj.getCType(), callback);
		}
	}

	*//**
	 * 收藏
	 * 
	 * @param username
	 * @param sourceid
	 * @param isShare
	 * @param sourceType
	 * @param title
	 * @param remarks
	 * @param area
	 * @param fphoto
	 * @param iszy
	 *//*
	private static void collection(String username, int sourceid, int isShare,
			int sourceType, String title, String remarks, String area,
			String fphoto, int iszy, final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();
		params.put("createBy.id", username);
		params.put("sourceid", sourceid);
		params.put("isshare", isShare);
		params.put("sourcetype", sourceType);
		params.put("title", title);
		params.put("remarks", remarks);
		params.put("area", area);
		params.put("fphoto", fphoto);
		params.put("iszy", iszy);

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};

		if (NetworkHelper.isNetworkAvailable(WawaApplication.getInstance())) {
			HttpUtils.get(WawaApplication.getInstance(),
					HttpUtils.getAbsoluteUrl(AppConfig.COLLECTION_INTERFACE),
					params, handler, t);
		} else {
			LogUtis.showTast(WawaApplication.getInstance(), "网络不给力");
		}

	}

	*//**
	 * 取消收藏
	 * 
	 * @param sourceid
	 * @param createID
	 * @param sourceType
	 * @param isShare
	 * @param delFlag
	 *            ?sourceid=6140&createBy.id=5003&isshare=1&sourcetype=0
	 *//*
	private static void cancelCollection(String username, int sourceid,
			int sourceType, int isShare, final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();

		params.put("sourceid", sourceid);
		params.put("isshare", isShare);
		params.put("sourcetype", sourceType);
		params.put("createBy.id", username);

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {
				if (callback != null)
					callback.onResult(response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils
				.get(WawaApplication.getInstance(), HttpUtils
						.getAbsoluteUrl(AppConfig.CANCEL_COLLECTION_INTERFACE),
						params, handler, t);
	}

	*//**
	 * 关注好友
	 * 
	 * @param uid
	 *            登录用户id
	 * @param adduser
	 *            关注的用户id
	 * @param callback
	 *//*
	public static void focusFriend(String uid, String adduser,
			final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();

		params.put("userdetail.id", uid);
		params.put("juser.id", adduser);

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.ADD_FRIEND_INTERFACE),
				params, handler, t);

	}

	*//**
	 * 取消关注好友
	 * 
	 * @param uid
	 *            登录用户id
	 * @param adduser
	 *            取消的用户id
	 * @param callback
	 *//*
	public static void cancelFocusFriend(String uid, String adduser,
			final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();

		params.put("userdetail.id", uid);
		params.put("juser.id", adduser);

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.DELETE_FRIEND_INTERFACE),
				params, handler, t);

	}

	*//**
	 * 用户修改
	 * 
	 * @param user
	 * @param callback
	 *//*
	public static void modifyUserInfo(UserInfo user,
			final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();

		params.put("id", user.getId());
		params.put("genter", user.getGenter());
		params.put("username", user.getName());
		params.put("area", user.getAddr());
		params.put("sign", user.getSign());
		params.put("age", user.getAge());
		params.put("emotion", user.getEmotion());
		params.put("constellation", user.getConstellation());
		params.put("dictid", user.getHttpArgsString());
		params.put("pimg", user.getPimg());

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.SAVE_USERINFO_INTERFACE),
				params, handler, t);

	}

	*//**
	 * @param path
	 *            要上传的文件路径
	 * @param url
	 *            服务端接收URL
	 * @throws Exception
	 *//*
	public static void uploadFile(Context ctx, String path, String url,
			final ICallBack<UploadFileResult[]> callback) throws Exception {
		final ProgressDialog dlg = new ProgressDialog(ctx);
		dlg.setMessage("正在上传...");
		File file = new File(path);
		if (file.exists() && file.length() > 0) {

			RequestParams params = new RequestParams();
			params.put("uploadfile", file);
			BaseHttpResponseHandler<UploadFileResult[]> handler = new BaseHttpResponseHandler<UploadFileResult[]>() {

				@Override
				public void onSuccess(int statusCode, Header[] headers,
						String rawJsonResponse, UploadFileResult[] response) {
					if (callback != null)
						callback.onResult(response);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,
						Throwable throwable, String rawJsonData,
						UploadFileResult[] errorResponse) {
					dlg.dismiss();
					if (callback != null)
						callback.onError(throwable);

				}

				@Override
				public void onStart() {

					super.onStart();
					dlg.show();
				}

				@Override
				public void onFinish() {

					super.onFinish();
					dlg.dismiss();
				}

			};
			Type t = new TypeToken<UploadFileResult[]>() {
			}.getType();
			post(ctx, url, params, handler, t);

		} else {
			// Toast.makeText(mContext, "文件不存在", Toast.LENGTH_LONG).show();
		}

	}

	*//**
	 * 获取用户标签字典
	 * 
	 * @param callback
	 *//*
	public static void getUserLabels(final ICallBack<UserLabel[]> callback) {
		RequestParams params = new RequestParams();

		Type t = new TypeToken<UserLabel[]>() {
		}.getType();
		BaseHttpResponseHandler<UserLabel[]> handler = new BaseHttpResponseHandler<UserLabel[]>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, UserLabel[] response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					UserLabel[] errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.USER_LABELS_INTERFACE),
				params, handler, t);

	}

	*//**
	 * 挖友大厅 (支持根据用户查询)
	 * 
	 * @param username
	 * 查找的用户名 可选
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 *//*
	public static void findUser(String username, int pageNo, int pageSize,
			final ICallBack<UserInfo[]> callback) {
		RequestParams params = new RequestParams();
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		params.put("username", username);

		Type t = new TypeToken<UserInfo[]>() {
		}.getType();
		BaseHttpResponseHandler<UserInfo[]> handler = new BaseHttpResponseHandler<UserInfo[]>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, UserInfo[] response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					UserInfo[] errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_USER), params, handler,
				t);

	}

	*//**
	 * 反馈
	 * 
	 * @param name
	 * @param content
	 * @param email
	 * @param callback
	 *//*
	public static void feedback(String name, String content, String email,
			final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();
		params.put("name", name);
		params.put("content", content);
		params.put("email", email);
		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {

				callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.FEEDBACK_INTERFACE), params,
				handler, t);

	}

	*//**
	 * 获取服务器端版本信息
	 * 
	 * @param type
	 * @param versionCode
	 *            应用版本号
	 * @param callback
	 *//*
	public static void getAppVersion(int type, int versionCode,String channel,
			final ICallBack<Version> callback) {
		RequestParams params = new RequestParams();
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
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_SERVER_VERSION), params,
				handler, t);

	}

	*//**
	 * 获取音乐圈
	 * 
	 * @param userID
	 *            用户id
	 * @param isShare
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 *//*
	public static void getMusicShares(String userID, int isShare, int pageNo,
			int pageSize, final ICallBack<List<Share>> callback) {
		RequestParams params = new RequestParams();
		params.put("createBy.id", userID);
		params.put("isshare", isShare);
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		Type t = new TypeToken<List<Share>>() {
		}.getType();
		BaseHttpResponseHandler<List<Share>> handler = new BaseHttpResponseHandler<List<Share>>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, List<Share> response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					List<Share> errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_MUSIC_SHARES), params,
				handler, t);

	}

	*//**
	 * 获取某个用户的音乐圈
	 * 
	 * @param userID
	 * @param isShare
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 *//*
	public static void getMusicSharesByUser(String userID, int isShare,
			int pageNo, int pageSize, final ICallBack<List<Share>> callback) {
		RequestParams params = new RequestParams();
		params.put("createBy.id", userID);
		params.put("isshare", isShare);
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);

		Type t = new TypeToken<List<Share>>() {
		}.getType();
		BaseHttpResponseHandler<List<Share>> handler = new BaseHttpResponseHandler<List<Share>>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, List<Share> response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					List<Share> errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_MUSIC_SHARES_BY_USER),
				params, handler, t);

	}

	*//**
	 * 获取音乐圈广场
	 * 
	 * @param userID
	 * @param isShare
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 *//*
	public static void getMusicPiazza(int pageNo, int pageSize,
			final ICallBack<List<Share>> callback) {
		RequestParams params = new RequestParams();

		params.put("isshare", 0);
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);

		Type t = new TypeToken<List<Share>>() {
		}.getType();
		BaseHttpResponseHandler<List<Share>> handler = new BaseHttpResponseHandler<List<Share>>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, List<Share> response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					List<Share> errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(), HttpUtils
				.getAbsoluteUrl(AppConfig.GET_MUSIC_SHARES_GUANGCHANG_BY_USER),
				params, handler, t);

	}

	*//**
	 * 音乐圈回复
	 * 
	 * @param fid
	 * @param userId
	 * @param content
	 * @param rid
	 * @param callback
	 *//*
	public static void musicReply(String fid, String userId, String content,
			String rid, final ICallBack<Comment> callback) {
		RequestParams params = new RequestParams();
		params.put("createBy.id", userId);
		params.put("fid", fid);
		params.put("rid", rid);
		params.put("content", content);

		Type t = new TypeToken<Comment>() {
		}.getType();
		BaseHttpResponseHandler<Comment> handler = new BaseHttpResponseHandler<Comment>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Comment response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Comment errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.MUSIC_SHARE_REPLY), params,
				handler, t);

	}

	*//**
	 * 7.7文章发布评论http://192.168.199.228:8080/CmsSite/a/cms/comment2014/msave?fid=
	 * 508&title=1&createBy.id=3292&content=old%20woman&categoryId=29
	 * 
	 * 7.8 文章评论回复
	 * http://192.168.199.228:8080/CmsSite/a/cms/comment2014/msave?fid
	 * =508&title=1&createBy.id=1950&content=old%20woman&categoryId=29&rid=1
	 * 文章回复
	 *//*

	public static void articleReply(String fid, String userId, String title,
			String content, String rid, String cid,
			final ICallBack<AlbumReplyResult> callback) {
		RequestParams params = new RequestParams();
		params.put("createBy.id", userId);
		params.put("fid", fid);
		params.put("rid", rid);
		params.put("content", content);
		params.put("categoryId", cid);
		params.put("title", title);

		Type t = new TypeToken<AlbumReplyResult>() {
		}.getType();
		BaseHttpResponseHandler<AlbumReplyResult> handler = new BaseHttpResponseHandler<AlbumReplyResult>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, AlbumReplyResult response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					AlbumReplyResult errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.ARTICLE_REPLY), params,
				handler, t);

	}

	*//**
	 * 期刊评论
	 * 
	 * @param uid
	 *            用户id
	 * @param albumID
	 *            期刊id
	 * @param content
	 *            评论内容
	 * @param callback
	 *            回调
	 *//*
	public static void albumReply(String uid, String albumID, String content,
			final ICallBack<AlbumReplyResult> callback) {
		RequestParams params = new RequestParams();
		params.put("createBy.id", uid);
		params.put("contentId", albumID);
		params.put("content", content);

		Type t = new TypeToken<AlbumReplyResult>() {
		}.getType();
		BaseHttpResponseHandler<AlbumReplyResult> handler = new BaseHttpResponseHandler<AlbumReplyResult>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, AlbumReplyResult response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					AlbumReplyResult errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.MAGAZINE_REPLY_URL), params,
				handler, t);

	}

	public static void noticeReply(String uid, String id, String content,
			final ICallBack<AlbumReplyResult> callback) {
		RequestParams params = new RequestParams();
		params.put("create_user_id", uid);
		params.put("content_id", id);
		params.put("content", content);

		Type t = new TypeToken<AlbumReplyResult>() {
		}.getType();
		BaseHttpResponseHandler<AlbumReplyResult> handler = new BaseHttpResponseHandler<AlbumReplyResult>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, AlbumReplyResult response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					AlbumReplyResult errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				AppConfig.NOTICE_REPLY_URL, params, handler, t);

	}

	*//**
	 * 音乐圈点赞
	 * 
	 * @param userId
	 * @param fid
	 * @param callback
	 *//*
	public static void praiseShare(String userId, String fid,
			final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();
		params.put("createBy.id", userId);
		params.put("fid", fid);

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};

		if (NetworkHelper.isNetworkAvailable(WawaApplication.getInstance())) {
			HttpUtils.post(WawaApplication.getInstance(),
					HttpUtils.getAbsoluteUrl(AppConfig.MUSIC_SHARE_PRAISE),
					params, handler, t);
		} else {
			LogUtis.showTast(WawaApplication.getInstance(), "网络不给力");
		}

	}

	*//**
	 * 删除评论
	 * 
	 * @param ids
	 *            fid (123,431)
	 * @param isShare
	 *            0
	 * @param callback
	 *//*
	public static void deleteComment(String url,
			final ICallBack<Result> callBack) {
		RequestParams params = new RequestParams();
		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {
				// TODO Auto-generated method stub
				if (callBack != null)
					callBack.onResult(response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callBack != null)
					callBack.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(), url, params, handler, t);
	}

	*//**
	 * 删除分享
	 * 
	 * @param ids
	 *            fid (123,431)
	 * @param isShare
	 *            0
	 * @param callback
	 *//*
	public static void deleteShares(String ids, int isShare,
			final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();

		params.put("ids", ids);
		params.put("isShare", isShare);

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		// 参数：Instance,拼接后的URL,传过来的参数，handle，type
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.DELETE_SHARE), params,
				handler, t);

	}

	*//**
	 * 根据ids获取音乐
	 * 
	 * @param ids
	 * @param callback
	 *//*
	public static void getTracksByTracksId(String ids,
			final ICallBack<Track[]> callback) {

		RequestParams params = new RequestParams();

		params.put("ids", ids);

		Type t = new TypeToken<Track[]>() {
		}.getType();
		BaseHttpResponseHandler<Track[]> handler = new BaseHttpResponseHandler<Track[]>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Track[] response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Track[] errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_TRACK_BY_ID), params,
				handler, t);

	}

	*//**
	 * 注册.登录
	 * 
	 * @param loginName
	 * @param openID
	 * @param genter
	 * @param userclient
	 * @param clientinfo
	 * @param callback
	 *//*
	public static void regAndLogin(String loginName, String openID,
			String unionid, String genter, String userclient,
			String clientinfo, String pimg, final ICallBack<UserInfo> callback) {
		RequestParams params = new RequestParams();

		params.put("loginName", loginName);
		params.put("openid", openID);
		params.put("genter", genter);
		params.put("userclient", userclient);
		params.put("clientinfo", clientinfo);
		params.put("pimg", pimg);
		params.put("unionid", unionid);

		Type t = new TypeToken<UserInfo>() {
		}.getType();
		BaseHttpResponseHandler<UserInfo> handler = new BaseHttpResponseHandler<UserInfo>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, UserInfo response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					UserInfo errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.REGISTER_AND_LOGIN), params,
				handler, t);

	}

	*//**
	 * 获取微信用户信息
	 * 
	 * @param token
	 * @param openid
	 * @param callback
	 *//*
	public static void getWeixinUserinfo(String token, String openid,
			final ICallBack<WeixinUser> callback) {
		RequestParams params = new RequestParams();

		params.put("access_token", token);
		params.put("openid", openid);

		Type t = new TypeToken<WeixinUser>() {
		}.getType();
		BaseHttpResponseHandler<WeixinUser> handler = new BaseHttpResponseHandler<WeixinUser>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, WeixinUser response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					WeixinUser errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				"https://api.weixin.qq.com/sns/userinfo", params, handler, t);

	}

	*//**
	 * 匿名登录
	 * 
	 * @param userclient
	 * @param callback
	 *//*
	public static void anonymousLogin(String userclient,
			final ICallBack<UserInfo> callback) {
		RequestParams params = new RequestParams();

		params.put("id", 14);
		params.put("userclient", userclient);

		Type t = new TypeToken<UserInfo>() {
		}.getType();
		BaseHttpResponseHandler<UserInfo> handler = new BaseHttpResponseHandler<UserInfo>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, UserInfo response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					UserInfo errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.LOGIN_GUEST), params,
				handler, t);

	}

	*//**
	 * 匿名登录
	 * 
	 * @param userclient
	 * @param callback
	 *//*
	public static void getWeiXinToken(String appid, String secret, String code,
			String grant_type, final ICallBack<WXAccessToken> callback) {
		RequestParams params = new RequestParams();

		params.put("appid", appid);
		params.put("secret", secret);
		params.put("code", code);
		params.put("grant_type", grant_type);

		Type t = new TypeToken<WXAccessToken>() {
		}.getType();
		BaseHttpResponseHandler<WXAccessToken> handler = new BaseHttpResponseHandler<WXAccessToken>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, WXAccessToken response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					WXAccessToken errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				"https://api.weixin.qq.com/sns/oauth2/access_token", params,
				handler, t);

	}

	*//**
	 * 根据id获取分享
	 * 
	 * @param fid
	 * @param callback
	 *//*
	public static void getShareByID(String fid, final ICallBack<Share> callback) {
		RequestParams params = new RequestParams();

		params.put("fid", fid);

		Type t = new TypeToken<Share>() {
		}.getType();
		BaseHttpResponseHandler<Share> handler = new BaseHttpResponseHandler<Share>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Share response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData, Share errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_SHARE_BY_ID), params,
				handler, t);

	}

	*//**
	 * 期刊点赞
	 * 
	 * @param uid
	 * @param id
	 * @param callback
	 *//*
	public static void praiseMagazine(String uid, String id,
			final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();

		params.put("id", id);
		params.put("uid", uid);

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.PRAISE_MAGZ), params,
				handler, t);

	}

	*//**
	 * 获取点赞用户列表
	 * 
	 * @param contentid
	 * @param type
	 * @param callback
	 *//*
	public static void getFollowers(int contentid, int type,
			final ICallBack<List<UserInfo>> callback) {
		RequestParams params = new RequestParams();

		params.put("contentId", contentid);
		params.put("type", type);

		Type t = new TypeToken<List<UserInfo>>() {
		}.getType();
		BaseHttpResponseHandler<List<UserInfo>> handler = new BaseHttpResponseHandler<List<UserInfo>>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, List<UserInfo> response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					List<UserInfo> errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_FOLLOWS), params,
				handler, t);

	}

	*//**
	 * 上传通讯录
	 * 
	 * @param uid
	 *            用户uid
	 * @param contacts
	 *            电话号特征码(3244,1431,5222)
	 * @param callback
	 *//*

	public static void uploadContacts(String uid, String contacts,
			final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();

		params.put("uid", uid);
		params.put("phones", contacts);

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.UPLOAD_CONTACTS), params,
				handler, t);

	}

	*//**
	 * 获取通讯录好友
	 * 
	 * @param uid
	 * @param callback
	 *//*
	public static void getAdressBookFriends(String uid,
			final ICallBack<List<UserInfo>> callback) {
		RequestParams params = new RequestParams();

		params.put("uid", uid);

		Type t = new TypeToken<List<UserInfo>>() {
		}.getType();
		BaseHttpResponseHandler<List<UserInfo>> handler = new BaseHttpResponseHandler<List<UserInfo>>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, List<UserInfo> response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					List<UserInfo> errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_CONTACT_FRIENDS),
				params, handler, t);

	}

	*//**
	 * 获取好玩类别列表
	 * 
	 * @param callback
	 *//*
	public static void getArticleCategorys(
			final ICallBack<List<ArticleCategory>> callback) {
		RequestParams params = new RequestParams();

		Type t = new TypeToken<List<ArticleCategory>>() {
		}.getType();
		BaseHttpResponseHandler<List<ArticleCategory>> handler = new BaseHttpResponseHandler<List<ArticleCategory>>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, List<ArticleCategory> response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					List<ArticleCategory> errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_ARTICLE_CATEGORY),
				params, handler, t);

	}

	*//**
	 * 文章点赞
	 * 
	 * @param id
	 *            文章id
	 * @param uid
	 *            用户id
	 * @param callback
	 *//*

	public static void praiseArticle(int id, String uid,
			final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();

		params.put("id", id);
		params.put("uid", uid);

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.PRAISE_ARTICLE), params,
				handler, t);

	}

	*//**
	 * 获取用户详细
	 * 
	 * @param id
	 * @param callback
	 *//*
	public static void getUserInfo(String id, final ICallBack<UserInfo> callback) {

		RequestParams params = new RequestParams();

		params.put("id", id);

		Type t = new TypeToken<UserInfo>() {
		}.getType();
		BaseHttpResponseHandler<UserInfo> handler = new BaseHttpResponseHandler<UserInfo>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, UserInfo response) {
				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					UserInfo errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};

		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.USERINFO_INTERFACE), params,
				handler, t);

	}

	*//**
	 * 微信邀请模板
	 * 
	 * @param type
	 *            "recomment"
	 * @param callback
	 *//*
	public static void getWechatTemplate(String type,
			final ICallBack<InviteMessage> callback) {

		RequestParams params = new RequestParams();

		params.put("type", type);

		Type t = new TypeToken<InviteMessage>() {
		}.getType();
		BaseHttpResponseHandler<InviteMessage> handler = new BaseHttpResponseHandler<InviteMessage>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, InviteMessage response) {
				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					InviteMessage errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		if (NetworkHelper.isNetworkAvailable(WawaApplication.getInstance())) {
			HttpUtils.get(WawaApplication.getInstance(),
					HttpUtils.getAbsoluteUrl(AppConfig.WECHAT_TEMPLATE),
					params, handler, t);
		} else {
			LogUtis.showTast(WawaApplication.getInstance(), "网络不给力");
		}

	}

	*//**
	 * 获取最新一期乐人
	 * 
	 *//*
	public static void getHotYueren(String uid,
			final ICallBack<List<Article>> callback) {
		RequestParams params = new RequestParams();

		params.put("uid", StringUtils.isEmpty(uid) ? 0 : uid);

		Type t = new TypeToken<List<Article>>() {
		}.getType();
		BaseHttpResponseHandler<List<Article>> handler = new BaseHttpResponseHandler<List<Article>>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, List<Article> response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					List<Article> errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_HOT_YUEREN), params,
				handler, t);

	}

	*//**
	 * 获取推荐用户
	 * 
	 *//*
	public static void getRandomUsers(final ICallBack<List<UserInfo>> callback) {
		RequestParams params = new RequestParams();

		Type t = new TypeToken<List<UserInfo>>() {
		}.getType();
		BaseHttpResponseHandler<List<UserInfo>> handler = new BaseHttpResponseHandler<List<UserInfo>>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, List<UserInfo> response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					List<UserInfo> errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.RANDOM_USERS_INTERFACE),
				params, handler, t);

	}

	*//**
	 * 随机获取一期期刊
	 * 
	 * @param callback
	 *//*
	public static void getRandomMagazine(final ICallBack<Album> callback) {
		RequestParams params = new RequestParams();

		Type t = new TypeToken<Album>() {
		}.getType();
		BaseHttpResponseHandler<Album> handler = new BaseHttpResponseHandler<Album>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Album response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData, Album errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_RANDOM_MAGAZINE),
				params, handler, t);

	}

	*//**
	 * 获取用户的歌单
	 * 
	 * @param uid
	 * @param pageNo
	 * @param pageSize
	 * @param callback
	 *//*
	public static void getTracksByUser(String uid, int pageNo, int pageSize,
			final ICallBack<List<Favorites<Track>>> callback) {
		RequestParams params = new RequestParams();
		params.put("createBy.id", uid);
		params.put("isshare", 1);
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		params.put("sourcetype", 0);

		Type t = new TypeToken<List<Favorites<Track>>>() {
		}.getType();
		BaseHttpResponseHandler<List<Favorites<Track>>> handler = new BaseHttpResponseHandler<List<Favorites<Track>>>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, List<Favorites<Track>> response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					List<Favorites<Track>> errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.get(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.USER_FAVORITES_INTERFACE),
				params, handler, t);

	}

	*//**
	 * 进入广告
	 * 
	 * @param type
	 * @param callback
	 *//*
	public static void getAdvertisementInfo(int type,
			final ICallBack<Ad> callback) {

		RequestParams params = new RequestParams();
		params.put("type", type);

		Type t = new TypeToken<Ad>() {
		}.getType();
		BaseHttpResponseHandler<Ad> handler = new BaseHttpResponseHandler<Ad>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Ad response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData, Ad errorResponse) {
				LogUtis.log(throwable.getMessage());
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.GET_AD), params, handler, t);

	}

	*//**
	 * 退出登录
	 * 
	 * @param callback
	 *//*
	public static void logout(final ICallBack<Result> callback) {
		RequestParams params = new RequestParams();

		Type t = new TypeToken<Result>() {
		}.getType();
		BaseHttpResponseHandler<Result> handler = new BaseHttpResponseHandler<Result>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, Result response) {

				if (callback != null)
					callback.onResult(response);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					Result errorResponse) {
				if (callback != null)
					callback.onError(throwable);

			}
		};
		HttpUtils.post(WawaApplication.getInstance(),
				HttpUtils.getAbsoluteUrl(AppConfig.LOGIN_OUT_URL), params,
				handler, t);

	}

}*/