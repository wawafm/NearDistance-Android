package com.neighbor.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.neighbor001.R;
import com.neighbor.fragment.BusinessCircleFragment;
import com.neighbor.fragment.HomeFragment;
import com.neighbor.fragment.InteractFragment;
import com.neighbor.fragment.MyFragment;
import com.neighbor.utils.LogUtis;
import com.neighbor.widget.ListViewForScrollView;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends BaseActivity {

	private RadioGroup rgs;
	private ViewPager mPager;
	private MainFragmentPageAdapter fragmentPageAdapter = null;
	private List<Fragment> fragments = null;
	private String titleName [] = {"邻居","邻居圈","商业","个人中心"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initRadioGroup();
		initViewPager();
	}

	private void initView() {
		// TODO Auto-generated method stub
		mPager = (ViewPager) findViewById(R.id.viewpager);
		rgs = (RadioGroup) findViewById(R.id.mainBottomRgs);
		
	}

	private void initViewPager() {
		// TODO Auto-generated method stub
		fragments = new ArrayList<Fragment>();
		fragments.add(new HomeFragment());
		fragments.add(new InteractFragment());
		fragments.add(new BusinessCircleFragment());
		fragments.add(new MyFragment());
		fragmentPageAdapter = new MainFragmentPageAdapter(getSupportFragmentManager());
		mPager.setAdapter(fragmentPageAdapter);
		mPager.setOnPageChangeListener(new onPageSetChanger());
		mPager.setOffscreenPageLimit(3);
		mPager.setCurrentItem(0);
	}

	private void initRadioGroup() {
		// TODO Auto-generated method stub
		
		rgs.setOnCheckedChangeListener(new onRadioGroupCheched());
		rgs.check(1);
	}

	
	/*RadioGroup的点击事件*/
	public class onRadioGroupCheched implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			int count = arg0.getChildCount();
			for (int i = 0; i <count; i++) {
				if (arg1==arg0.getChildAt(i).getId()) {
					mPager.setCurrentItem(i);
					setTitle(titleName[i]);
				}
			}
		}
		
	}
	
	/*Fragment左右滑动时候的事件*/
	public class onPageSetChanger implements OnPageChangeListener{

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
			rgs.check(arg0+1);
		}
		
	}
	
	/*给Fragment设置adapter*/
	public class MainFragmentPageAdapter extends FragmentStatePagerAdapter{

		public MainFragmentPageAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragments.size();
		}
		
	} 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
