package com.neighbor.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.neighbor001.R;
import com.neighbor.adapter.InteractListAdapter;
import com.neighbor.widget.CircularImage;
import com.neighbor.widget.RefreshListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InteractFragment extends Fragment{

	
	private RefreshListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.interact_fragment_layout,container,false);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		listView = (RefreshListView) view.findViewById(R.id.huDongList);
		initListView();
	}

	private void initListView() {
		
		//初始化listview要显示的数据
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			list.add("baby"+i);
		}
		InteractListAdapter listAdapter = new  InteractListAdapter(getActivity(),list);
		listView.setAdapter(listAdapter);
	}
}
