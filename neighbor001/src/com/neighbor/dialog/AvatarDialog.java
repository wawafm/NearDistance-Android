package com.neighbor.dialog;

import com.neighor.neighbor001.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AvatarDialog extends Dialog {



	

	private Context mContext;
	private View.OnClickListener mClickListener;
	private String title;

	

	public AvatarDialog(Context context,String title,View.OnClickListener listener) {
		super(context, R.style.ShareDialog);
		
		mContext = context;
		mClickListener = listener;
		this.title = title;
		LayoutParams lay = this.getWindow().getAttributes();
		setParams(lay);
		
	}

	private void setParams(LayoutParams lay) {
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
		Rect rect = new Rect();
		View view = getWindow().getDecorView();
		view.getWindowVisibleDisplayFrame(rect);
		lay.height = dm.heightPixels - rect.top;
		lay.width = dm.widthPixels;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.dlg_avatar_change);
		TextView btnPhoto = (TextView) findViewById(R.id.btnPhoto);
		TextView btnCarmer = (TextView) findViewById(R.id.btnCarmer);
		TextView layoutUp = (TextView) findViewById(R.id.layoutUp);
		TextView tvTitle = (TextView)findViewById(R.id.shareTitle);
		tvTitle.setText(title);
		layoutUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
		Button btnCacel = (Button) findViewById(R.id.cancel);

		final int cFullFillWidth = 10000;
		
		// set a large value put it in bottom
		Window w = getWindow();
		w.setWindowAnimations(R.anim.slide_right_in);
		WindowManager.LayoutParams lp = w.getAttributes();
		lp.x = 0;
		final int cMakeBottom = -1000;
		lp.y = cMakeBottom;
		lp.width = LayoutParams.MATCH_PARENT;
		lp.gravity = Gravity.BOTTOM;
		onWindowAttributesChanged(lp);
		setCanceledOnTouchOutside(true);
		//w.setWindowAnimations(R.style.dialogWindowAnim);
		btnPhoto.setOnClickListener(mClickListener);
		btnCarmer.setOnClickListener(mClickListener);
		
		btnCacel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}



	


}
