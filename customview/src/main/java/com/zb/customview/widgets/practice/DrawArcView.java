package com.zb.customview.widgets.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawArcView extends View {

    private Paint paint;


    public DrawArcView(Context context) {
        this(context, null);
    }

    public DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(new RectF(0, 0, dp2px(100), dp2px(100)),
                0, 90, false, paint);
    }

    private float dp2px(float dp) {
        return getResources().getDisplayMetrics().density * dp;
    }
}
