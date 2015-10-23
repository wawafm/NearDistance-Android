package com.neighbor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.neighbor.utils.LogUtis;
import com.neighbor.widget.ClearableEditTextWithIcon;
import com.neighor.neighbor001.R;

public class ChangeNiChengActivity extends BaseActivity{

	private EditText changeEdit;
	private String primaryNiCheng;
	public static void lanuch(Context mContext,String nicheng){
		Intent intent = new Intent(mContext,ChangeNiChengActivity.class);
		intent.putExtra("nicheng", nicheng);
		mContext.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_nicheng_layout);
		primaryNiCheng = getIntent().getStringExtra("nicheng");
		setTextRight("保存",3);
		setTitle("修改昵称");
		initView();
	}
	private void initView() {
		// TODO Auto-generated method stub
		changeEdit = (EditText) findViewById(R.id.changeEdit);
		changeEdit.setText(primaryNiCheng);
	}
	
	@Override
	public void onRightClick(View view) {
		// TODO Auto-generated method stub
		super.onRightClick(view);
	}
}
