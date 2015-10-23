package com.neighbor.update;
import com.neighor.neighbor001.R;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
public class CurrentVersion {
	private static final String TAG = "Config";
	public static final String appPackName = "fm.wawa.music";
	
	/**
	 * 获取版本号
	 * @param context
	 * @return
	 * @throws NameNotFoundException
	 */
	public static int getVerCode(Context context)throws NameNotFoundException{
		int verCode = -1;
		try{
			verCode = context.getPackageManager().getPackageInfo(appPackName, 0).versionCode;	
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
		}
		return verCode;
	}
	/**
	 * 获取版本名
	 * @param context
	 * @return
	 */
	public static String getVerName(Context context){
		String verName = "";
		try{
			verName = context.getPackageManager().getPackageInfo(appPackName, 0).versionName;
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
		}
		return verName;
	}
	
	/**
	 * 获取应用程序名
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context){
		String appName = context.getResources().getText(R.string.app_name).toString();
		return appName;
		}
}
