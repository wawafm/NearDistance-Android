package com.neighbor.fragment;

import com.example.neighbor001.R;
import com.neighbor.app.AppConfig;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class BusinessCircleFragment extends Fragment{
	
	private WebView businessWebView;
	private String businessUrl = AppConfig.BUSINESS_URL;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.business_circle_fragment_layout,container,false);
		return view;
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		businessWebView = (WebView) view.findViewById(R.id.businessWebView);
		businessWebView.loadUrl(businessUrl);
	}

}
