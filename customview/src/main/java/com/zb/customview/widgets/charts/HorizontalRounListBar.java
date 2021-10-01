package com.zb.customview.widgets.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 横向的文字 bar 数值 图表
 * 有显示最高5条和最低5条功能
 * DataSet为图表所接收的数据，或者文字list + double值 list
 * setData后会进行数值校验，排序。现只支持大于等于0的数值
 * 最高5条 最低5条的bar颜色需要自己设置 所有文字颜色 大小需要在类中硬编码
 * 暂未实现 自定义属性
 * 已知问题：value为0时，bar的左起始位置会向左移动
 * 实现高度自计算，宽度由view来控制
 * bar的宽度由最大数值 和 MAX_VALUE_LENGTH_PERCENTAGE控制，后者表示最大数值站可用bar宽度空间的百分比
 * 流程  设置数值-》requestLayout计算控件高度-》onDraw之前计算文字，bar的宽度-》onDraw
 */
public class HorizontalRounListBar extends View {

    private final int COLOR_TEXT = 0xFFFF0000;
    private final int COLOR_VALUE = 0xFFFF0000;
    private final int COLOR_CHART_LARGE = 0xFFFF0F00;//最大5条的图表默认颜色
    private final int COLOR_CHART_SMALL = 0xFFFF00F0;//最小5条的图表默认颜色



    private Paint mValuePaint, mChartPaint;//数值画笔与图表画笔
    private TextPaint mTextPaint; // 文字画笔
    private List<DataSet> data; //图表支持的数据

    private float mTextSize = sp2px(12);  //文字大小
    private float mValueSize = sp2px(8);  //数值文字大小
    private int mTextColor = COLOR_TEXT; //文字颜色
    private int mValueColor = COLOR_VALUE; //数值文字颜色
    private int mChartLargeColor = COLOR_CHART_LARGE;
    private int mChartSmallColor = COLOR_CHART_SMALL;

    //bar的粗细
    private float mChartSize = dp2px(8);

    private final String MAX_LEFT_TEXT = "一二三四五六";  //用于计算左侧文字宽度的占位文字
    private final String MAX_RIGHT_VALUE_TEXT = "99.99%"; //用于计算右侧数值文字宽度的占位文字
    //左侧文字的最大宽度
    private float mTextMaxWidth;
    //右侧数值文字的最大宽度
    private float mValueTextWidth;
    //图表最大宽度
    private float mMaxChartWidth;
    //图表和文字间的间距
    private float spcacing = dp2px(5);
    //每条数据之间的间距
    private float dataSapcing = dp2px(2);

    private float width, height;

    private double mMaxValue = 1d;
    //最大值数据的图表占图表最大宽度的90%
    private double MAX_VALUE_LENGTH_PERCENTAGE = 0.9d;

    //绘制最高，最低5条
    private boolean isDrawAll = false;



    public void setData(List<DataSet> data) {
        this.data = data;
        checkData(data);
        requestLayout();
        invalidate();
    }

    public void setData(List<String> names, List<Double> values) {
        setData(wrappedToDataSet(names, values));
    }
    private List<DataSet> wrappedToDataSet(List<String> names, List<Double> values) {
        if(null == names || null == values || 0 == names.size() || 0 == values.size()
        || names.size() != values.size())
            return null;
        List<DataSet> dataSets = new ArrayList<>();
        for(int i=0; i<names.size(); i++) {
            DataSet d = new DataSet(names.get(i), values.get(i));
            dataSets.add(d);
        }
        return dataSets;
    }

    /**
     * 设置数据  按数值大小从高到低排序
     * @param data
     */
    private void checkData(List<DataSet> data) {
        if(null == data || data.size() == 0)
            return;
        Collections.sort(data);
    }

    /**
     * 通过数值大小来获得数值文字
     * @param value
     * @return
     */
    private String getValueText(double value) {
        double percentageValue = value * 100;
        return String.format("%.1f", percentageValue) + "%";
    }

    /**
     * 是否展示所有数据
     * @param showAll
     */
    public void setShowAll(boolean showAll) {
        isDrawAll = showAll;
        setData(data);
    }

    public boolean getShowAll() {
        return isDrawAll;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(null == data || data.size() == 0)
            return;
        //初始化文字，图表所占宽度
        initValueLayout();
        canvas.save();

        if(isDrawAll || data.size() < 10) { //如果需要画全部数据或者数据条目小于10条
            for (DataSet element : data) {
                drawSingleElement(element, canvas, false);
            }
        } else {//只画最高 最低5条
            for(int i=0; i<5; i++) {
                drawSingleElement(data.get(i), canvas, true);
            }
            for(int i=data.size() - 1; i > data.size() - 6; i--) {
                drawSingleElement(data.get(i), canvas, false);
            }
        }

        canvas.restore();
    }

