package com.zb.customview.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.zb.common.utils.P;

public class ChangingProgressBar extends BaseView {

    private final int COLORS[] = new int[] { Color.RED, Color.GREEN, Color.BLUE };

    private final int SHAPE_CIR = 0;
    private final int SHAPE_SQU = 1;
    private final int SHAPE_TRI = 2;

    private int mCurrentShape = 0;

    private Paint mPaint;


    private long degreeCount = 0;
    @Override
    protected void onDraw(Canvas canvas) {

        long currentT = System.currentTimeMillis();
        int args = (int) ((currentT / 1000)%3);
//        mPaint.setColor(COLORS[args]);
        canvas.rotate(degreeCount, width/2, height/2);
        degreeCount++;
        switch (args) {
            case SHAPE_CIR:
                drawCricle(canvas);
                break;
            case SHAPE_SQU:
                drawSquare(canvas);
                break;
            case SHAPE_TRI:
                drawTriangle(canvas);
                break;
        }
//        drawCricle(canvas);
//        drawSquare(canvas);
        invalidate();
//        drawTriangle(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed) {
            width = getWidth();
            height = getHeight();
            rectF.left = 0;
            rectF.right = width;
            rectF.top = 0;
            rectF.bottom = height;


            float squareLength = (float) (rectF.width() * 0.5 * Math.cos(Math.toRadians(45)) * 2);
            squareRect.left = (float) (width * 0.5 - squareLength * 0.5);
            squareRect.top = (float) (height * 0.5 - squareLength * 0.5);
            squareRect.right = squareRect.left + squareLength;
            squareRect.bottom = squareRect.top + squareLength;

            p1 = new PointF(width/2, 0);
            float y = (float) (Math.cos(Math.toRadians(60)) * (height / 2) + height / 2);
            float xDiff = (float) (Math.sin(Math.toRadians(60)) * (width / 2));

            p2 = new PointF();
            p2.x = width/2 - xDiff;
            p2.y = y;
            p3 = new PointF();
            p3.x = width/2 + xDiff;
            p3.y = y;

            path.reset();
            path.moveTo(p1.x, p1.y);
            path.lineTo(p2.x, p2.y);
            path.lineTo(p3.x, p3.y);
            path.close();
            P.p(Math.cos(Math.toRadians(60)) + "");
            P.p(p1.toString());
            P.p(p2.toString());
            P.p(p3.toString());
        }
    }

    private RectF rectF = new RectF();
    private void drawCricle(Canvas canvas) {
        mPaint.setColor(COLORS[SHAPE_CIR]);
        canvas.drawArc(rectF, 0, 360, true, mPaint);
    }
    private RectF squareRect = new RectF();
    private void drawSquare(Canvas canvas) {
        mPaint.setColor(COLORS[SHAPE_SQU]);
        canvas.drawRect(squareRect, mPaint);
    }
    private PointF p1, p2, p3;
    Path path = new Path();
    private void drawTriangle(Canvas canvas) {
        mPaint.setColor(COLORS[SHAPE_TRI]);
        canvas.drawPath(path, mPaint);
    }

    public ChangingProgressBar(Context context) {
        super(context);
    }
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(COLORS[mCurrentShape]);
    }

    public ChangingProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
}
