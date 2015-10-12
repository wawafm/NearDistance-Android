package com.neighbor.activity;

import com.example.neighbor001.R;
import com.neighbor.widget.RefreshListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class JinRiYouHuiActivity extends BaseActivity{
	
	public static void Lanuch(Context mContext) {
		Intent it = new Intent(mContext, JinRiYouHuiActivity.class);
		mContext.startActivity(it);
//		((Activity) ctx).overridePendingTransition(R.anim.slide_right_in, 0);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jinri_youhui_layout);
	}

}
