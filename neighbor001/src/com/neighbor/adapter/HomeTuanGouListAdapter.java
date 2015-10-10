package com.neighbor.adapter;

import java.util.List;

import com.example.neighbor001.R;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeTuanGouListAdapter extends BaseAdapter{

	private Context mContext;
	private List<String> tuanGouStr = null;
	public HomeTuanGouListAdapter(Context mContext,List<String> tuanGouStr){
		this.mContext = mContext;
		this.tuanGouStr = tuanGouStr;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tuanGouStr.size();
	}

	@Override
	public Object getItem(int item) {
		// TODO Auto-generated method stub
		return tuanGouStr.get(item);
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
		vh.tuanGouListTitle.setText(tuanGouStr.get(position));
		vh.tuanGouMoreDetail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		return convertView;
	}
	
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
