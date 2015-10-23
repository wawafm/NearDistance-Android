package com.neighbor.activity;

import java.lang.annotation.Annotation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.neighbor.dialog.ConfirmDialog;
import com.neighbor.utils.LogUtis;
import com.neighor.neighbor001.R;

public class SuperMenberActivity extends BaseActivity{

	private String[] town = null;
	private String[][] xiaoqu = null;
	private String[][] room = null;
	private String[] xiaoquStrings[] = null;
	private String[] roomStrings[] = null;
	private ListView userRoomListView;
	
	private String userSelect; 
	
	public static void lanuch(Context mContext){
		Intent intent = new Intent(mContext,SuperMenberActivity.class);
		
		mContext.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.super_menber_layout);
		setTitle("绑定房号");
		setTextRight("绑定", 3);
		initData();
		initView();
	}
	private void initData() {

		// TODO Auto-generated method stub
		town = new String[]{"鼎峰国汇山","鸿威金都雅苑"};
		xiaoqu = new String[][]{{"1栋","2栋","3栋"},{"2栋","3栋","4栋","5栋","6栋"}};
		room = new String[][] {{"1001","1002","1003","1004",
				"1101","1102","1103","1104",
				"1201","1202","1203","1204",
				"1301","1302","1303","1304",},
				{"1001","1002","1003","1004",
				"1101","1102","1103","1104",
				"1201","1202","1203","1204",
				"1301","1302","1303","1304",},
				{"1001","1002","1003","1004",
				"1101","1102","1103","1104",
				"1201","1202","1203","1204",
				"1301","1302","1303","1304",},
				{"1501","1502","1503","1504",
				"1601","1602","1603","1604",
				"1701","1702","1703","1704",},
				{"1501","1502","1503","1504",
				"1601","1602","1603","1604",
				"1701","1702","1703","1704",},
				{"1501","1502","1503","1504",
				"1601","1602","1603","1604",
				"1701","1702","1703","1704",},
				{"1501","1502","1503","1504",
				"1601","1602","1603","1604",
				"1701","1702","1703","1704",},
				{"1501","1502","1503","1504",
				"1601","1602","1603","1604",
				"1701","1702","1703","1704"}
				};
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		userRoomListView = (ListView) findViewById(R.id.userRoomAddress);
		ArrayAdapter<String> townAdapter = new ArrayAdapter<String>(SuperMenberActivity.this,android.R.layout.simple_list_item_1,town);
		userRoomListView.setAdapter(townAdapter);
		userRoomListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position==0) {
					ArrayAdapter<String> xiaoquAdapter = new ArrayAdapter<String>(SuperMenberActivity.this,android.R.layout.simple_list_item_1,xiaoqu[0]);
					userRoomListView.setAdapter(xiaoquAdapter);
					userRoomListView.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							    userSelect = town[0]+"-"+xiaoqu[position]+"-";
								ArrayAdapter<String> xiaoquAdapter = new ArrayAdapter<String>(SuperMenberActivity.this,android.R.layout.simple_list_item_1,room[position]);
								userRoomListView.setAdapter(xiaoquAdapter);
								userRoomListView.setOnItemClickListener(new OnItemClickListener() {
		
									@Override
									public void onItemClick(AdapterView<?> parent,
											View view, int position, long id) {
										userSelect = userSelect+room[position];
										builddialog();
										
									}
								});
							
						}
					});
				}else {
					ArrayAdapter<String> xiaoquAdapter = new ArrayAdapter<String>(SuperMenberActivity.this,android.R.layout.simple_list_item_1,xiaoqu[1]);
					userRoomListView.setAdapter(xiaoquAdapter);
					userRoomListView.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
								userSelect = town[0]+"-"+xiaoqu[position]+"-";
								ArrayAdapter<String> xiaoquAdapter = new ArrayAdapter<String>(SuperMenberActivity.this,android.R.layout.simple_list_item_1,room[position]);
								userRoomListView.setAdapter(xiaoquAdapter);
								userRoomListView.setOnItemClickListener(new OnItemClickListener() {
		
									@Override
									public void onItemClick(AdapterView<?> parent,
											View view, int position, long id) {
										userSelect = userSelect+room[position];
										builddialog();
									}
								});
							}
					});
				}
			}
		});
	}
	private void builddialog() {

		final ConfirmDialog dlg2 = ConfirmDialog.createDialog(SuperMenberActivity.this);
		dlg2.setTitle("退出登录");
		dlg2.setMessage("你确定退出登录吗？");
		dlg2.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences share = getSharedPreferences("sharedpreference",MODE_PRIVATE);
				/** 参数编辑器 */
				Editor editor = share.edit();
				editor.putString("roomNum",userSelect);
				dlg2.dismiss();
				finish();
			}
		});
	}
}
