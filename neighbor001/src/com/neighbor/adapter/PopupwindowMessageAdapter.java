package com.neighbor.adapter;

import java.util.List;

import com.neighor.neighbor001.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopupwindowMessageAdapter extends BaseAdapter{
	
	private List<String> list;
	private Context mContext;
	
	public PopupwindowMessageAdapter(Context mcContext,List<String> list) {
		
		this.list = list;
		this.mContext = mcContext;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView==null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_message_layout, null);
			vh = new ViewHolder(convertView);
			convertView.setTag(convertView);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
		
		vh.popItemText.setText(list.get(position));
		return convertView;
	}
	private class ViewHolder{
		private TextView popItemText;
		
		public ViewHolder(View v){
			popItemText = (TextView) v.findViewById(R.id.popItemText);
		}
	}

}
