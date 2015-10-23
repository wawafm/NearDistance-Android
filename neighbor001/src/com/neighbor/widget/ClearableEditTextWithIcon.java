package com.neighbor.widget;

import com.neighor.neighbor001.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class ClearableEditTextWithIcon extends EditText implements TextWatcher, View.OnTouchListener {
	final Drawable deleteImage = getResources().getDrawable(R.drawable.setting_delete);
	Drawable icon;

	public ClearableEditTextWithIcon(Context paramContext) {
		super(paramContext);
		init();
	}

	public ClearableEditTextWithIcon(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		init();
	}

	public ClearableEditTextWithIcon(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		init();
	}

	private void init() {
		setOnTouchListener(this);
		addTextChangedListener(this);
		this.deleteImage.setBounds(0, 0, this.deleteImage.getIntrinsicWidth(), this.deleteImage.getIntrinsicHeight());
		manageClearButton();
	}

	void addClearButton() {
		setCompoundDrawables(this.icon, getCompoundDrawables()[1], this.deleteImage, getCompoundDrawables()[3]);
	}

	public void afterTextChanged(Editable paramEditable) {
	}

	public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
	}

	void manageClearButton() {
		if (getText().toString().equals("")) {
			removeClearButton();
			return;
		}
		addClearButton();
	}

	public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
		manageClearButton();
	}

	public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
		if (getCompoundDrawables()[2] == null)
			;
		while ((paramMotionEvent.getAction() != 1) || (paramMotionEvent.getX() <= getWidth() - getPaddingRight() - this.deleteImage.getIntrinsicWidth()))
			return false;
		setText("");
		removeClearButton();
		return false;
	}

	void removeClearButton() {
		setCompoundDrawables(this.icon, getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
	}

	public void setIconResource(int paramInt) {
		this.icon = getResources().getDrawable(paramInt);
		this.icon.setBounds(0, 0, this.icon.getIntrinsicWidth(), this.icon.getIntrinsicHeight());
		manageClearButton();
	}
}