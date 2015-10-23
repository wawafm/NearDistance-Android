package com.neighbor.update;

import com.neighor.neighbor001.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class UpdateDialog extends Activity implements OnClickListener {

	private TextView message1;
	private Button negativeBtn, positiveBtn;
	private Version mVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_dialog_update);
		mVersion = (Version) getIntent().getSerializableExtra("ver");

		message1 = (TextView) findViewById(R.id.confirm_dialog_content);

		negativeBtn = (Button) findViewById(R.id.confirm);
		positiveBtn = (Button) findViewById(R.id.cancel);
		message1.setText(mVersion.getRemarks());

		negativeBtn.setOnClickListener(this);
		positiveBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent it = new Intent(this, VersionManager.class);
		switch (v.getId()) {
		case R.id.cancel:

			it.setAction(VersionManager.UPDATE_CANCEL);
			startService(it);
			finish();
			break;
		case R.id.confirm:

			it.setAction(VersionManager.UPDATE_START);
			startService(it);
			finish();
			break;

		default:
			break;
		}
	}

}
