package com.neighbor.activity;

import java.util.ArrayList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.neighbor.adapter.JinRiYouHuiAdapter;
import com.neighbor.bean.DealBean;
import com.neighbor.bean.ShopBean;
import com.neighbor.utils.LogUtis;
import com.neighbor.widget.RefreshListView;
import com.neighor.neighbor001.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class JinRiYouHuiActivity extends BaseActivity{
	
	private RefreshListView refreshListView;
	private ArrayList<DealBean> youHuiDealBeans;
	private ArrayList<ShopBean> youHuiShopBeans;
	private WebView vb;
	/*private ArrayList<DealBean> youHuiDealBeans = null;
	private ArrayList<ShopBean> youHuiShopBeans = null;*/
	public static void Lanuch(Context mContext,ArrayList<DealBean> youHuiDealBeans,ArrayList<ShopBean> youHuiShopBeans) {
		Intent it = new Intent(mContext, JinRiYouHuiActivity.class);
		it.putExtra("youHuiDealBeans",youHuiDealBeans);
		it.putExtra("youHuiShopBeans",youHuiShopBeans);
		mContext.startActivity(it);
//		((Activity) ctx).overridePendingTransition(R.anim.slide_right_in, 0);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jinri_youhui_layout);
		setTitleImage(R.drawable.shouyelogo);
		
		initListView();
	}

	private void initListView() {
		// TODO Auto-generated method stub
		refreshListView = (RefreshListView) findViewById(R.id.YouHuiList);
		youHuiDealBeans = (ArrayList<DealBean>) getIntent().getSerializableExtra("youHuiDealBeans");
		youHuiShopBeans = (ArrayList<ShopBean>) getIntent().getSerializableExtra("youHuiShopBeans");
		JinRiYouHuiAdapter youHuiAdapter = new JinRiYouHuiAdapter(JinRiYouHuiActivity.this, youHuiDealBeans, youHuiShopBeans);
		refreshListView.setAdapter(youHuiAdapter);
		refreshListView.setMode(Mode.BOTH);
//		refreshListView.setOnRefreshListener(new OnRefreshListListener());
		refreshListView.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				if (youHuiDealBeans!=null&&youHuiDealBeans.size()>0) {
					
					JinRiYouHuiListActivity.Lanuch(JinRiYouHuiActivity.this, youHuiDealBeans.get(position).getDeal_wap_url());
				}
				
			}
		});
	}
	
	public class OnRefreshListListener implements OnRefreshListener2<ListView>{

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			// TODO Auto-generated method stub
//			LogUtis.showTast(JinRiYouHuiActivity.this,">>>>>>>>下拉刷新");
		}

		@Override
		public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
			// TODO Auto-generated method stub
//			LogUtis.showTast(JinRiYouHuiActivity.this,">>>>>>>>上拉加载更多");
		}
		
	}
}