    /**
     * 画单条数据
     * @param data
     * @param canvas
     * @param isTop
     */
    private void drawSingleElement(DataSet data, Canvas canvas, boolean isTop) {
        if(isTop) {
            mChartPaint.setColor(COLOR_CHART_LARGE);
        } else {
            mChartPaint.setColor(COLOR_CHART_SMALL);
        }
        StaticLayout textLayout = new StaticLayout(data.name, mTextPaint, (int) (mTextMaxWidth + 0.5f),
                Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        float leftTextHeight = textLayout.getHeight();
        float barHeight = mChartSize;
        float mBarWidth = (float) (mMaxChartWidth * MAX_VALUE_LENGTH_PERCENTAGE * (data.value / mMaxValue + 0.01));
        float mLineHeight = Math.max(barHeight, leftTextHeight);
        float centerY = 0.5f * mLineHeight;
        Paint.FontMetricsInt valueFMI = mValuePaint.getFontMetricsInt();
        float valueBaseLine = (valueFMI.descent - valueFMI.ascent) * .5f - valueFMI.descent;
        textLayout.draw(canvas);
        canvas.drawLine(mTextMaxWidth + spcacing , centerY, mTextMaxWidth + mBarWidth, centerY, mChartPaint);
        canvas.drawText(getValueText(data.value), mTextMaxWidth + mBarWidth + spcacing * 2, centerY + valueBaseLine, mValuePaint);
        canvas.translate(0, mLineHeight + dataSapcing);
    }


    /**
     * 在画图前初始化文字，图表占位宽度 设置最大数据值
     */
    private void initValueLayout() {

        mTextMaxWidth = mTextPaint.measureText(MAX_LEFT_TEXT);
        mValueTextWidth = mValuePaint.measureText(MAX_RIGHT_VALUE_TEXT);
        mMaxChartWidth = width - 2f * spcacing - mTextMaxWidth - mValueTextWidth;

        if(null == data || 0 == data.size())
            return;

        //获取图表数据最大值
        for(DataSet element : data) {
            mMaxValue = Math.max(mMaxValue, element.value);
        }
        if(0 == mMaxValue)
            mMaxValue = 0.01d;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST) {
            int barCount = null == data? 0:data.size();
            //计算图表高度
            if(isDrawAll) {
                float heightSum = 0;
                if(barCount > 0) {
                    for(DataSet element : data) {
                        heightSum += getLineHeight(element);
                    }
                }
                setMeasuredDimension(getMeasuredWidth(), (int) heightSum);
            } else { //只画10个数据
                float heightSum = 0;
                if(barCount >= 10) {
                    for(int i=0; i<5; i++) {
                        heightSum += getLineHeight(data.get(i));
                    }
                    for(int i=data.size() - 1; i > data.size() - 6; i--) {
                        heightSum += getLineHeight(data.get(i));
                    }
                } else {
                    for(int i=0; i<data.size(); i++) {
                        heightSum += getLineHeight(data.get(i));
                    }
                }
                setMeasuredDimension(getMeasuredWidth(), (int) heightSum);
            }
        } else {
            //什么都不做 让super处理
        }
    }

    /**
     * 获取数据对应的每条数据应占高度
     * @param element
     * @return
     */
    private float getLineHeight(DataSet element) {
        StaticLayout textLayout = new StaticLayout(element.name, mTextPaint, (int) (mTextMaxWidth + 0.5f),
                Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        float leftTextHeight = textLayout.getHeight();
        float barHeight = mChartSize;
        float mLineHeight = Math.max(barHeight, leftTextHeight);
        return mLineHeight + dataSapcing;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed) {
            width = getWidth();
            height = getHeight();
        }
    }

    public HorizontalRounListBar(Context context) {
        this(context, null);
    }

    public HorizontalRounListBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalRounListBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);

        mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setTextSize(mValueSize);
        mValuePaint.setColor(mValueColor);

        mChartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mChartPaint.setColor(mChartLargeColor);
        mChartPaint.setStrokeWidth(mChartSize);
        mChartPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private float dp2px(float dp) {
        return getResources().getDisplayMetrics().density * dp;
    }
    private float sp2px(float sp) {
        return getResources().getDisplayMetrics().scaledDensity * sp;
    }




}
/**
 * 图表的数据bean
 */
final class DataSet implements Comparable {
    public int id;
    public String name;
    public double value;

    public DataSet() {}
    public DataSet(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public DataSet(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        return value > ((DataSet)o).value? -1:1;
    }
}