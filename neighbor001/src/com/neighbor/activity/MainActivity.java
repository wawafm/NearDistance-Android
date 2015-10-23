package com.neighbor.activity;

import java.util.ArrayList;
import java.util.List;

import com.neighbor.fragment.BusinessCircleFragment;
import com.neighbor.fragment.HomeFragment;
import com.neighbor.fragment.InteractFragment;
import com.neighbor.fragment.MyFragment;
import com.neighbor.utils.FragmentTabUtils;
import com.neighor.neighbor001.R;
import com.sina.weibo.sdk.utils.NetworkHelper;


import android.os.Bundle;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private RadioGroup rgs;
	private ArrayList<Fragment> fragments=null;
	private int containerId;
	private Context mContext;
	private ImageView logoImage;
	private TextView logoTitleText,logoTitleRight;
	
	public static void lanuch(Context mContext){
		Intent intent = new Intent(mContext,MainActivity.class);
		mContext.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initActionBar();
		initView();
	}

	private void checkNetWork() {
		if (!NetworkHelper.isNetworkAvailable(this)) {
			
		}

	}
	private void initActionBar() {
		// TODO Auto-generated method stub
		ActionBar actionBar = getActionBar();
		if (actionBar!=null) {
			
			actionBar.hide();
		}
	}

	private void initView() {
		containerId = R.id.frameLayout;
		logoImage = (ImageView) findViewById(R.id.logoImg);
		logoTitleText = (TextView) findViewById(R.id.logoTitle);
		logoTitleRight = (TextView) findViewById(R.id.logoRight);
		rgs = (RadioGroup) findViewById(R.id.mainBottomRgs);
		fragments = new ArrayList<Fragment>();
		fragments.add(new HomeFragment());
		fragments.add(new InteractFragment());
		fragments.add(new BusinessCircleFragment());
		fragments.add(new MyFragment());
		new FragmentTabUtils(containerId,fragments,rgs,getSupportFragmentManager(),logoTitleText,logoTitleRight);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
