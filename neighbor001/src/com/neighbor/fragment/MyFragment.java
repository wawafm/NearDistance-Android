package com.neighbor.fragment;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.neighbor.activity.ChangeNiChengActivity;
import com.neighbor.activity.MainActivity;
import com.neighbor.activity.SuperMenberActivity;
import com.neighbor.activity.UserAddressActivity;
import com.neighbor.activity.UserIsLoginActivity;
import com.neighbor.activity.UserStartLoginActivity;
import com.neighbor.api.imp.HttpUtils;
import com.neighbor.dialog.AvatarDialog;
import com.neighbor.dialog.ConfirmDialog;
import com.neighbor.utils.LogUtis;
import com.neighbor.widget.CircleImageViewLine;
import com.neighor.neighbor001.R;
import com.neighor.neighbor001.NeighborConstants;
import com.xiaomi.mipush.sdk.MiPushClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MyFragment extends Fragment implements OnClickListener{
	
	private CircleImageViewLine loginUserImg;
	private TextView userPhone,userType,changeAvatar,changeNicheng,superMenber,userAddress,aboutNeighbor,userExit;
	private AvatarDialog dlg;
	private String filePath;
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//已经登录  my_islogin_layout,my_register_fragment_layout,my_login_fragment_layout
		View view = inflater.inflate(R.layout.my_islogin_layout,container,false);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View v) {
		// TODO Auto-generated method stub
		loginUserImg = (CircleImageViewLine)v.findViewById(R.id.loginUserImg);
		userPhone = (TextView)v.findViewById(R.id.userPhone);
		userType = (TextView)v.findViewById(R.id.userType);
		
		changeAvatar = (TextView)v.findViewById(R.id.changeAvatar);
		changeNicheng = (TextView)v.findViewById(R.id.changeNicheng);
		superMenber = (TextView)v.findViewById(R.id.superMenber);
		userAddress = (TextView)v.findViewById(R.id.userAddress);
		aboutNeighbor = (TextView)v.findViewById(R.id.aboutNeighbor);
		userExit = (TextView)v.findViewById(R.id.userExit);
		
		changeAvatar.setOnClickListener(this);
		changeNicheng.setOnClickListener(this);
		superMenber.setOnClickListener(this);
		userAddress.setOnClickListener(this);
		aboutNeighbor.setOnClickListener(this);
		userExit.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.changeAvatar:
			changeAvatar();
			break;
		case R.id.changeNicheng:
			ChangeNiChengActivity.lanuch(getActivity(),"小菜鸟");
			break;
		case R.id.superMenber:
			SuperMenberActivity.lanuch(getActivity());
			break;
		case R.id.userAddress:
			UserAddressActivity.lanuch(getActivity());
			break;
		case R.id.aboutNeighbor:
			
			break;
		case R.id.userExit:
			builddialog();
			break;

		default:
			break;
		}
	}
	
	private void changeAvatar() {
		// TODO Auto-generated method stub
		dlg = new AvatarDialog(getActivity(),"设置头像",
				new View.OnClickListener() {
					Intent intent = null;

					@Override
					public void onClick(View v) {
						switch (v.getId()) {
						case R.id.btnCarmer:

							File dir = new File(NeighborConstants.MyAvatarDir);
							if (!dir.exists()) {
								dir.mkdirs();
							}
							File file = new File(dir, new SimpleDateFormat(
									"yyMMddHHmmss").format(new Date()));
							filePath = file.getAbsolutePath();// 获取相片的保存路径
							Uri imageUri = Uri.fromFile(file);

							intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
							startActivityForResult(
									intent,
									NeighborConstants.REQUESTCODE_UPLOADAVATAR_CAMERA);
							dlg.dismiss();
							break;
						case R.id.btnPhoto:

							intent = new Intent(Intent.ACTION_PICK, null);
							intent.setDataAndType(
									MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
									"image/*");
							startActivityForResult(
									intent,
									NeighborConstants.REQUESTCODE_UPLOADAVATAR_LOCATION);
							dlg.dismiss();
							break;

						default:
							break;
						}
					}
				});
		dlg.show();
	}
	
	private void builddialog() {

		final ConfirmDialog dlg2 = ConfirmDialog.createDialog(getActivity());
		dlg2.setTitle("退出登录");
		dlg2.setMessage("你确定退出登录吗？");
		dlg2.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences share = getActivity().getSharedPreferences("sharedpreference",getActivity().MODE_PRIVATE);
				/** 参数编辑器 */
				Editor editor = share.edit();
				editor.clear();
				/** 提交保存 */
				editor.commit();
				Intent intent = new Intent(getActivity(),UserStartLoginActivity.class);
				getActivity().startActivity(intent);
				dlg2.dismiss();
				getActivity().finish();
			}
		});
	}
}
