package com.neighbor.activity;

import com.neighbor.widget.PopWindowHelper;
import com.neighor.neighbor001.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class QueryChargeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_charge_layout);
		initActionBar();
		popupwindowShow();
	}

	private void popupwindowShow() {
		// TODO Auto-generated method stub
	}

	private void initActionBar() {
		// TODO Auto-generated method stub
		ActionBar actionBar = getActionBar();
		if (actionBar!=null) {
			actionBar.hide();
		}
	}
	
}
