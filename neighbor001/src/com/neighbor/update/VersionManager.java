package com.neighbor.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.neighbor.activity.MainActivity;
import com.neighbor.api.imp.HttpUtils;
import com.neighbor.api.imp.ICallBack;
import com.neighbor.utils.LogUtis;
import com.neighbor.utils.StringUtils;
import com.neighbor.utils.Util;
import com.neighor.neighbor001.AppConfig;
import com.neighor.neighbor001.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * 版本更新类
 * 
 * @author figting
 * 
 */
public class VersionManager extends Service {

	private Context mContext;
	private String appName = "wawa.apk";
	private Handler handler = new Handler();
	public static String UPDATE_START = "start";
	public static String UPDATE_CANCEL = "cancel";
	public static boolean isRunning = false;

	public static boolean isShowResult;

	// 通知栏
	private NotificationManager updateNotificationManager = null;
	private Notification updateNotification = null;
	// 通知栏跳转Intent
	private Intent updateIntent = null;
	private PendingIntent updatePendingIntent = null;
	private Version mVersion;

	public VersionManager() {
		mContext = this;
	}

	public void setContext(Context ctx) {
		mContext = ctx;
	}

	public void setShowResult(boolean r) {
		isShowResult = r;
	}

	// check new version and update
	public void checkToUpdate() throws NameNotFoundException {
		// TODO Auto-generated method stub
		if (mContext == null)
			throw new RuntimeException("Context is null");
		getAppVersion(0, CurrentVersion.getVerCode(mContext));
	}

	// show Update Dialog
	private void showUpdateDialog(final Version version)
			throws NameNotFoundException {
		// TODO Auto-generated method stub
		Intent it = new Intent(mContext, UpdateDialog.class);
		it.putExtra("ver", version);
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(it);

		// final UpdateDialog dialog = new UpdateDialog(mContext);
		// dialog.setCancelable(false);
		//
		// dialog.getWindow()
		// .setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		// dialog.show();
		// dialog.setCancelButton(false);
		// dialog.setTitle(R.string.update_dialog_title);
		// dialog.setMessage1(version.getDesc());
		// dialog.setMessage2(version.getSize());
		// dialog.setNegativeButtonText(R.string.update_native_btn_text);
		// dialog.setPositiveButtonText(R.string.update_positive_btn_text);
		// dialog.setOnNegativeButtonListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// dialog.dismiss();
		// stopSelf();
		// }
		// });
		//
		// dialog.setOnPositiveButtonListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// if (!Util.isAvaiableSpace(6)) {
		// Toast.makeText(mContext, "你的sd卡空间不够", Toast.LENGTH_SHORT)
		// .show();
		// return;
		// }
		// dialog.dismiss();
		// updateNotificationManager.notify(0, updateNotification);
		// Log.i("fighting", "verseion url:"+version.getUrl());
		// if(!StringUtils.isEmpty(version.getUrl()))
		// downAppFile(version.getUrl());
		// // 更新当前版本
		// }
		// });

	}

	protected void downAppFile(final String url) {

		new Thread() {
			int downloadCount = 0;
			long totalSize = 0;
			int updateTotalSize = 0;

			@SuppressWarnings("deprecation")
			public void run() {
				isRunning = true;
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				HttpResponse response;
				try {
					response = client.execute(get);
					HttpEntity entity = response.getEntity();
					updateTotalSize = (int) entity.getContentLength();
					Log.isLoggable("DownTag", (int) updateTotalSize);
					InputStream is = entity.getContent();
					FileOutputStream fileOutputStream = null;
					if (is == null) {
						throw new RuntimeException("isStream is null");
					}
					File file = new File(
							Environment.getExternalStorageDirectory(), appName);
					if (file.exists()) {
						file.delete();

					}
					file.createNewFile();
					fileOutputStream = new FileOutputStream(file);
					byte[] buf = new byte[4096];
					int readSize = -1;
					do {
						readSize = is.read(buf);
						if (readSize <= 0)
							break;
						fileOutputStream.write(buf, 0, readSize);
						totalSize += readSize;
						if ((downloadCount == 0)
								|| (int) (totalSize * 100 / updateTotalSize) - 10 > downloadCount) {
							downloadCount += 10;
							updateNotification.setLatestEventInfo(
									VersionManager.this, "正在下载",
									(int) totalSize * 100 / updateTotalSize
											+ "%", updatePendingIntent);
							updateNotificationManager.notify(0,
									updateNotification);
						}
					} while (true);
					is.close();
					fileOutputStream.close();
					haveDownLoad();
					updateNotification.setLatestEventInfo(VersionManager.this,
							"下载完成", 100 + "%", updatePendingIntent);
					updateNotification.flags = Notification.FLAG_AUTO_CANCEL;
					updateNotificationManager.notify(0, updateNotification);

				} catch (IOException e) {
					e.printStackTrace();
					updateNotification.setLatestEventInfo(VersionManager.this,
							"下载失败", "", updatePendingIntent);
					updateNotification.flags = Notification.FLAG_AUTO_CANCEL;
					updateNotificationManager.notify(0, updateNotification);
					stopSelf();
				}
			}
		}.start();
	}

