package com.neighbor.activity;

import com.neighbor.widget.PullRefreshWebView;
import com.neighor.neighbor001.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HouseHireActivity extends BaseActivity{

	private PullRefreshWebView webView;
	private Context mContext;
	private String hireUrl;
	public static void lanuch(Context mContext,String url){
		Intent intent = new Intent(mContext,HouseHireActivity.class);
		intent.putExtra("hireurl", url);
		mContext.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.house_hire_layout);
		setTitle("房屋租赁");
		setTextRight("",0);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		webView = (PullRefreshWebView) findViewById(R.id.houseHireWeb);
		WebSettings settings = webView.getRefreshableView().getSettings();
		settings.setJavaScriptEnabled(true);   //设置可以访问JavaScript
		settings.setAllowFileAccess(true);      //设置可以访问文件
		webView.getRefreshableView().loadUrl("http://m.fang.com/zf/huizhou/");
		//当webview进行加载网页时候进行监听，点击网页中其他内容的时候，不用手机本地浏览器打开，而是用应用程序自带的webview打开。。
		webView.getRefreshableView().setWebViewClient(new LoadWebViewClient());
	}
	private class LoadWebViewClient extends WebViewClient{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}
		
	}
}
