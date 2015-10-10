package com.neighbor.adapter;

import java.util.List;

import com.example.neighbor001.R;
import com.neighbor.widget.CircularImage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InteractListAdapter extends BaseAdapter{

	private List<String> list = null;
	private Context mContext;
	
	public InteractListAdapter(Context mContext,List<String> list){
		this.mContext = mContext;
		this.list = list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int item) {
		// TODO Auto-generated method stub
		return list.get(item);
	}

	@Override
	public long getItemId(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;		
		if (convertView == null) {
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.interact_listview_content_layout,null);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.nameText.setText(list.get(position));
		return convertView;
	}

	public class ViewHolder{
		
		private CircularImage commontAvatar;
		private TextView nameText;
		private TextView commontText;
		private ImageView publishImg;
		private TextView commontTime;
		private TextView replyMsg;
		private TextView replyContentText;
		private CircularImage replyAvatarImg;
		
		public ViewHolder(View view) {
			commontAvatar = (CircularImage) view.findViewById(R.id.commontAvatarImg);
			nameText = (TextView) view.findViewById(R.id.nameText);
			commontText = (TextView) view.findViewById(R.id.commentText);
			publishImg = (ImageView) view.findViewById(R.id.publishImg);
			commontTime = (TextView) view.findViewById(R.id.commontTime);
			replyMsg = (TextView) view.findViewById(R.id.replyMsg);
			replyContentText = (TextView) view.findViewById(R.id.replyContentText);
			replyAvatarImg = (CircularImage) view.findViewById(R.id.replyAvatarImg);
		}
	}
}
