/**
 * 
 */
/**
 * @author xzh
 *
 */
package com.neighbor.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class LogUtis {
	private static String TAG = "wawalog";

	public static void log(String log) {
		if (!StringUtils.isEmpty(log))
			Log.i(TAG, log);
	}

	public static void showTast(final Context context, final String text) {

		if (!StringUtils.isEmpty(text)) {
			Toast t = Toast.makeText(context, text, Toast.LENGTH_SHORT);

			t.show();
		}

	}

}
