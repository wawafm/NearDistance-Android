package com.neighbor.widget;

import com.neighor.neighbor001.R;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AutoScrollViewPager {

	private ViewPager mViewPager;
	private ViewGroup mViewGroup;
	private int[] imgIdArray;
	private Context mContext;
	
	public AutoScrollViewPager(Context mContext,ViewPager mViewPager,ViewGroup mViewGroup) {
		super();
		this.mViewPager = mViewPager;
		this.mViewGroup = mViewGroup;
		this.mContext = mContext;
	}



	private ImageView[] tips;

	private ImageView[][] mImageViews;

	private static final int MSG_CHANGE_PHOTO = 1;

	/** 图片自动切换时间 */
	private static final int PHOTO_CHANGE_TIME = 3000;
	private Handler mHandler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			switch (msg.what) {
			case MSG_CHANGE_PHOTO:
				int index = mViewPager.getCurrentItem();
				mViewPager.setCurrentItem(index + 1);
				mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO,
						PHOTO_CHANGE_TIME);
				break;
			}
			super.dispatchMessage(msg);
		}
	};

	public void startScroll() {
		imgIdArray = new int[] { R.drawable.img1 ,R.drawable.img2,R.drawable.img3,R.drawable.img4};

		// 将点点加入到ViewGroup中
		tips = new ImageView[imgIdArray.length];

		if (imgIdArray.length <= 1)
			mViewGroup.setVisibility(View.GONE);
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(mContext);
			imageView.setMinimumWidth(30);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
			params.setMargins(20, 0, 0, 0);
			imageView.setLayoutParams(params);
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.adjiaodian);
			} else {
				tips[i].setBackgroundResource(R.drawable.adnojiaodian);
			}

			mViewGroup.addView(imageView);
		}

		mImageViews = new ImageView[2][];
		// 将图片装载到数组中,其中一组类似缓冲，防止图片少时出现黑色图片，即显示不出来
		mImageViews[0] = new ImageView[imgIdArray.length];
		mImageViews[1] = new ImageView[imgIdArray.length];

		for (int i = 0; i < mImageViews.length; i++) {
			for (int j = 0; j < mImageViews[i].length; j++) {
				ImageView imageView = new ImageView(mContext);
				imageView.setBackgroundResource(imgIdArray[j]);
				mImageViews[i][j] = imageView;
			}
		}

		// 设置Adapter
		mViewPager.setAdapter(new MyAdapter());
		// 设置监听，主要是设置点点的背景
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageScrollStateChanged(int arg0) {

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageSelected(int arg0) {
				setImageBackground(arg0 % imgIdArray.length);
			}
		});
		
		// 设置ViewPager的默认项, 设置为长度的50倍，这样子开始就能往左滑动
		mViewPager.setCurrentItem((imgIdArray.length) * 50);
		mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO, PHOTO_CHANGE_TIME);
	}
		

	public class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			if (imgIdArray.length == 1)
				((ViewPager) container).removeView(mImageViews[position
						/ imgIdArray.length % 2][0]);
			else
				((ViewPager) container).removeView(mImageViews[position
						/ imgIdArray.length % 2][position % imgIdArray.length]);
		}

		/**
		 * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			if (imgIdArray.length == 1)
				return mImageViews[position / imgIdArray.length % 2][0];
			else
				((ViewPager) container).addView(mImageViews[position
						/ imgIdArray.length % 2][position % imgIdArray.length],
						0);
			return mImageViews[position / imgIdArray.length % 2][position
					% imgIdArray.length];
		}

	}



	/**
	 * 设置选中的tip的背景
	 * 
	 * @param selectItemsIndex
	 */
	private void setImageBackground(int selectItemsIndex) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItemsIndex) {
				tips[i].setBackgroundResource(R.drawable.adjiaodian);
			} else {
				tips[i].setBackgroundResource(R.drawable.adnojiaodian);
			}
		}
	}
}
