package com.neighbor.activity;

import com.neighor.neighbor001.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class SelectReceiveAddressActivity extends BaseActivity{
	
	private ListView addrList;
	public static void lanuch(Context mContext){
		Intent intent = new Intent(mContext,SelectReceiveAddressActivity.class);
		mContext.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_receive_addr_layout);
		initView();
		initData();
	}
	private void initData() {
		// TODO Auto-generated method stub
		
	}
	private void initView() {
		// TODO Auto-generated method stub
		addrList = (ListView) findViewById(R.id.selectAddrList);
	}

}