	// cancel progressBar and start new App
	protected void haveDownLoad() {
		// TODO Auto-generated method stub
		isRunning = false;
		handler.post(new Runnable() {
			public void run() {

				installNewApk();

			}
		});
	}

	// 安装新的应用
	protected void installNewApk() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), appName)),
				"application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
		stopSelf();

	}

	private void getAppVersion(int type, int versionCode) {
		//LogUtis.showTast(mContext, "正在检测更新");
		HttpUtils.getAppVersion(type, versionCode,AppConfig.MI_CHANNEL,
				new ICallBack<Version>() {

					@Override
					public void onResult(Version result) {
						mVersion = result;

						if (result != null) {

							int currentCode = 0;
							try {
								currentCode = CurrentVersion
										.getVerCode(mContext);
							} catch (NameNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int sversionCode = Integer.parseInt(result
									.getVersionCode());
							if (sversionCode > currentCode) {// Current Version
																// is old
								// 弹出更新提示对话框
								try {
									Log.i("fighting", "result:showUpdateDlg");
									showUpdateDialog(result);
								} catch (NameNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								if (isShowResult) {
									showNoUpdateDlg();
								}
							}
						} else {

							showErrorUpdateDlg();

							stopSelf();
						}

					}

					@Override
					public void onError(Throwable result) {

					}
				});
	}

	public void showNoUpdateDlg() {
		// TODO Auto-generated method stub

		LogUtis.showTast(mContext, "已是最新版本");
		stopSelf();

	}

	public void showErrorUpdateDlg() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "服务器获取版本信息出错", Toast.LENGTH_LONG).show();
		stopSelf();

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 设置下载过程中，点击通知栏，回到主界面
		updateNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		updateNotification = new Notification();
		updateIntent = new Intent(this, MainActivity.class);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), appName)),
				"application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		updatePendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		// 设置通知栏显示内容
		updateNotification.icon = R.drawable.icon;
		updateNotification.tickerText = "开始下载";

		updateNotification.setLatestEventInfo(this,
				getResources().getString(R.string.app_name), "0%",
				updatePendingIntent);
		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		if (intent == null)
			return;
		String action = intent.getAction();
		if (UPDATE_START.equals(action)) {
			if (isRunning) {
				Toast.makeText(mContext, "正在后台更新!", Toast.LENGTH_SHORT).show();
				return;
			}
			if (!Util.isAvaiableSpace(6)) {
				Toast.makeText(mContext, "你的sd卡空间不够", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			finishActivity();
			updateNotificationManager.notify(0, updateNotification);
			if (mVersion != null) {
				if (!StringUtils.isEmpty(mVersion.getUrl()))
					downAppFile(mVersion.getUrl());
			}
		} else if (UPDATE_CANCEL.equals(action)) {
			stopSelf();
			finishActivity();

		} else {

			try {
				checkToUpdate();
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void finishActivity() {
		if (mContext instanceof Activity) {
			((Activity) mContext).finish();
		}
	}

	public static void startService(Context context) {
		Intent intent = new Intent(context, VersionManager.class);
		context.startService(intent);
	}

	public static void stopService(Context context) {
		Intent intent = new Intent(context, VersionManager.class);
		context.stopService(intent);
	}

}
