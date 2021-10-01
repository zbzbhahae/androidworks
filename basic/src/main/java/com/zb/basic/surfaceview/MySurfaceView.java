package com.zb.basic.surfaceview;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.zb.common.utils.P;

public class MySurfaceView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    private SurfaceHolder mSurfaceHolder;

    public MySurfaceView(Context context) {
        this(context, null, 0,0);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView(){
        mSurfaceHolder = getHolder();
        //注册回调方法
        mSurfaceHolder.addCallback(this);
        //设置一些参数方便后面绘图
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //创建
        P.p("MySurfaceView::surfaceCreated()");
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        //改变
        P.p("MySurfaceView::surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        //销毁
        P.p("MySurfaceView::surfaceDestroyed()");
    }

    @Override
    public void run() {

    }



    private void drawBack() {
        int w = getWidth();
        int h = getHeight();


    }
}
