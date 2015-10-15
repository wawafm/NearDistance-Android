package com.neighbor.widget;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.neighbor.bean.CommentBean;
import com.neighor.neighbor001.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReplyListView extends LinearLayout {

	private List<CommentBean> mData;
	private Context mContext;
	private ItemOnClickListener mClickListener;


	public ReplyListView(Context context) {
		super(context);
		mContext = context;

	}

	public ReplyListView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		mContext = context;
	}

	public void setOnItemClickListener(ItemOnClickListener ls) {
		mClickListener = ls;
	}
	
	

	/**
	 * 删除ListView中上一次渲染的View，并添加新View。
	 */
	private void buildList() {
		if (mData == null) {

		}

		if (getChildCount() > 0) {
			removeAllViews();
		}

		int count = mData.size();

		for (int i = 0; i < count; i++) {
			TextView view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.list_simple_textview, null);
			Drawable d =null; 
			/*if(i == 0){
				d =  getResources().getDrawable(R.drawable.music_comment_down);
				
			}else{
				d = getResources().getDrawable(R.drawable.w);
			}*/
			view.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
			view.setBackgroundResource(R.drawable.selector_list_child_item);
			setData(view, i);
			if (view != null) {
				addView(view, i);

			}
		}
	}

	private void setData(final TextView view, final int pos) {
		// TODO Auto-generated method stub
		final CommentBean reply = mData.get(pos);
		StringBuilder sb = new StringBuilder();

		if (2 == reply.getType()) {
			sb.append("<font color='#65c1c1' size='13sp'>"
					+ reply.getUserInfo().getName() + "</font>");
			sb.append("<font color='#666666' size='13sp'> 回复 </font>").append(
					"<font color='#65c1c1' size='13sp'>"
							+ reply.getReplyUserInfo().getName() + '：'
							+ "</font>");
		} else {
			sb.append("<font color='#65c1c1' size='13sp'>"
					+ reply.getUserInfo().getName() + '：' + "</font>");
		}
		sb.append("<font color='#666666' size='13sp'>" + reply.getContent()
				+ "</font>");
		view.setText(Html.fromHtml(sb.toString()));
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mClickListener != null) {
					mClickListener.onItemClick(view, reply);
				}
			}
		});
		view.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				if(mClickListener != null){
					mClickListener.onLongClick(view, reply,pos);
				}
				return true;
			}
		});
		
	}

	/**
	 * 设置Adapter。
	 * 
	 * @param selfAdapter
	 */
	public void setSelfAdapter(List<CommentBean> data) {
		mData = data;
		Collections.sort(mData, new Comparator<CommentBean>() {

			@Override
			public int compare(CommentBean c1, CommentBean c2) {
				// TODO Auto-generated method stub

				if (c1 != null && c2 != null) {
					if (c1.getId() > c2.getId()) {
						return 1;
					} else if (c1.getId() == c2.getId())
						return 0;
					else
						return -1;
				} else
					return 0;
			}
		});
		buildList();
	}

	public interface ItemOnClickListener {

		void onItemClick(View v, CommentBean comment);
		void onLongClick(View v,CommentBean comment,int pos);
	}

}
