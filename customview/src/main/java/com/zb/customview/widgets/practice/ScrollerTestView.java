package com.zb.customview.widgets.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.zb.common.utils.P;


/**
 *
 */

public class ScrollerTestView extends View {

    public static final String TAG = "CustomView";

    private int width, height;

    private Paint paintTxt, paintLine;

    private int shaderColor = 0xffff0000;

    private float mDownX = 0;
    private float mDownY = 0;
    private float moveX = 0;
    private float moveY = 0;
    private int finalX = 0;
    private int finalY = 0;
    private float xVelocity = 0;
    private float yVelocity = 0;
    private Scroller mScroller;
    private VelocityTracker velocityTracker;

    // fling的最小速率
    int minFlingVelocity;

    private void initPaint() {
        paintTxt = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintTxt.setColor(Color.RED);
        paintTxt.setTextSize(30);

        paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLine.setColor(Color.BLUE);
        paintLine.setStrokeWidth(5);
        int[] colors = new int[]{shaderColor, 0x00FFFFFF};
        LinearGradient lg = new LinearGradient(1000, 100, 0, 100, colors, null, Shader.TileMode.CLAMP);
        paintLine.setShader(lg);

        mScroller = new Scroller(getContext());
        minFlingVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float textWidth = paintTxt.measureText("为人民服务", 0, 5);
        Rect rect = new Rect();
        paintTxt.getTextBounds("为人民服务", 0, 5, rect);
        rect.top += 40;
        rect.bottom += 40;
        paintTxt.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paintLine);
        canvas.drawText("为人民服务", getScrollX(), 40, paintTxt);

        canvas.drawLine(0, 0, 100, 100, paintLine);
        paintLine.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, 1000, 100, paintLine);

    }


    private float lastX = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = x;
                mDownY = y;
                lastX = x;
                if(!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }

                // 创建惯性滑动速度追踪类对象
                velocityTracker = VelocityTracker.obtain();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = mDownX - x;
                moveY = mDownY - y;

                int xDelta = (int) (x - lastX);
                lastX = x;
                if(xDelta > 0 && getScrollX() > 0) {
                    scrollBy(-xDelta, 0);
                } else if(xDelta < 0) {
                    scrollBy(-xDelta, 0);
                }
//                if(mScroller.isFinished()) {
//                    mScroller.startScroll(finalX, finalY, (int) moveX, 0, 0);
//                }

                // 将事件加入到VelocityTracker中
                velocityTracker.addMovement(event);
                // 计算1秒内滑动的像素个数
                velocityTracker.computeCurrentVelocity(1000);
                // 获取速度
                xVelocity = velocityTracker.getXVelocity();
                yVelocity = velocityTracker.getYVelocity();
                break;
            case MotionEvent.ACTION_UP:
                if(Math.abs(xVelocity) >= minFlingVelocity) {
                    mScroller.fling(getScrollX(), getScrollY(), (int) -xVelocity, 0, 0, 1000, 0, 0);
                } else {
                    mScroller.abortAnimation();
                    mScroller.fling(getScrollX(), getScrollY(), (int) -xVelocity, 0, 0, 1000, 0, 0);
                }

                finalX = mScroller.getFinalX();
                finalY = mScroller.getFinalY();

                velocityTracker.recycle();
                velocityTracker.clear();
                velocityTracker = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                finalX = mScroller.getFinalX();
                finalY = mScroller.getFinalY();

                velocityTracker.recycle();
                velocityTracker.clear();
                velocityTracker = null;
                break;
        }


        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed) {
            width = getWidth();
            height = getHeight();
        }
    }

    public ScrollerTestView(Context context) {
        this(context, null);
    }

    public ScrollerTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return false;
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()) {
            P.p("computeScrollOffset  ");
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
    }
}
