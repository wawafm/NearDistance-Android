package com.neighbor.activity;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.neighbor.bean.MemberinfoBean;
import com.neighbor.bean.UserLoginReturnBean;
import com.neighbor.utils.LogUtis;
import com.neighbor.utils.SaveAndGetPreference;
import com.neighor.neighbor001.AppConfig;
import com.neighor.neighbor001.R;

import android.R.array;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserStartLoginActivity extends Activity  implements OnClickListener{

	private EditText userNameEdit,userPasswordEdit;
	private Button userLoginBtn,userRegisterBtn;
	private TextView forgetPassWord;
	private HttpUtils httpUtils = null;
	private HttpHandler httpHandler = null;
	private String loginUserNameStr,loginPasswordStr;
	private MemberinfoBean memberinfo = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_login_fragment_layout);
		ActionBar actionBar = getActionBar();
		if (actionBar!=null) {
			actionBar.hide();
		}
		httpUtils = new HttpUtils();
		initView();
		initData();
		/*memberinfo = new SaveAndGetPreference(UserStartLoginActivity.this).readOAuth();
		if (memberinfo!=null) {
			String id = memberinfo.getId();
			if (id!=null&&id.length()>0) {
				MainActivity.lanuch(UserStartLoginActivity.this);
				finish();
			}
		}*/
		SharedPreferences shared = getSharedPreferences("sharedpreference",MODE_PRIVATE);
		String str = shared.getString("id", "没有找到");
		if (!str.equals("没有找到")) {
			MainActivity.lanuch(UserStartLoginActivity.this);
			finish();
		}
	}

	private void initData() {
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		userLoginBtn = (Button)findViewById(R.id.userLoginBtn);
		userRegisterBtn = (Button)findViewById(R.id.userRegistBtn);
		userNameEdit = (EditText)findViewById(R.id.userName);
		userPasswordEdit = (EditText)findViewById(R.id.userPassWord);
		forgetPassWord = (TextView)findViewById(R.id.forgetPassWord);
		userLoginBtn.setOnClickListener(this);
		userRegisterBtn.setOnClickListener(this);
		forgetPassWord.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.userLoginBtn:
			startLoginMsg();
			break;
		case R.id.userRegistBtn:
			//注册账号
			UserRegisterActivity.lanuch(UserStartLoginActivity.this,0);
			break;
		case R.id.forgetPassWord:
			//重置账号密码
			UserRegisterActivity.lanuch(UserStartLoginActivity.this,1);
			break;

		default:
			break;
		}
	}
	private void startLoginMsg() {
		// TODO Auto-generated method stub
		String loginUrl = "";
		loginUserNameStr = userNameEdit.getText().toString();
		loginPasswordStr = userPasswordEdit.getText().toString();
		if (loginPasswordStr!=null&&loginUserNameStr!=null&&loginPasswordStr.length()>0&&loginUserNameStr.length()>0) {
			loginUrl = AppConfig.USER_LOGIN+"mobile="+loginUserNameStr+"&password="+loginPasswordStr;
		}
		httpHandler = httpUtils.send(HttpRequest.HttpMethod.GET, loginUrl,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				
				UserLoginReturnBean<MemberinfoBean> returnBean = JSONObject.parseObject(arg0.result,new TypeReference<UserLoginReturnBean<MemberinfoBean>>(){});
//				LogUtis.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+returnBean.getCode());
				if (returnBean.getCode().equals("0")) {
					Toast.makeText(UserStartLoginActivity.this,returnBean.getMsg(),Toast.LENGTH_LONG);
				}else {
					if (returnBean.getMemberinfo()!=null) {
//						new SaveAndGetPreference(UserStartLoginActivity.this).saveOAuth(returnBean.getMemberinfo());
						/** 获取参数存储对象,,参数分别是：文件名，参数存储方式 */
						SharedPreferences share = getSharedPreferences("sharedpreference",MODE_PRIVATE);
						/** 参数编辑器 */
						Editor editor = share.edit();
						editor.putString("id",returnBean.getMemberinfo().getId());
						editor.putString("Nickname",returnBean.getMemberinfo().getNickname());
						/** 提交保存 */
						editor.commit();
						MainActivity.lanuch(UserStartLoginActivity.this);
						UserStartLoginActivity.this.finish();
					}
				}
				
			}
		});
	}
}
