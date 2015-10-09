
package com.neighbor.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.neighbor001.R;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.neighbor.utils.Util;
import com.neighbor.widget.PullRefreshScrollerView;

import android.R.integer;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class HomeFragment extends Fragment{
	
	private ViewPager homePager;
	private RadioGroup homeRgs;
	private GridView homeGrid;
	private ListView homeList;
	private HomeViewPagerAdapter homeAdapter = null;
	private List<ImageView> imageViews= null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		homeGrid = (GridView) view.findViewById(R.id.homeContentType);
		homeList = (ListView) view.findViewById(R.id.homeTuanGouList);
		homeRgs = (RadioGroup) view.findViewById(R.id.homePageRadio);
		
		homePager = (ViewPager) view.findViewById(R.id.homeAutoViewPager);
		
		initHomeRgs();
		initViewPager();
	}

	

	private void initHomeRgs() {
		// TODO Auto-generated method stub
		homeRgs.setOnCheckedChangeListener(new HomeRgsCheckedListener());
		((RadioButton)homeRgs.getChildAt(0)).setChecked(true);
	}

	private void initViewPager() {
		// TODO Auto-generated method stub
		
		int[] imgs = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,};
		imageViews = new ArrayList<ImageView>();
		for (int i = 0; i < imgs.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setImageResource(imgs[i]);
			imageViews.add(imageView);
		}
		homePager.setOnPageChangeListener(new ViewPageChangeListener());
		homePager.setCurrentItem(0);
		homeAdapter = new HomeViewPagerAdapter(imageViews);
		homePager.setAdapter(homeAdapter);
	}

	public class HomeViewPagerAdapter extends PagerAdapter{

		List<ImageView> imageViews = null;
		public HomeViewPagerAdapter(List<ImageView> imageViews){
			this.imageViews = imageViews;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		@Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = imageViews.get(position);
            container.addView(v);
            return v;
        }
         
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//          super.destroyItem(container, position, object);
            container.removeView(imageViews.get(position));
        }
		
	}
	public class HomeRgsCheckedListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			int count = arg0.getChildCount();
			for (int i = 0; i < count; i++) {
				if (arg1==arg0.getChildAt(i).getId()) {
					homePager.setCurrentItem(i);
				}
			}
		}
		
	}
	public class ViewPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			((RadioButton)homeRgs.getChildAt(arg0)).setChecked(true);
		}
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mHandler.sendEmptyMessageDelayed(1, 2000);
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mHandler.removeMessages(1);
	}
	private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch(msg.what) {
            case 1:
                int totalcount = imageViews.size();//存放ImageView的list的大小
                int currentItem = homePager.getCurrentItem();
                int toItem = currentItem + 1 == totalcount ? 0 : currentItem + 1;
                
                homePager.setCurrentItem(toItem, true);
            	
                //每两秒钟发送一个message，用于切换viewPager中的图片
                this.sendEmptyMessageDelayed(1, 2000);
            }
        }
    };
}