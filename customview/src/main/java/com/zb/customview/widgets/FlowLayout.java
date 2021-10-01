package com.zb.customview.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.zb.common.utils.P;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {

    private int mHorizontalSpacing = 10;//子控件横向之间间距
    private int mVericalSpacing = 10;

    private List<List<View>> allViews = new ArrayList<>();


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        P.p(this.getClass().getSimpleName() + "::onMeasure()");

        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        List<View> lineViews = new ArrayList<>();
        int lineWidthUsed = 0;//记录一行中已经使用的宽度
        int lineHeight = 0;//每行高度

        int selftWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        int selfWidthNeeded = 0;
        int selfHeightNeeded = 0;


        //进入测量方法需要重置 防止多次添加
        allViews.clear();
        if(childCount > 0) {
            for(int i=0; i<childCount; i++) {
                //测量子View
                View child = getChildAt(i);
                LayoutParams childP = child.getLayoutParams();
                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, (paddingLeft + paddingRight), childP.width);
                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, (paddingBottom+paddingTop), childP.height);
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

                int childMeasuredWidth = child.getMeasuredWidth();
                int childMeasuredheight = child.getMeasuredHeight();

                //根据子View的宽高来计算自己的宽高
                if(selftWidth < (lineWidthUsed + childMeasuredWidth + mHorizontalSpacing)) {
                    allViews.add(lineViews);
                    lineViews = new ArrayList<>();

                    selfWidthNeeded = Math.max(selfWidthNeeded, lineWidthUsed);
                    selfHeightNeeded += lineHeight + mVericalSpacing;

                    lineHeight = 0;
                    lineWidthUsed = 0;
                }
                lineViews.add(child);
                lineWidthUsed += childMeasuredWidth + mHorizontalSpacing;
                lineHeight = Math.max(lineHeight, childMeasuredheight);
            }

            allViews.add(lineViews);

            selfWidthNeeded = Math.max(selfWidthNeeded, lineWidthUsed);
            selfHeightNeeded += lineHeight + mVericalSpacing;
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //如果测量给定了确定值 必须使用确定值
        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selftWidth : selfWidthNeeded;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : selfHeightNeeded;

        setMeasuredDimension(realWidth, realHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        P.p(this.getClass().getSimpleName() + "::onDraw()");
        super.onDraw(canvas);
    }


    @Override
    protected void onLayout(boolean changed, int pleft, int ptop, int pright, int pbottom) {
        P.p(this.getClass().getSimpleName() + "::onLayout()");
        int currentWidth = 0;
        int currentHeight = getPaddingTop();
        int currentLineMaxHeight = 0;
        int count = allViews.size();
        for(int i=0; i<count;i++) {
            List<View> lineViews = allViews.get(i);
            currentWidth = getPaddingLeft();
            for(int j=0; j<lineViews.size();j++) {
                View child = lineViews.get(j);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();

                int left = 0;
                int right = 0;
                int top = 0;
                int bottom = 0;

                if(currentWidth > getPaddingLeft()) {
                    left = currentWidth + mHorizontalSpacing;
                } else {
                    left = currentWidth;
                }
                right = left + childWidth;
                top = currentHeight;
                bottom = top + childHeight;



                child.layout(left, top, right, bottom);

                currentLineMaxHeight = Math.max(currentLineMaxHeight, childHeight);
                currentWidth = right;
            }

            currentHeight += currentLineMaxHeight + mVericalSpacing;
        }
    }


    /**
     * new view时调用
     * @param context
     */
    public FlowLayout(Context context) {
        this(context, null);

    }

    /**
     * XML转java的时候
     * @param context
     * @param attrs
     */
    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 主题style
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
