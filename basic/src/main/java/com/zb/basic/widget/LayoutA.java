package com.zb.basic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zb.common.utils.P;

public class LayoutA extends FrameLayout {

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        P.p("A(Layout)::onIntercept()");
//        P.p(ev.toString());
        boolean result = super.onInterceptTouchEvent(ev);
//        P.p("A(Layout)::onInterceptEnd");
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        P.p("A(Layout)::dispatch()");
//        return true;
        boolean result = super.dispatchTouchEvent(event);
        P.p("A(Layout)::dispatchEnd");
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        P.p("A(Layout)::onTouchEvent()");
        boolean result = super.onTouchEvent(event);
        P.p("A(Layout)::onTouchEnd");
        return result;
    }



    public LayoutA(Context context) {
        super(context);
    }

    public LayoutA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LayoutA(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
