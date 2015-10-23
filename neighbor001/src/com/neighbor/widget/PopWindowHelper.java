package com.neighbor.widget;

import java.util.ArrayList;
import java.util.List;

import com.neighbor.activity.UserQueryChargeMsgActivity;
import com.neighbor.adapter.PopupwindowMessageAdapter;
import com.neighor.neighbor001.R;

import android.R.integer;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

public class PopWindowHelper {

	private static PopWindowHelper popWindowHelper;
	private PopupWindow popupWindow;
	private static Context context;
	private List<String> yList;
	
	public static void init(Context context){
		 PopWindowHelper.context = context;
	}
	
	public static PopWindowHelper getInstance(){
		if (popWindowHelper==null) {
			popWindowHelper = new PopWindowHelper();
		}
		return popWindowHelper;
	}
	
	private PopWindowHelper(){}
	
	private void initPopupWindow(){
		if (context==null) {
			
		}else {
			if (popupWindow==null) {
				popupWindow = new PopupWindow(context);
			}
		}
		
	}
	
	private void initPopupWindow(int width,int height) {
		
		this.initPopupWindow();
		popupWindow.setWidth(width);
		popupWindow.setHeight(height);
	}
	private void initPopupWindow(View contentView,int width,int height){
		this.initPopupWindow(width,height);
		popupWindow.setContentView(contentView);
	}
	
	public void showPopListView(View anchor, List<String> list, int width, int heigth) {
        if (popupWindow == null) {
            this.initPopupWindow(width, heigth);
        }
        View view = careatContentView(list);
        popupWindow.setContentView(view);
        //点击其他的地方时候popupwindow消失
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        //设置popupwindow背景色透明
        ColorDrawable dw = new ColorDrawable(-00000);
        //点击手机返回键的时候不会直接退出activity。所以要设置它的焦点为true，并且要设置它的背景才能相应手机的返回事件。
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(dw);
        //设置进出场动画
        popupWindow.setAnimationStyle(R.style.popupwindowStyle);
        popupWindow.showAtLocation(anchor, Gravity.TOP, 200, 250);
    }

    private View careatContentView(List<String> list) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_content_layout, null);
        final ListView listView = (ListView) view.findViewById(R.id.pop_listview);
        PopupwindowMessageAdapter adapter = new PopupwindowMessageAdapter(context, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				initData();
				ArrayAdapter<String> adapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,yList);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						UserQueryChargeMsgActivity.lanuch(context);
					}
					
				});
			}

			
		});
        return view;
    }

    private void initData() {
		// TODO Auto-generated method stub
		yList = new ArrayList<String>();
		for (int i = 1; i <=12; i++) {
			yList.add(i+"");
		}
	}
    
    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
