package com.zb.review.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.zb.review.utils.P;

public class TouchView extends View {
    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                P.p("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                P.p("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                P.p("ACTION_UP");
                break;
            default:
                P.p(" default: " + event.getAction());
                break;
        }


        return true;
    }
}
