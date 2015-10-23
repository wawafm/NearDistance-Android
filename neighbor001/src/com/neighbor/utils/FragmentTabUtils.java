package com.neighbor.utils;

import java.util.ArrayList;

import com.neighor.neighbor001.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FragmentTabUtils implements RadioGroup.OnCheckedChangeListener{

	private int containerId;
	private ArrayList<Fragment> fragments;
	private RadioGroup rgs;
	private TextView leftTitle,rightTitle;
	private FragmentManager manager;
	private String[] title = {"邻距","邻居圈","商圈","个人中心"}; 
	public FragmentTabUtils(int containerId, ArrayList<Fragment> fragments,
			RadioGroup rgs, FragmentManager manager,TextView leftTitle,TextView rightTitle) {
		super();
		this.containerId = containerId;
		this.fragments = fragments;
		this.rgs = rgs;
		this.manager = manager;
		this.leftTitle = leftTitle;
		this.rightTitle = rightTitle;
		this.leftTitle.setText(title[0]);
		((RadioButton)rgs.getChildAt(0)).setChecked(true);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(containerId, fragments.get(0));
        transaction.show(fragments.get(0));
        transaction.commit();
        rgs.setOnCheckedChangeListener(this);
		
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < group.getChildCount(); i++) {
            Fragment fragment = fragments.get(i);
            if (checkedId == group.getChildAt(i).getId()) {
                addFragment(fragment);
                leftTitle.setText(title[i]);
                if (i==1) {
					rightTitle.setVisibility(View.VISIBLE);
				}else {
					rightTitle.setVisibility(View.GONE);
				}
            } else {
                FragmentTransaction t = manager.beginTransaction();
                t.hide(fragment);
                t.commit();
            }
        } 
	}
	
	private void addFragment(Fragment f) {
        FragmentTransaction t = manager.beginTransaction();
        if (!f.isAdded()) {
            t.add(R.id.frameLayout, f);
        }
        t.show(f);
        t.commit();
    }
	
	
}
