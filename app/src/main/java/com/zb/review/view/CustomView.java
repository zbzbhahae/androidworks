package com.zb.review.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;


/**
 * https://rengwuxian.com/ui-1-2/
 */
public class CustomView extends View {


    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);

        int w = getWidth();
        int h = getHeight();

        drawRect(0, 0, w, h, c);
        drawCircle(w/2, h/2, w/4, c);
        drawCross(w/2, h/2, w/2, c);


        drawUseShaderCircle(c);
        drawText("一二三四", 0, 20, c);


    }

    /**
     * 画黑色背景
     * @param x
     * @param y
     * @param w
     * @param h
     * @param c
     */
    void drawRect(float x, float y, float w, float h, Canvas c) {
        Paint p = new Paint();
        p.setFlags(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);
        c.drawRect(x, y, x + w, y + h, p);
    }

    /**
     * 画一个圆
     * @param x
     * @param y
     * @param r
     * @param c
     */
    void drawCircle(float x, float y, float r, Canvas c) {
        Paint p = new Paint();
        p.setFlags(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        p.setStrokeWidth(5);
        c.drawCircle(x, y, r, p);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        c.drawCircle(x , y, r, p);
    }

    /**
     * 画十字线
     * @param x
     * @param y
     * @param length
     * @param c
     */
    void drawCross(float x, float y, float length, Canvas c) {
        float l = length - 5f;
        Paint p = new Paint();
        p.setFlags(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        p.setStrokeWidth(3);
        p.setColor(Color.GREEN);
        p.setStyle(Paint.Style.STROKE);
        c.drawLine(x, y - 0.5f * l, x, y + 0.5f * l, p);
        c.drawLine(x - 0.5f * l, y, x + 0.5f * l, y, p);
    }

    void drawText(String text, float x, float y, Canvas c) {
        Paint p = new Paint();
        p.setTextSize(28);
        p.setFlags(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        p.setColor(Color.GREEN);
        p.setStyle(Paint.Style.STROKE);
        c.drawText(text, x, y, p);
    }

    void drawUseShaderCircle(Canvas c) {
        Paint p = new Paint();
        p.setFlags(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        Shader shader = new LinearGradient(0, 0, 200, 200, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        p.setShader(shader);
        c.drawCircle(100, 100, 100, p);
    }






    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
