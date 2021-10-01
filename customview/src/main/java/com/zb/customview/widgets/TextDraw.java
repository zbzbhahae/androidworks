package com.zb.customview.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.zb.common.utils.P;
import com.zb.common.utils.graphics.PaintUtil;
import com.zb.customview.R;

public class TextDraw extends BaseView {

    private Paint linePaint;
    private TextPaint txtPaint;
//    private String content = "为人民服务";
    private String mText;
    private float mTextSize;
    private int mTextColor;
    private int mInputType;

    public TextDraw(Context context) {
        super(context);
    }

    public TextDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        P.p("TextDraw::TextDraw()");
        if(null != attrs) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextDraw);
            mText = ta.getString(R.styleable.TextDraw_text);
            if(TextUtils.isEmpty(mText)) {
                mText = "空字符串";
//                TextView
            }
            mTextSize = ta.getDimensionPixelSize(R.styleable.TextDraw_textSize, sp2px(20));
            mTextColor = ta.getInt(R.styleable.TextDraw_textColor, 0xffff0000);
            mInputType = ta.getInt(R.styleable.TextDraw_inputType, 5);
            ta.recycle();
            P.p(" " + mText + "  size:" + mTextSize + "  color:" + mTextColor + " inputType:" + mInputType);
        }
        init();
    }


    protected void init() {
        initPaint();
    }

    private void initPaint() {
        txtPaint = new TextPaint();
        txtPaint.setAntiAlias(true);
        txtPaint.setColor(mTextColor);
        txtPaint.setTextSize(mTextSize);
        linePaint = new PaintUtil.Builder()
                .setAntiAlias(true)
                .setColor(Color.GREEN)
                .setStrokeWidth(dp2px(1))
                .setStyle(Paint.Style.STROKE)
                .build();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawText(canvas);
        drawRect(canvas);
        drawCrossLine(canvas);
    }

    private void drawCrossLine(Canvas canvas) {
        canvas.drawLine(0, height/2, width, height/2, linePaint);
        canvas.drawLine(width/2, 0, width / 2, height, linePaint);
    }

    private void drawRect(Canvas canvas) {
        Rect rect = new Rect();
        txtPaint.getTextBounds(mText, 0, mText.length(), rect);
        canvas.drawRect(rect, linePaint);
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fm = txtPaint.getFontMetrics();
        P.p( "FontMetricsInt: top=" + fm.top + " ascent=" + fm.ascent +
                " descent=" + fm.descent + " bottom=" + fm.bottom +
                " leading=" + fm.leading);
        int offsetY = (int) ( (fm.bottom - fm.top) /2  - fm.leading);
        int textWidth = (int) txtPaint.measureText(mText);
        StaticLayout sl = new StaticLayout(mText, txtPaint, getWidth() , Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        sl.draw(canvas);
//        canvas.drawText(mText, width / 2 - textWidth/2, height / 2 + offsetY/2, txtPaint);
//        canvas.translate(0, 100);
//        canvas.drawText(mText, 0, 0, txtPaint);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        P.p("TextDraw::onLayout()");
        if(changed) {
            width = getWidth();
            height = getHeight();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        String message = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                message = printTouchString(event, "ACTION_DOWN");
                return true;
            case MotionEvent.ACTION_MOVE:
                message = printTouchString(event, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                message = printTouchString(event, "ACTION_UP");
                message = "事件结束";
                break;
            case MotionEvent.ACTION_CANCEL:
                message = printTouchString(event, "ACTION_CANCEL");
                message = "事件结束";
                break;
        }
        mText = message;
        requestLayout();
        invalidate();


        return super.onTouchEvent(event);
    }

    private String  printTouchString(MotionEvent event, String message) {
        float x = event.getX();
        float y = event.getY();
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        String str =  "Touch->" + message + " \nx:" + x + " \ny:" + y + " \nrawX:" + rawX + " \nrawY:" + rawY;
        str =  "Touch->" + message + " x:" + x + " y:" + y + " rawX:" + rawX + " rawY:" + rawY;
        P.p(str);
        return str;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        P.p("TextDraw::onMeasure()");
//        super.onMeasure();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measureWidth = 0;
        int measureHeight = 0;

        //使用Paint测量文字是不会换行的
        Paint.FontMetricsInt fmi = txtPaint.getFontMetricsInt();
        int textWidth = (int) (txtPaint.measureText(mText) + 0.5f);
        int textHeight = fmi.bottom - fmi.top;

        if(textWidth > widthSize) { //文字长度超过给定范围 (需要换行)
            // 使用StaticLayout来计算 StaticLayout有换行功能
            StaticLayout sl = new StaticLayout(mText, txtPaint,
                    widthSize , Layout.Alignment.ALIGN_NORMAL, 1.0F,
                    0.0F, true);
            textWidth = sl.getWidth();
            textHeight = sl.getHeight();
        }

        if(widthMode == MeasureSpec.AT_MOST) {
//            measureWidth = textWidth + getPaddingLeft() + getPaddingRight();
            measureWidth = textWidth;
        } else if(widthMode == MeasureSpec.EXACTLY){
            measureWidth = widthSize;
        } else {
//            int minWidth = textWidth+ getPaddingLeft() + getPaddingRight();
            int minWidth = textWidth;
            measureWidth = Math.max(minWidth, widthSize);
        }

        if(heightMode == MeasureSpec.AT_MOST) {
//            measureHeight = textHeight + getPaddingTop() + getPaddingBottom();
            measureHeight = textHeight;
        } else if(heightMode == MeasureSpec.EXACTLY){
            measureHeight = heightSize;
        } else {
//            measureHeight = Math.max(textHeight + getPaddingTop() + getPaddingBottom(), heightSize);
            measureHeight = Math.min(textHeight, heightSize);
            measureHeight = Math.max(0, measureHeight);
        }
        setMeasuredDimension(measureWidth, measureHeight);
    }
}
