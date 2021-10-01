package com.zb.basic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.zb.common.utils.P;

public class ViewB extends View {



    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        P.p("B(View)::dispatchTouchEvent()");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        P.p("B(View)::onTouchEvent()");
        return super.onTouchEvent(event);
    }















    public ViewB(Context context) {
        super(context);
    }

    public ViewB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewB(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
