
package com.neighor.neighbor001;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.neighor.neighbor001.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

public class NeighborApplecation extends Application{
	
	private static NeighborApplecation instance;
	private List<Activity> mActivities;
	
	public static NeighborApplecation getInstance() {

		if (null == instance) {

			instance = new NeighborApplecation();
		}

		return instance;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initImageLoader();
		mActivities = new ArrayList<Activity>();
	}
	
	public static DisplayImageOptions options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.default_l)
	.showImageForEmptyUri(R.drawable.default_l)
	.showImageOnFail(R.drawable.default_l).cacheInMemory(true)
	.cacheOnDisk(true).considerExifParams(true)
	.displayer(new SimpleBitmapDisplayer())
	.build();
	
	private void initImageLoader() {
		initImageLoader(getApplicationContext());

	}
	
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		File cache = new File(Environment.getExternalStorageDirectory(),
				"/wawa/image");
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
				context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app
		config.discCache(new UnlimitedDiscCache(cache));
		

		config.denyCacheImageMultipleSizesInMemory()
				.memoryCacheSize(2 * 1024 * 1024)
				.defaultDisplayImageOptions(options)
				.imageDownloader(
						new BaseImageDownloader(context, 5 * 1000, 30 * 1000));

		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config.build());
	}
	
	public void addActivity(Activity ac) {
		mActivities.add(ac);
	}

	public void removeActivity(Activity ac) {
		mActivities.remove(ac);
	}

	// 销所有的activity
	public void destoryAll() {
		/*
		 * for (int i = 0; i < mActivities.size(); i++) { Activity a =
		 * mActivities.get(i); if (a != null) a.finish(); }
		 */

		for (Activity activity : mActivities) {

			activity.finish();
		}

	}

	public void exitApp() {

		for (Activity activity : mActivities) {

			activity.finish();
		}
	}

	
}