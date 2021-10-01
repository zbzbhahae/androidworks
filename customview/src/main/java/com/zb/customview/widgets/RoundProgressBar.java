package com.zb.customview.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.zb.common.utils.P;
import com.zb.customview.R;

import java.text.DecimalFormat;

public class RoundProgressBar extends BaseView {


    private int outColor;
    private int innerColor;
    private float borderWidth;
    private int textSize;
    private int textColor;

    private final int DEFAULT_TEXT_COLOR = 0xFF000000;
    private final int DEFAULT_OUT_COLOR = 0xFFFF0000;
    private final int DEFAULT_INNER_COLOR = 0xFF00FF00;
    private final int DEFAULT_TEXT_SIZE = sp2px(14);
    private final int DEFAULT_BORDER_WIDTH = dp2px(10);


    private Paint linePaint;
    private TextPaint textPaint;

    //0-1
    private float mProgress = 0;
    private String mText = "";
    private int count;
    private long timeUsed;
    private long startT, stopT;
    private boolean isDrawedOut = false;

    DecimalFormat df = new DecimalFormat("#.0%");

    public void setPercent(float percent) {
        this.mProgress = percent;
        invalidate();
    }



    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(null != attrs) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
            textColor = ta.getColor(R.styleable.RoundProgressBar_RoundProgressBar_textColor, DEFAULT_TEXT_COLOR);
            outColor = ta.getColor(R.styleable.RoundProgressBar_RoundProgressBar_outColor, DEFAULT_OUT_COLOR);
            innerColor = ta.getColor(R.styleable.RoundProgressBar_RoundProgressBar_innerColor, DEFAULT_INNER_COLOR);
            borderWidth = ta.getDimensionPixelSize(R.styleable.RoundProgressBar_RoundProgressBar_borderWidth,
                    DEFAULT_BORDER_WIDTH);
            textSize = ta.getDimensionPixelSize(R.styleable.RoundProgressBar_RoundProgressBar_textSize,
                    DEFAULT_TEXT_SIZE);
            mProgress = ta.getFloat(R.styleable.RoundProgressBar_RoundProgressBar_percent, 0.01f);
            ta.recycle();
        }
        init();
    }


    private void init() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(borderWidth);
        linePaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        count++;
        startT = System.nanoTime();
        drawOut(canvas);
        drawInner(canvas);
        drawText(canvas);
        stopT = System.nanoTime();
        timeUsed = stopT - startT;
    }

    public void printTime() {
        P.p("count:" + count + "   time:" + timeUsed);
    }

    private void drawOut(Canvas canvas) {
        linePaint.setColor(DEFAULT_OUT_COLOR);
        linePaint.setStrokeWidth(borderWidth);
        RectF rectF = new RectF(borderWidth/2, borderWidth/2,
                getWidth() - borderWidth/2, getHeight() - borderWidth/2);
        //画圆弧 从45度扫过270个度， 是否连接圆心 false rectF为圆的外框 需要考虑画笔的宽度
        canvas.drawArc(rectF, 135, 270, false, linePaint);
    }

    private void drawInner(Canvas canvas) {
        linePaint.setColor(DEFAULT_INNER_COLOR);
        linePaint.setStrokeWidth(borderWidth + 1);
        RectF rectF = new RectF(borderWidth/2, borderWidth/2,
                getWidth() - borderWidth/2, getHeight() - borderWidth/2);
        if(mProgress < 0.01) {
            mProgress = 0.01f;
        }
        if(mProgress > 1) {
            mProgress = 1;
        }
        float sweepAngle = 270 * mProgress;

        //画圆弧 从45度扫过270个度， 是否连接圆心 false rectF为圆的外框 需要考虑画笔的宽度
        canvas.drawArc(rectF, 135, sweepAngle, false, linePaint);
    }

    private RectF rect = new RectF();
    private void drawText(Canvas canvas) {
        textPaint.setColor(innerColor);
        mText = df.format(mProgress);
        if(TextUtils.isEmpty(mText))
            return;
        float textWidth = textPaint.measureText(mText);
        Paint.FontMetricsInt pfm = textPaint.getFontMetricsInt();
        float baseLine = (pfm.descent + Math.abs(pfm.ascent))  / 2
                - pfm.descent;


        float cachedright;
        canvas.save();
        rect.left =  ((getWidth() - textWidth) / 2);
        rect.top = getHeight() / 2 - (pfm.top - pfm.bottom) / 2;
        rect.right =  (rect.left + textWidth);
        rect.bottom = rect.top + (pfm.top - pfm.bottom);

        cachedright = rect.right;
        rect.right =  rect.right - rect.width() * (1-mProgress);

        canvas.clipRect(rect);

        canvas.drawText(mText, (getWidth() - textWidth) / 2, (getHeight()/2 + baseLine), textPaint);
        canvas.restore();

        textPaint.setColor(outColor);
        canvas.save();
        rect.right = cachedright;
        rect.left =  ((rect.right - rect.left) * mProgress + rect.left);

        canvas.clipRect(rect);

//        canvas.drawText(mText, 0, (rect.top - rect.bottom) /2 + baseLine, textPaint);
//        canvas.drawText(mText, 0, 0, textPaint);

        canvas.drawText(mText, (getWidth() - textWidth) / 2, (getHeight()/2 + baseLine), textPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measureWidth = 0;
        int measureHeight = 0;
        //取长宽最小值 变成正方形
        setMeasuredDimension(widthSize > heightSize? heightSize:widthSize,
                widthSize > heightSize? heightSize:widthSize);
    }
}
