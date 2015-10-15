/**
 * 
 */
/**
 * @author xzh
 *
 */
package com.neighbor.adapter;

import com.neighor.neighbor001.R;
import com.xiaomi.push.service.l;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeGridViewAdapter extends BaseAdapter{
	
	private Context mContext;
	private int[] logo;
	private String[] str;
	public HomeGridViewAdapter(Context mContexts,int[] logo,String[] str){
		this.mContext = mContexts;
		this.logo = logo;
		this.str = str;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return logo.length;
	}

	@Override
	public Object getItem(int item) {
		// TODO Auto-generated method stub
		return logo[item];
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
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.home_gridview_adapter_layout, null);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		vh.logoImg.setImageResource(logo[position]);
		vh.typeText.setText(str[position]);
		return convertView;
	}
	
	public class ViewHolder{
		private ImageView logoImg;
		private TextView typeText;
		public ViewHolder(View view) {
			// TODO Auto-generated constructor stub
			logoImg = (ImageView) view.findViewById(R.id.logoImg);
			typeText = (TextView) view.findViewById(R.id.logoText);
		}
	}
	
}