package com.neighbor.dialog;

import com.neighor.neighbor001.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConfirmDialog extends Dialog {

	private Context mContext;
	

	private TextView mTitle,mContent;
	private Button mOkButton,mCancelButton;
	private ImageView line;
	private  static ConfirmDialog dlg;
	public ConfirmDialog(Context context) {
		super(context);
		
		
	}

	private ConfirmDialog(Context context, int theme) {
		super(context, theme);
	}
	public static ConfirmDialog createDialog(Context context) {

		dlg = new ConfirmDialog(context, R.style.NotTitleBarDialog);
		//dlg.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dlg.show();
		dlg.setContentView(R.layout.confirm_dialog_item5);
		dlg.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
		dlg.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
		

		return dlg;

	}
	
	public void setTitleHide(){
		mTitle = (TextView) findViewById(R.id.confirm_dialog_title_tv);
		mTitle.setVisibility(View.GONE);
	}
	/*public void setPadding(){
		mContent = (TextView) findViewById(R.id.confirm_dialog_content);
		mContent.setPadding(0, 30, 0, 0);
	}*/
	public void setTitle(int resid)
	{
		mTitle = (TextView) findViewById(R.id.confirm_dialog_title_tv);
		mTitle.setText(resid);
	}
	
	public void setMessage(int resid){
		mContent = (TextView) findViewById(R.id.confirm_dialog_content);
		mContent.setText(resid);
	}
	
	public void setTitle(String tips)
	{
		mTitle = (TextView) findViewById(R.id.confirm_dialog_title_tv);
		mTitle.setText(tips);
	}
	
	public void setMessage(String message){
		mContent = (TextView) findViewById(R.id.confirm_dialog_content);
		mContent.setText(message);
	}
	
	public void setNegativeButton(CharSequence text, View.OnClickListener ls)
	{
	
		mCancelButton = (Button)dlg.findViewById(R.id.cancel);
		mCancelButton.setText(text);
		mCancelButton.setOnClickListener(ls);
	}
	
	public void setPositiveButton(CharSequence text, View.OnClickListener ls)
	{
		
		mOkButton = (Button)dlg.findViewById(R.id. confirm);
		mOkButton.setOnClickListener(ls);
		mOkButton.setText(text);
	}
	
	

}
