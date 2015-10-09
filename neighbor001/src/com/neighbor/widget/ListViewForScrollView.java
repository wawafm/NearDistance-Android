package com.neighbor.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @Package com.qianfeng.village.weight
 * @作 用:
 * @创 建 人: wxianing
 * @日 期: 2015/3/23
 * @修 改 人:
 * @日 期:
 */
public class ListViewForScrollView extends ListView {
    public ListViewForScrollView(Context context) {
        super(context);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, height);
    }
}
