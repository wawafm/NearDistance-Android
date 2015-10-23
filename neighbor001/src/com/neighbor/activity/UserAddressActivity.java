package com.neighbor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.neighbor.utils.LogUtis;
import com.neighor.neighbor001.R;

public class UserAddressActivity extends BaseActivity{

	private EditText addrUserName,addrUserPhone,addrUserAddrDetail,addrUserPostcode;
	private TextView addrUserAddress;
	public static void lanuch(Context mContext){
		Intent intent = new Intent(mContext,UserAddressActivity.class);
		mContext.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_address_layout);
		setTextRight("保存",3);
		setTitle("收货地址");
		initView();
	}
	private void initView() {
		// TODO Auto-generated method stub
		addrUserAddrDetail = (EditText) findViewById(R.id.addUserAddrDetail);
		addrUserAddress = (TextView) findViewById(R.id.addrUserAddr);
		addrUserName = (EditText) findViewById(R.id.addrUserName);
		addrUserPostcode = (EditText) findViewById(R.id.addrPostcode);
		addrUserPhone = (EditText) findViewById(R.id.addrUserPhone);
		addrUserAddress.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.addrUserAddr:
					SelectReceiveAddressActivity.lanuch(UserAddressActivity.this);
					break;

				default:
					break;
				}
			}
		});
	}
}
