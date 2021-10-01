package com.zb.review.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * 触摸事件 down->move-move...->up/cancel(cancel事件比较特殊,时间序列非人为结束)
 */

public class TouchLayout extends ScrollView {







    public TouchLayout(Context context) {
        super(context);
    }

    public TouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
