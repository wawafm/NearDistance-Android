package com.neighbor.activity;

import com.neighor.neighbor001.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class UserQueryChargeMsgActivity extends BaseActivity{
	
	public static void lanuch(Context mContext){
		Intent intent = new Intent(mContext,UserQueryChargeMsgActivity.class);
		mContext.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("查询显示");
		setTextRight("",0);
		setContentView(R.layout.user_query_charge_msg);
	}

}
