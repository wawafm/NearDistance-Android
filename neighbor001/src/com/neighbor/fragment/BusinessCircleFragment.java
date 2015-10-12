package com.neighbor.fragment;

import com.example.neighbor001.AppConfig;
import com.example.neighbor001.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.neighbor.widget.PullRefreshWebView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BusinessCircleFragment extends Fragment{
	
	private PullRefreshWebView businessWebView;
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
		businessWebView = (PullRefreshWebView) view.findViewById(R.id.businessWebView);
		businessWebView.getRefreshableView().loadUrl(businessUrl);
		//当webview进行加载网页时候进行监听，点击网页中其他内容的时候，不用手机本地浏览器打开，而是用应用程序自带的webview打开。。
		businessWebView.getRefreshableView().setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
			
		});
		
		businessWebView.setOnRefreshListener(new OnRefreshListener<WebView>() {

			@Override
			public void onRefresh(PullToRefreshBase<WebView> refreshView) {
				// TODO Auto-generated method stub
				onWebviewRefresh(businessWebView);
			}

		});
	}

	private void onWebviewRefresh(PullRefreshWebView businessWebView) {
		// TODO Auto-generated method stub
		businessWebView.getRefreshableView().loadUrl(businessUrl);
	}
}
