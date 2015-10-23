package com.neighbor.activity;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.neighbor.utils.LogUtis;
import com.neighor.neighbor001.AppConfig;
import com.neighor.neighbor001.R;

import android.R.integer;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserRegisterActivity extends BaseActivity implements OnClickListener{
	
	private int tag = 0;
	private Context mContext;
	
	private EditText registerUserName,registerUserCaptcha,registerUserPassWord;
	private TextView userCaptchaBack;
	private Button registerUserBtn;
	private HttpUtils httpUtils = null;
	private HttpHandler httpHandler = null;
	
	private String captchaStr,passwordStr,userNameStr;
	
	public static void lanuch(Context mContext,int tag){
		
		Intent intent = new Intent(mContext,UserRegisterActivity.class);
		intent.putExtra("tag", tag);
		mContext.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_register_fragment_layout);
		ActionBar actionBar = getActionBar();
		if (actionBar!=null) {
			actionBar.hide();
		}
		httpUtils = new HttpUtils();
		initView();
		initData();
	}

	private void initView() {
		// TODO Auto-generated method stub
		registerUserBtn = (Button) findViewById(R.id.registerUserBtn);
		registerUserCaptcha = (EditText) findViewById(R.id.registerUserCaptcha);
		registerUserName = (EditText) findViewById(R.id.registerUserName);
		registerUserPassWord = (EditText) findViewById(R.id.registerUserPassWord);
		userCaptchaBack = (TextView) findViewById(R.id.userCaptchaBack);
		
		userCaptchaBack.setOnClickListener(this);
		registerUserBtn.setOnClickListener(this);
	}
	
	private void initData() {
		// TODO Auto-generated method stub
		tag = getIntent().getIntExtra("tag",0);
		if (tag==0) {
			registerUserBtn.setText("注册");
		}else {
			registerUserBtn.setText("重置");
		}
		
		/*//获取输入框内的验证码
		captchaStr = userCaptchaBack.getText().toString();
		
		//获取输入密码
		passwordStr = registerUserPassWord.getText().toString();*/
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.registerUserBtn:
			registerUser();
			break;
		case R.id.userCaptchaBack:
			getUserCaptcha();
			break;

		default:
			break;
		}
	}

	private void registerUser() {
		// TODO Auto-generated method stub
		String regUrl = "";
		userNameStr = registerUserName.getText().toString();
		passwordStr = registerUserPassWord.getText().toString();
		captchaStr = registerUserCaptcha.getText().toString();
		
		if (userNameStr!=null&&passwordStr!=null&&captchaStr!=null&&userNameStr.length()==11
				&&passwordStr.length()>=6&&passwordStr.length()<=20) {
			if (tag==0) {
				regUrl = AppConfig.USER_REGISTER+"mobile="+userNameStr+"&password="+passwordStr+"&code="+captchaStr;
			}else {
				regUrl = AppConfig.USER_PASSWORD_RESETTING+"mobile="+userNameStr+"&password="+passwordStr+"&code="+captchaStr;
			}
		}
		
		httpHandler = httpUtils.send(HttpRequest.HttpMethod.GET, regUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtis.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+arg0.result);
				MainActivity.lanuch(UserRegisterActivity.this);
			}
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			
		});
	}

	private void getUserCaptcha() {
		String codeUrl = "";
		//获取输入框内用户名
		userNameStr = registerUserName.getText().toString();
		// TODO Auto-generated method stub
		if (userNameStr!=null&&userNameStr.length()==11) {
			codeUrl = AppConfig.SEND_MESSAGE+userNameStr;
		}
		httpHandler = httpUtils.send(HttpRequest.HttpMethod.GET, codeUrl, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				MainActivity.lanuch(UserRegisterActivity.this);
			}
		});
	}

}
