package com.neighbor.fragment;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.neighbor.activity.UserIsLoginActivity;
import com.neighbor.activity.UserRegisterActivity;
import com.neighbor.utils.LogUtis;
import com.neighor.neighbor001.AppConfig;
import com.neighor.neighbor001.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserIsNotLoginFragment extends Fragment implements OnClickListener{

	private EditText userNameEdit,userPasswordEdit;
	private Button userLoginBtn,userRegisterBtn;
	private TextView forgetPassWord;
	private HttpUtils httpUtils = null;
	private HttpHandler httpHandler = null;
	private String loginUserNameStr,loginPasswordStr;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.my_login_fragment_layout, null);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		httpUtils = new HttpUtils();
		initView(view);
		
	}
	private void initView(View v) {
		// TODO Auto-generated method stub
		userLoginBtn = (Button)v.findViewById(R.id.userLoginBtn);
		userRegisterBtn = (Button)v.findViewById(R.id.userRegistBtn);
		userNameEdit = (EditText)v.findViewById(R.id.userName);
		userPasswordEdit = (EditText)v.findViewById(R.id.userPassWord);
		forgetPassWord = (TextView) v.findViewById(R.id.forgetPassWord);
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
			UserRegisterActivity.lanuch(getActivity(),0);
			break;
		case R.id.forgetPassWord:
			//重置账号密码
			UserRegisterActivity.lanuch(getActivity(),1);
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
				// TODO Auto-generated method stub
				LogUtis.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+arg0.result);
				UserIsLoginActivity.lanuch(getActivity());
			}
		});
	}

}
