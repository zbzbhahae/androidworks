package com.zb.customview.widgets.myproj;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import androidx.annotation.Nullable;


import com.zb.common.utils.P;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShaderLineView extends View {

    private int TEXT_COLOR = 0xFFCCCCCC;    // xy轴文字颜色
    private int XY_LINE_COLOR = 0xFFD7D7D7; // xy轴颜色
    private int XY_TEXT_COLOR = 0xFF989898; // xy轴文字颜色
    private int LINE_COLOR = 0xFFBFE0FF; // 折线颜色
    private int BACKGROUND_LINE_COLOR = 0xFFF5F5F5; // 背景辅助线颜色



    private float XY_LINE_WIDTH = dp2px(1);
    private float XY_TEXT_SIZE = sp2px(10);
    private float CHART_TOP_SPACING = dp2px(20); // 顶部到图表的距离 预留
    private float Y_TEXT_SPCAING = dp2px(2); // y轴文字到y轴距离

    private float X_TEXT_SPACING = dp2px(30); // x坐标间距

    private int width, height;
    private PointF oriPoint; // 原点坐标 左下角 0, height 右上固定的位置，可能会滑动到看不到
    private float mScrollX;  // x轴的滑动距离 每次ondraw之前重新获取
    private Paint textPaint, valueTextPaint, linePaint, chartPaint, shaderPaint;

    private List<String> chartLabels; // 横坐标文字
    private List<Double> data1; // 数据内容

    private boolean drawValueText = true; // 是否画数值文字
    private double mMaxValue = 100;
    private double mMinValue = 0;
    private String Y_TEXT_STUB = "0.00%"; // Y轴文字宽度占位
    private float mTextWidth = 0; // Y轴文字宽度

    private DecimalFormat df = new DecimalFormat("#.##");


    // 滑动相关的东西
    private float xVelocity = 0;
    private Scroller mScroller;
    private VelocityTracker velocityTracker;
    // fling的最小速率
    private int minFlingVelocity;
    private int maxFlingVelocity;


    private void init(Context context) {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(XY_TEXT_COLOR);
        textPaint.setTextSize(XY_TEXT_SIZE);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(XY_LINE_COLOR);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(XY_LINE_WIDTH);

        chartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        chartPaint.setColor(LINE_COLOR);
        chartPaint.setStyle(Paint.Style.STROKE);
        chartPaint.setStrokeWidth(XY_LINE_WIDTH);

        valueTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        valueTextPaint.setColor(XY_TEXT_COLOR);
        valueTextPaint.setTextSize(XY_TEXT_SIZE);

        mScroller = new Scroller(context);
        minFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        maxFlingVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();

        initData();

    }

    /**
     * 制造假数据
     */
    private void initData() {
        chartLabels = new ArrayList<>();
        for(int i=0; i<50; i++) {
            chartLabels.add("abc" + i);
        }
        data1 = new ArrayList<>();
        for(int i=0; i<50; i++) {
            data1.add(new Random().nextDouble());
        }
    }

    private void initLayout() {
        width = getWidth();
        height = getHeight();
        mScrollX = getScrollX();
        Paint.FontMetricsInt fmt = textPaint.getFontMetricsInt();
        mTextWidth = fmt.bottom - fmt.top; // 获得x轴文字高度
        float yTextWidth = textPaint.measureText(Y_TEXT_STUB); // 获得y轴文字宽度
        if(oriPoint == null) {
            oriPoint = new PointF();
        }
        oriPoint.x = yTextWidth + Y_TEXT_SPCAING;
        oriPoint.y = height - mTextWidth;

        mMaxValue = getMaxHeight(data1);
    }

    /**
     * 暂时最小数据按0处理 不考虑负值
     * @param data
     * @return
     */
    private double getMaxHeight(List<Double> data) {
        if(null == data)
            return 100;
        double maxValue = 0;
        for(Double d : data) {
            maxValue = Math.max(d, maxValue);
        }
        // 获得最大值后 需要对最大值进行细分，让y轴数据更合理
        maxValue = getSuitableValue(maxValue);
        P.p("max value is " + maxValue);
        return maxValue;
    }

    /**
     * 对数值进行细分，人民币面值式的细分，有想法
     * @param value
     * @return
     */
    private double getSuitableValue(double value) {
        if(value < 0)
            return 0;
        int i = 1;
        while(true) {
            if(value < 0.005 * i * 10) {
                return 0.005 * i * 10;
            } else if(value < 0.01 * i * 10) {
                return 0.01 * i * 10;
            } else if(value < 0.02 * i * 10) {
                return 0.02 * i * 10;
            }
            i++;
            if(i > 12) {
                // 数据有点大 撑不住了 有问题
                Log.e("view", "ShaderLineView has huge value");
                return value;
            }
        }
    }

    /**
     * 通过数值大小来获得数值文字
     * @param value
     * @return
     */
    private String getValueText(double value) {
//        double percentageValue = value * 100;
        return df.format(value);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initLayout(); //TODO 初始化数据应该在设置数据中进行
        drawXYLine(canvas);
        drawXYText(canvas);
        drawLines(canvas);
    }



    private void drawXYLine(Canvas canvas) {
        P.p("mScrollX: " + mScrollX);
        canvas.drawLine(oriPoint.x + mScrollX, oriPoint.y, oriPoint.x + width + mScrollX, oriPoint.y, linePaint);
        // y轴比较特殊 需要根据scrollx来确定x
        canvas.drawLine(oriPoint.x + mScrollX, CHART_TOP_SPACING, oriPoint.x + mScrollX, oriPoint.y, linePaint);
    }

    private void drawXYText(Canvas canvas) {
        Paint.FontMetricsInt fmi = textPaint.getFontMetricsInt();
        float baseLine = (fmi.descent - fmi.ascent) * 0.5f - fmi.descent;
        float y = (oriPoint.y + height) * 0.5f + baseLine;
        for(int i=0; i<chartLabels.size(); i++) {
            String content = chartLabels.get(i);
            float textWidth = textPaint.measureText(content);
            float x = X_TEXT_SPACING * i + 0.5f * X_TEXT_SPACING + oriPoint.x - textWidth * 0.5f;

            canvas.drawText(content, x, y, textPaint);
        }

        // 画y轴文字，最底下不用画
        for(int i=1; i<=5; i++) {
            String content = getValueText(mMinValue + (mMaxValue - mMinValue) * 0.2f * i);
            float textWidth = textPaint.measureText(content);
            float x = oriPoint.x + mScrollX - textWidth - Y_TEXT_SPCAING;
            y = oriPoint.y - (oriPoint.y - CHART_TOP_SPACING) * 0.2f * i; // 要计算顶部预留
            y += baseLine;
            canvas.drawText(content, x, y, textPaint);

            // 画辅助线
            canvas.drawLine(oriPoint.x + mScrollX, y, oriPoint.x + width + mScrollX,  y, linePaint);
        }
    }

    /**
     * 根据数据画折线和shader
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        if(null == data1 || 0 == data1.size()) {
            return;
        }
        // 裁剪画布 防止滑动后 在y轴左侧绘制图表
        canvas.save();
        canvas.clipRect(oriPoint.x + mScrollX, 0, oriPoint.x + width + mScrollX, oriPoint.y);

        boolean isFirstValue = true;
        float lineMaxHeight = oriPoint.y - CHART_TOP_SPACING;
        Path linePath = new Path();
        Path shaderPath = new Path();
        float maxValueY = Float.MAX_VALUE;
        for (int i=0; i<data1.size(); i++) {
            double value = data1.get(i);
            float x = X_TEXT_SPACING * i + 0.5f * X_TEXT_SPACING + oriPoint.x;
            float y = (float) (oriPoint.y - value / mMaxValue * lineMaxHeight);
            if(isFirstValue) {
                isFirstValue = false;
                linePath.moveTo(x, y);
                P.p("move to " + x + "  " + y);
                shaderPath.moveTo(x, oriPoint.y);
                maxValueY = y;
            } else {
                linePath.lineTo(x, y);
                P.p(i + " line to " + x + "  " + y);
            }
            shaderPath.lineTo(x, y);
            // 画实心或者空心 TODO 空心时 会被最后画的线覆盖 待改进
            canvas.drawCircle(x, y, 5, chartPaint);
            maxValueY = Math.min(maxValueY, y);
            if(i == data1.size() - 1) { // 如果是最后一个数据 则关闭shader 的path
                shaderPath.lineTo(x, oriPoint.y);
                shaderPath.close();
            }

            if(drawValueText) {
                // 如果需要画数值文字
                String valueText = getValueText(value);
                float textWidth = valueTextPaint.measureText(valueText);
                // 偷个懒
                canvas.drawText(valueText, x - 0.5f * textWidth, y - valueTextPaint.getTextSize(), valueTextPaint);
            }
        }
        canvas.drawPath(linePath, chartPaint);
        int[] colors = new int[]{chartPaint.getColor() & 0x00FFFFFF, chartPaint.getColor()};
        LinearGradient lg = new LinearGradient(oriPoint.x, oriPoint.y, oriPoint.x, maxValueY, colors, null, Shader.TileMode.CLAMP);
        chartPaint.setShader(lg);
        chartPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(shaderPath, chartPaint);
        chartPaint.setShader(null);
        chartPaint.setStyle(Paint.Style.STROKE);

        canvas.restore();

    }

    private float lastX = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                if(!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }

                // 创建惯性滑动速度追踪类对象
                velocityTracker = VelocityTracker.obtain();
                break;
            case MotionEvent.ACTION_MOVE:
                int xDelta = (int) (x - lastX);
                lastX = x;
                P.p("xDelta : " + xDelta);
                if(xDelta > 0 && getScrollX() > 0) {
                    scrollBy(-xDelta, 0);
                } else if(xDelta < 0 && getScrollX() < getMaxScrollX()) { // 手指从右向左滑 检查是否到底了
                    scrollBy(-xDelta, 0);
                }

                // 将事件加入到VelocityTracker中
                velocityTracker.addMovement(event);
                // 计算1秒内滑动的像素个数
                velocityTracker.computeCurrentVelocity(1000, maxFlingVelocity);
                // 获取速度
                xVelocity = velocityTracker.getXVelocity();
                break;
            case MotionEvent.ACTION_UP:
                if(Math.abs(xVelocity) >= minFlingVelocity) {
                    mScroller.abortAnimation();
                    mScroller.fling(getScrollX(), getScrollY(), (int) -xVelocity, 0, 0, getMaxScrollX(), 0, 0);
                } else {
//                    mScroller.abortAnimation();
//                    mScroller.fling(getScrollX(), getScrollY(), (int) -xVelocity, 0, 0, 1000, 0, 0);
                }


                velocityTracker.recycle();
                velocityTracker.clear();
                velocityTracker = null;
                break;
            case MotionEvent.ACTION_CANCEL:

                velocityTracker.recycle();
                velocityTracker.clear();
                velocityTracker = null;
                break;
        }
        return true;
    }

    /**
     * 获得x方向可以滑动的最大值  即横坐标最后一个的位置加上半个横坐标间距 减去 展示出来的宽度
     * @return
     */
    private int getMaxScrollX() {
        if(null != chartLabels && chartLabels.size() > 0) {
            int lastLabelX = (int) (X_TEXT_SPACING * chartLabels.size() + 0.5f * X_TEXT_SPACING + oriPoint.x);
            int endOfChartX = (int) (lastLabelX + 0.5f * X_TEXT_SPACING);
            // 计算能看到图表最后的样子时 已经滑动过的距离
            int scrolledX = (int) (endOfChartX - width + mTextWidth);
            if(scrolledX > 0) {
                return scrolledX;
            }
            return 0;
        } else {
            return 0;
        }
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX() <= 0? 0 : mScroller.getCurrX(), 0);
        }
    }

    public ShaderLineView(Context context) {
        this(context, null);
    }

    public ShaderLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private float dp2px(float dp) {
        return getContext().getResources().getDisplayMetrics().density * dp;
    }

    private float sp2px(float sp) {
        return getContext().getResources().getDisplayMetrics().scaledDensity * sp;
    }
}
