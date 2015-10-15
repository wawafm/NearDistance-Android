package com.neighbor.adapter;

import java.util.List;

import com.neighbor.bean.DealBean;
import com.neighbor.bean.ShopBean;
import com.neighbor.utils.Util;
import com.neighor.neighbor001.R;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class JinRiYouHuiAdapter extends BaseAdapter{

	private Context mContext;
	private List<DealBean> youHuiDealBeans = null;
	private List<ShopBean> youHuiShopBeans = null;
	
	public JinRiYouHuiAdapter(Context mContext,List<DealBean> youHuiDealBeans,List<ShopBean> youHuiShopBeans){
		this.mContext = mContext;
		this.youHuiDealBeans = youHuiDealBeans;
		this.youHuiShopBeans = youHuiShopBeans;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return youHuiShopBeans.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return youHuiShopBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		YouHuiViewHolder youHuiViewHolder = null;
		if (convertView == null) {
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.home_tuangou_list_layout, null);
			youHuiViewHolder = new YouHuiViewHolder(convertView);
			convertView.setTag(youHuiViewHolder);
		}else {
			youHuiViewHolder = (YouHuiViewHolder) convertView.getTag();
		}
		Util.displayImage(youHuiViewHolder.tuanGouImg,youHuiDealBeans.get(position).getDeal_img());
		youHuiViewHolder.tuanGouListTitle.setText(youHuiShopBeans.get(position).getShop_name());
		youHuiViewHolder.tuanGouDetail.setText(youHuiDealBeans.get(position).getDeal_desc());
		
		return convertView;
	}
	
	//初始化ViewHolder
		public class YouHuiViewHolder{
			private ImageView tuanGouImg;
			private TextView tuanGouListTitle;
			private TextView tuanGouDetail;
			private TextView tuanGouMoreDetail;
			private Button qiangGouBtn;
			public YouHuiViewHolder(View view) {
				// TODO Auto-generated constructor stub
				tuanGouImg = (ImageView) view.findViewById(R.id.tuangouImg);
				tuanGouListTitle = (TextView) view.findViewById(R.id.tuanGouMsgTitle);
				tuanGouDetail = (TextView) view.findViewById(R.id.tuanGouMsgDetail);
				tuanGouMoreDetail = (TextView) view.findViewById(R.id.textDetail);
				qiangGouBtn = (Button) view.findViewById(R.id.homeQiangGou);
			}
		}

}
