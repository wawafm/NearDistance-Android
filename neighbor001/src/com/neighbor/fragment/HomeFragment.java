
package com.neighbor.fragment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.neighbor.activity.HouseHireActivity;
import com.neighbor.activity.JinRiYouHuiActivity;
import com.neighbor.activity.JinRiYouHuiListActivity;
import com.neighbor.adapter.HomeGridViewAdapter;
import com.neighbor.adapter.HomeTuanGouListAdapter;
import com.neighbor.bean.DealBean;
import com.neighbor.bean.ShopBean;
import com.neighbor.widget.AutoScrollViewPager;
import com.neighbor.widget.PopWindowHelper;
import com.neighor.neighbor001.AppConfig;
import com.neighor.neighbor001.R;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;


public class HomeFragment extends Fragment{
	
	private ViewPager homePager;
	private ViewGroup homeRgs;
	private GridView homeGrid;
	private ListView homeList;
	private PullToRefreshScrollView pullToRefreshScrollView;
	private List<ImageView> imageViews= null;
	//存放XML解析的数据
//	private List<Map<String, Object>> beanList = null;
	private ArrayList<DealBean> dealBeansList = null;
	private ArrayList<ShopBean> shopBeansList = null;
	private DealBean dealBeanObj = null;
	private ShopBean shopBeanObj = null;
	private List<String> list = null;
	private HttpHandler httpHandler = null;
	private HttpUtils httpUtils=null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		httpUtils = new HttpUtils();
		homeGrid = (GridView) view.findViewById(R.id.homeContentType);
		homeList = (ListView) view.findViewById(R.id.homeTuanGouList);
		homeRgs = (ViewGroup) view.findViewById(R.id.homePageRadio);
		homePager = (ViewPager) view.findViewById(R.id.homeAutoViewPager);
		pullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.homeRefreshScrollLayout);
		pullToRefreshScrollView.setMode(Mode.BOTH);
//		pullToRefreshScrollView.setOnRefreshListener(new OnScrollRefreshListener());
		initGridView();
		initHomeList();
		initData();
		AutoScrollViewPager autoViewPager = new AutoScrollViewPager(getActivity(), homePager, homeRgs);
		autoViewPager.startScroll();
	}

	
	private void initData() {
		// TODO Auto-generated method stub
		list = new ArrayList<String>();
		list.add("2015");
		list.add("2016");
		list.add("2017");
		list.add("2018");
		list.add("2019");
	}


	public class OnScrollRefreshListener implements OnRefreshListener2<ScrollView>{

		@Override
		public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
			// TODO Auto-generated method stub
		}
		
	}
	
	
	private void initHomeList() {
		getMeiTuanData();
		
	}

	//获取美团接口里面的数据
	private void getMeiTuanData() {
		// TODO Auto-generated method stub
		httpHandler = httpUtils.send(HttpRequest.HttpMethod.GET,AppConfig.MEITUAN_URL,new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo){
				// TODO Auto-generated method stub
				InputStream is = null;
				try {
					is = new ByteArrayInputStream(responseInfo.result.getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				initXMLData(is);
			}
			
			@Override
			public void onFailure(HttpException e, String s) {
				// TODO Auto-generated method stub
				
			}

		});
	}
	
	private void initXMLData(InputStream result) {
		InputStreamReader isr = null;
		XmlPullParserFactory parserFactory = null;
		XmlPullParser parser = null;
		try {
			isr = new InputStreamReader(result);
			parserFactory = XmlPullParserFactory.newInstance();
			parser = parserFactory.newPullParser();
			parser.setInput(isr);
			int event = parser.getEventType();
			while (event!=XmlPullParser.END_DOCUMENT) {      
				switch (event) {
				case XmlPullParser.START_DOCUMENT:    
					//如果是开始标签，那么就开始创建集合。
//					beanList = new ArrayList<Map<String, Object>>();
					dealBeansList = new ArrayList<DealBean>();
					shopBeansList = new ArrayList<ShopBean>();
					break;
				case XmlPullParser.START_TAG:
					//获得节点的名字，判断节点是否等于某一节点。
					String tagName = parser.getName();
					if (tagName.equals("deal")) {
						//如果节点等于deal，那么开始创建DealBean对象。
						dealBeanObj = new DealBean();
					}else if (tagName.equals("shop")) {
						//如果节点等于shop，那么开始创建ShopBean对象。
						shopBeanObj = new ShopBean();
					}else if (tagName.equals("deal_title")) {
						dealBeanObj.setDeal_title(parser.nextText());
					}else if (tagName.equals("deal_desc")) {
						dealBeanObj.setDeal_desc(parser.nextText());
					}else if (tagName.equals("shop_name")) {
						shopBeanObj.setShop_name(parser.nextText());
					}else if (tagName.equals("deal_img")) {
						dealBeanObj.setDeal_img(parser.nextText());
					}else if (tagName.equals("deal_wap_url")) {
						dealBeanObj.setDeal_wap_url(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					//当解析到最后的根节点的时候，把对象存放在数组里，并让该对象为空对像，
					if ("deal".equals(parser.getName())) {
						
						dealBeansList.add(dealBeanObj);
						dealBeanObj = null;
					}
					
					if ("shop".equals(parser.getName())) {
						shopBeansList.add(shopBeanObj);
						shopBeanObj = null;
					}
					
					break;

				default:
					break;
				}
				event = parser.next();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HomeTuanGouListAdapter tuanGouListAdapter = new HomeTuanGouListAdapter(getActivity(),shopBeansList,dealBeansList);
		homeList.setAdapter(tuanGouListAdapter);
		homeList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (dealBeansList!=null&&dealBeansList.size()>0) {
					JinRiYouHuiListActivity.Lanuch(getActivity(),dealBeansList.get(position).getDeal_wap_url());
				}
				
			}
		});
	}

	private void initGridView() {
		// TODO Auto-generated method stub
		String str[] = {"费用查询","小区消息","保修求助","房屋租赁","今日优惠","投诉信箱"}; 
		int logo[] = {R.drawable.feiyongchaxun,R.drawable.xiaoquxiaoxi,R.drawable.baoxiuqiuzhu,
					R.drawable.fangwuzulin,R.drawable.jinriyouhui,R.drawable.tousuxinxiang}; 
		HomeGridViewAdapter gridViewAdapter = new HomeGridViewAdapter(getActivity(),logo,str);
		homeGrid.setAdapter(gridViewAdapter);
		homeGrid.setOnItemClickListener(new HomeGridItemClick());
	}

	public class HomeGridItemClick implements OnItemClickListener{

		int i = 1;
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch (position) {
			case 0:
				PopWindowHelper.init(getActivity());
				PopWindowHelper.getInstance().showPopListView(view, list,800,1718);
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				HouseHireActivity.lanuch(getActivity(), AppConfig.HOUSE_HIRE);
				break;
			case 4:
				JinRiYouHuiActivity.Lanuch(getActivity(),dealBeansList,shopBeansList);
				break;
			case 5:
				break;
			default:
				break;
			}
			
		}

	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
}