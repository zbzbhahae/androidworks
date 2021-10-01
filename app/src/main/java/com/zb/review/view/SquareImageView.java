package com.zb.review.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.zb.review.utils.P;

import org.jetbrains.annotations.NotNull;

public class SquareImageView extends AppCompatImageView {
    public SquareImageView(@NonNull @NotNull Context context) {
        super(context);
    }

    public SquareImageView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        //MeasureSpec.AT_MOST;
        //MeasureSpec.EXACTLY;
        //MeasureSpec.UNSPECIFIED;
        P.p("measured width height : " + measuredWidth + " x " + measuredHeight);
        measuredWidth = Math.min(measuredHeight, measuredWidth);
        measuredHeight = measuredWidth;
        P.p("measured width height : " + measuredWidth + " x " + measuredHeight);
//        measuredWidth = resolveSize(measuredWidth, widthMeasureSpec);
//        measuredHeight = resolveSize(measuredHeight, heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }


}
