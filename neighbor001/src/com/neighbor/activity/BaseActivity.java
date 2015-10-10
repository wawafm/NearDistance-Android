package com.neighbor.activity;

import com.example.neighbor001.R;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class BaseActivity extends FragmentActivity{

	private TextView mTitle,textRight ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View title = LayoutInflater.from(this).inflate(R.layout.global_title_layout,null,false); 
		textRight = (TextView) title.findViewById(R.id.textRight);
		mTitle = (TextView) title.findViewById(R.id.titleText);
		if (getActionBar()!=null) {
			getActionBar().setCustomView(title);
			getActionBar().setDisplayShowCustomEnabled(true);
		}
		
	}
	
	public void setTitle(String title) {
		this.mTitle.setText(title);
	}

	public void setTitle(int res) {
		this.mTitle.setText(res);
	}

	public TextView getTextRight() {
		return textRight;
	}

	public void setTextRight(String textRight,int i) {
		this.textRight.setText(textRight);
		if (i==0) {
			this.textRight.setVisibility(View.GONE);
		}else {
			this.textRight.setVisibility(View.VISIBLE);
		}
	}

	
	
	
}
