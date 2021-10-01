package com.zb.customview.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class FPSView extends View {
    public FPSView(Context context) {
        this(context, null);
    }

    public FPSView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FPSView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private long count;
    private long countInSecond;
    private TextPaint mPaint;
    private long cacheStartTime;

    private void init() {
        mPaint = new TextPaint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.RED);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = caculateString();
        drawText(canvas, text);
        invalidate();
    }

    private void drawText(Canvas canvas, String text) {
        int width = getWidth();
        int height = getHeight();
        Paint.FontMetricsInt fmi = mPaint.getFontMetricsInt();
        float baseline = (fmi.descent + Math.abs(fmi.ascent)) / 2 - fmi.descent;
        float textWidth = mPaint.measureText(text);
        canvas.drawText(text, width/2 - textWidth/2, height/2 + baseline, mPaint);
    }

    private String caculateString() {
        long currentTime = System.nanoTime();
        if(currentTime - cacheStartTime > 1000000000L) {
            cacheStartTime = currentTime;
            countInSecond = count;
            count = 0;

        }
        String countStr = countInSecond + "FPS";
        count ++;
        return countStr;
    }
}
