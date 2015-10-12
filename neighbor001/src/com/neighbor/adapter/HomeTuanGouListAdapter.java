package com.neighbor.adapter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.neighbor001.R;
import com.neighbor.bean.DealBean;
import com.neighbor.bean.ShopBean;
import com.neighbor.utils.LogUtis;
import com.neighbor.utils.Util;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Paint;
import android.media.tv.TvContentRating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeTuanGouListAdapter extends BaseAdapter{

	private Context mContext;
//	private List<Map<String,Object>> tuanGouStr = null;
	List<DealBean> dealBeansList = null;
	List<ShopBean> shopBeanList = null;
	public HomeTuanGouListAdapter(Context mContext,List<ShopBean> shopBeansList,List<DealBean> dealBeansList){
		this.mContext = mContext;
		this.shopBeanList = shopBeansList;
		this.dealBeansList = dealBeansList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public Object getItem(int item) {
		// TODO Auto-generated method stub
		return shopBeanList.get(item);
	}
	@Override
	public long getItemId(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vh = null;
		if (convertView==null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.home_tuangou_list_layout, null);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
//		vh.tuanGouListTitle.setText(shopMsgs.get(position).getShop_name());  //店名
		vh.tuanGouMoreDetail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);    // 下划线
		
		
		ShopBean sBean = shopBeanList.get(position);
		
		DealBean dBean = dealBeansList.get(position);
		if (sBean!=null) {
			vh.tuanGouListTitle.setText(sBean.getShop_name());
		}
		if (dBean!=null) {
			vh.tuanGouDetail.setText(dBean.getDeal_desc());
			Util.displayImage(vh.tuanGouImg,dBean.getDeal_img());
		}
		
		return convertView;
	}
	
	//初始化ViewHolder
	public class ViewHolder{
		private ImageView tuanGouImg;
		private TextView tuanGouListTitle;
		private TextView tuanGouDetail;
		private TextView tuanGouMoreDetail;
		private Button qiangGouBtn;
		public ViewHolder(View view) {
			// TODO Auto-generated constructor stub
			tuanGouImg = (ImageView) view.findViewById(R.id.tuangouImg);
			tuanGouListTitle = (TextView) view.findViewById(R.id.tuanGouMsgTitle);
			tuanGouDetail = (TextView) view.findViewById(R.id.tuanGouMsgDetail);
			tuanGouMoreDetail = (TextView) view.findViewById(R.id.textDetail);
			qiangGouBtn = (Button) view.findViewById(R.id.homeQiangGou);
		}
	}

}
