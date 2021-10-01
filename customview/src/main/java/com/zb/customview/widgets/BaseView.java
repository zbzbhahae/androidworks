package com.zb.customview.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class BaseView extends View {
    protected int width, height;

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    protected int dp2px(int dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp + 0.5);
    }
    protected int sp2px(int sp) {
        return (int) (getContext().getResources().getDisplayMetrics().scaledDensity * sp + 0.5);
    }

}
