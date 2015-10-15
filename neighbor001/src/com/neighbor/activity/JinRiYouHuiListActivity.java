package com.neighbor.activity;

import com.neighbor.widget.PullRefreshScrollerView;
import com.neighbor.widget.PullRefreshWebView;
import com.neighor.neighbor001.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JinRiYouHuiListActivity extends BaseActivity{
	
	private PullRefreshWebView pWebView;
	
	public static void Lanuch(Context mContext,String url){
		Intent it = new Intent(mContext,JinRiYouHuiListActivity.class);
		it.putExtra("url", url);
		mContext.startActivity(it);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.business_circle_fragment_layout);
		initRefreshScrollView();
		setTitleImage(R.drawable.shouyelogo);
	}
	private void initRefreshScrollView() {
		// TODO Auto-generated method stub
		pWebView = (PullRefreshWebView) findViewById(R.id.businessWebView);
		WebSettings settings = pWebView.getRefreshableView().getSettings();
		settings.setJavaScriptEnabled(true);   //设置可以访问JavaScript
		settings.setAllowFileAccess(true);      //设置可以访问文件
		settings.setBuiltInZoomControls(true);    //设置图片被压缩
		pWebView.getRefreshableView().loadUrl(getIntent().getStringExtra("url"));
		//当webview进行加载网页时候进行监听，点击网页中其他内容的时候，不用手机本地浏览器打开，而是用应用程序自带的webview打开。。
		pWebView.getRefreshableView().setWebViewClient(new LoadWeViewClient());
	}
	private class LoadWeViewClient extends WebViewClient{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}
		
		
	}

}
