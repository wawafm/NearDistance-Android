
package com.neighbor.app;

import com.example.neighbor001.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import android.app.Application;

public class NeighborApplecation extends Application{
	private static NeighborApplecation instance;
	
	
	public static NeighborApplecation getInstance() {

		if (null == instance) {

			instance = new NeighborApplecation();
		}

		return instance;
	}
	
	public static DisplayImageOptions options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.default_l)
	.showImageForEmptyUri(R.drawable.default_l)
	.showImageOnFail(R.drawable.default_l).cacheInMemory(true)
	.cacheOnDisk(true).considerExifParams(true)
	.displayer(new SimpleBitmapDisplayer())
	.build();
}