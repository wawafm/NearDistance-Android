package com.neighbor.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.neighbor.dialog.AvatarDialog;
import com.neighbor.dialog.ConfirmDialog;
import com.neighbor.widget.CircleImageViewLine;
import com.neighor.neighbor001.NeighborConstants;
import com.neighor.neighbor001.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class UserIsLoginActivity extends BaseActivity implements OnClickListener{

	private Context mContext;
	private CircleImageViewLine loginUserImg;
	private TextView userPhone,userType,changeAvatar,changeNicheng,superMenber,userAddress,aboutNeighbor,userExit;
	private String[] town = null;
	private String[] xiaoqu = null;
	private String[] room = null;
	private AvatarDialog dlg;
	private String filePath;
	
	public static void lanuch(Context mContext){
		Intent intent = new Intent(mContext,UserIsLoginActivity.class);
		mContext.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.my_islogin_layout);
		setTitle("个人中心");
		setTextRight("",0);
		initView();
	}
	private void initView() {
		// TODO Auto-generated method stub
		loginUserImg = (CircleImageViewLine)findViewById(R.id.loginUserImg);
		userPhone = (TextView)findViewById(R.id.userPhone);
		userType = (TextView)findViewById(R.id.userType);
		
		changeAvatar = (TextView)findViewById(R.id.changeAvatar);
		changeNicheng = (TextView)findViewById(R.id.changeNicheng);
		superMenber = (TextView)findViewById(R.id.superMenber);
		userAddress = (TextView)findViewById(R.id.userAddress);
		aboutNeighbor = (TextView)findViewById(R.id.aboutNeighbor);
		userExit = (TextView)findViewById(R.id.userExit);
		
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
			ChangeNiChengActivity.lanuch(UserIsLoginActivity.this,"小菜鸟");
			break;
		case R.id.superMenber:
			SuperMenberActivity.lanuch(UserIsLoginActivity.this);
			break;
		case R.id.userAddress:
			UserAddressActivity.lanuch(UserIsLoginActivity.this);
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
		dlg = new AvatarDialog(UserIsLoginActivity.this,"设置头像",
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

		final ConfirmDialog dlg2 = ConfirmDialog.createDialog(UserIsLoginActivity.this);
		dlg2.setTitle("退出登录");
		dlg2.setMessage("你确定退出登录吗？");
		dlg2.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(View v) {
				final ProgressDialog dialog = new ProgressDialog(UserIsLoginActivity.this);
				dialog.setMessage("正在退出...");
				dialog.show();
				/*HttpUtils.logout(new ICallBack<Result>() {

					@Override
					public void onResult(Result result) {
						// Intent service = new Intent(getActivity(),
						// PlayerService.class);
						// service.setAction(PlayerService.ACTION_STOP);
						// getActivity().startService(service);
						String uid = SharePreferenceUtil
								.getLoginName(getActivity());
						Util.setUserinfoActivityTag(getActivity(), true);
						Util.setWaPlayFragmentTag(getActivity(), true);
						MiPushClient.unsetUserAccount(getActivity(), uid, null);
						SharePreferenceUtil.clearAll(getActivity());
						setButtonText();
						dialog.dismiss();
					}

					@Override
					public void onError(Throwable err) {
						dialog.dismiss();
						LogUtis.showTast(getActivity(), "退出失败");

					}
				});*/

				dlg2.dismiss();

			}
		});
	}

}
