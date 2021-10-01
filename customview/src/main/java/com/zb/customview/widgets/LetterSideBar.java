package com.zb.customview.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.zb.customview.R;

public class LetterSideBar extends View {

    private Paint mPaint;
    private int color, selectedColor;
    private float textSize, spacing;
    private String mChoosing = "Z";
    private OnLetterSelectedListener listener;
    private int width, height;

    private String[] LETTERS = new String[] {"#", "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public interface OnLetterSelectedListener {
        //空表示取消选中
        void onSelected(String letter);
    }

    public void setOnLetterSelectedListener(OnLetterSelectedListener listener) {
        this.listener = listener;
    }

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(null != attrs) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LetterSideBar);
            color = ta.getColor(R.styleable.LetterSideBar_LetterSideBar_textColor, Color.BLACK);
            selectedColor = ta.getColor(R.styleable.LetterSideBar_LetterSideBar_textSelectedColor, Color.RED);
            textSize = ta.getDimensionPixelSize(R.styleable.LetterSideBar_LetterSideBar_textSize, sp2px(12));
            spacing = ta.getDimensionPixelSize(R.styleable.LetterSideBar_LetterSideBar_spacing, dp2px(5));
            ta.recycle();
        }
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawText(canvas);
        drawSelectedText(canvas, mChoosing);
    }

    private void drawText(Canvas canvas) {
        mPaint.setColor(color);
        for (int i=0; i<LETTERS.length; i++) {
            drawLetterAt(canvas, i, LETTERS[i]);
        }
    }

    private void drawSelectedText(Canvas canvas, String selected) {
        if(TextUtils.isEmpty(selected))
            return;
        mPaint.setColor(selectedColor);
        int position = -1;
        for(int i=0; i<LETTERS.length; i++) {
            if(selected.equals(LETTERS[i])) {
                position = i;
                break;
            }
        }
        if(position < 0 || position >= LETTERS.length)
            return;
        drawLetterAt(canvas, position, selected);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                if(isTouchInsideView(x, y)) {
                    //触摸在控件内
                    int position = caculatePosition(y);
                    if(position >= 0 && position < LETTERS.length) {
                        //合规位置
                        String letter = LETTERS[position];
                        if(!letter.equals(mChoosing)) { //与选中的不符 去刷新控件
                            mChoosing = letter;
                            performListener(mChoosing);
                            invalidate();
                        }
                    } else {
                        //不合规位置
                        if(null != mChoosing) {
                            mChoosing = null;
                            performListener(mChoosing);
                            invalidate();
                        }
                    }
                } else if(null != mChoosing) { //点击事件不在view内部
                    mChoosing = null;
                    performListener(mChoosing);
                    invalidate();//触摸在view之外 取消选中
                }
                return true;
            default:
                if(mChoosing != null) {
                    mChoosing = null;
                    performListener(mChoosing);
                    invalidate();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void performListener(String letter) {
        if(null != listener)
            listener.onSelected(letter);
    }

    private boolean isTouchInsideView(float x, float y) {
        //左右可以适当判断在控件内
        if(x >= 0 && x <= width && y >= getPaddingTop() && y < height)
            return true;
        return false;
    }

    /**
     * 计算触摸的位置
     * @param y
     * @return
     */
    private int caculatePosition(float y) {
        float heightWithOutPadding = height - getPaddingTop() - getPaddingBottom();
        float eachElementHeight = heightWithOutPadding / LETTERS.length;
        y -= getPaddingTop();
        int position = (int) (y / eachElementHeight);
        return position;
    }

    private void drawLetterAt(Canvas canvas, int position, String letter) {

        float heightForEach = ((height * 1f - getPaddingTop() - getPaddingBottom())
                - (LETTERS.length - 1) * spacing) / LETTERS.length;
        float spacingInUp = spacing * (position - 1);
        if(spacingInUp < 0)
            spacingInUp = 0;
        float currentTop = getPaddingTop() + (heightForEach * position) + spacingInUp;
        float currentBottom = currentTop + heightForEach;
        Paint.FontMetrics fmi = mPaint.getFontMetrics();
        float x = (width - getPaddingLeft() - getPaddingRight() - mPaint.measureText(letter)) / 2f + getPaddingLeft();
        float baseLine = (fmi.descent + Math.abs(fmi.ascent)) / 2f - fmi.descent;
        float y = (currentBottom + currentTop) / 2f + baseLine;
        canvas.drawText(letter, 0, 1, x, y, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed) {
            width = getWidth();
            height = getHeight();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int textWidth = (int) (getPaddingLeft() + getPaddingRight() + mPaint.measureText("A"));
        Rect textBounds = new Rect();
        mPaint.getTextBounds("A", 0, 1, textBounds);
        int singleTextHeight = textBounds.height();
        int totalHeight = (int) (27f * singleTextHeight + 26f * spacing) + getPaddingBottom() + getPaddingTop();//26个字母+1个#

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(widthMeasureSpec);
        int realWidth, realHeight;
        if(widthMode == MeasureSpec.EXACTLY) {
            realWidth = specWidth;
        } else {
            realWidth = textWidth;
        }
        if(heightMode == MeasureSpec.EXACTLY) {
            realHeight = specHeight;
        } else {
            realHeight = totalHeight;
        }
        setMeasuredDimension(realWidth, realHeight);
    }

    protected int dp2px(int dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp + 0.5);
    }
    protected int sp2px(int sp) {
        return (int) (getContext().getResources().getDisplayMetrics().scaledDensity * sp + 0.5);
    }
}
