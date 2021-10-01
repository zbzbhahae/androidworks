package com.zb.customview.widgets.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpiderChartView extends View {

    private static final int TEXT_COLOR = 0xFFFF0000;
    private static final int VALUE_COLOR = 0xFFFF0FF0;//默认数值的颜色
    private int START_DEGREE = -90;


    private int width, height;//控件宽高
    private List<String> indexLabelSet; //坐标轴的属性文字
    private List<List<Double>> dataSet;//对应的多个数据集
    private float centerX, centerY; //图表中心点位置，用于作图
    private float maxChartLength;  //图表的半径，即坐标轴最远长度
    private int deep = 5; //蜘蛛图的网状层级，3层指把一个方向的距离分成3份
    private int parts = 3;//蛛网图的层级 最少3层
    private float degreePerPart;//每一个扇形的角度
    private List<PointF> vertexes;//顶点


    private float mTextSize = sp2px(8); //坐标轴文字大小
    private float mValueSize = sp2px(6);  //图表值大小
    private int mTextColor = TEXT_COLOR;
    private int mValueColor = VALUE_COLOR;
    private List<Integer> mValueColorSet; //数据集合的颜色集合,基本每种数据对应一种颜色
    private Paint mPaintLine, mPaintText, mPaintChart, mPaintDot, mPaintValue;
    private float mTextSpacing = dp2px(5);//轴属性文字与图标的间距
    private Path mPath = new Path();//缓存线路路径

    private boolean isAvailableData = false;//校验数据合法性的flag

    public SpiderChartView(Context context) {
        this(context, null);
    }

    public SpiderChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpiderChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化画笔
     */
    private void init() {
        mPaintLine = new Paint();
        mPaintLine.setAntiAlias(true);
        mPaintLine.setColor(0x33000000);
        mPaintLine.setStrokeWidth(1);
        mPaintLine.setStyle(Paint.Style.STROKE);


        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setTextSize(mTextSize);
        mPaintText.setColor(mTextColor);

        mPaintChart = new Paint();
        mPaintChart.setAntiAlias(true);
        mPaintChart.setStrokeWidth(1);
        mPaintChart.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaintDot = new Paint();
        mPaintDot.setAntiAlias(true);
        mPaintDot.setStyle(Paint.Style.FILL);
        mPaintDot.setStrokeWidth(dp2px(3));

        mPaintValue = new Paint();
        mPaintValue.setAntiAlias(true);
        mPaintValue.setColor(mValueColor);
        mPaintValue.setTextSize(mValueSize);

        dataSet = new ArrayList<>();
        for(int i=0; i<4; i++) {
            List<Double> data1 = new ArrayList<>();
            data1.add(30.);
            data1.add(45.);
            data1.add(67.);
            data1.add(32.);
            data1.add(32.);
            data1.add(32.);
            data1.add(32.);
            data1.add(32.);
            dataSet.add(data1);
        }
        indexLabelSet = new ArrayList<>();

        indexLabelSet.add("我国");
        indexLabelSet.add("我国");
        indexLabelSet.add("我国");
        indexLabelSet.add("数据体验");
        indexLabelSet.add("我国");
        indexLabelSet.add("我国");
        indexLabelSet.add("我国");
        indexLabelSet.add("我国");
    }


    /**
     * 绑定数据
     * @param dataSet
     * @param indexLabelSet
     */
    public void setData(List<List<Double>> dataSet, List<String> indexLabelSet) {
        this.dataSet = dataSet;
        this.indexLabelSet = indexLabelSet;
        invalidate();
    }

    /**
     * 设置数据集的颜色集
     * @param colors
     */
    public void setValueColorSet(List<Integer> colors) {
        this.mValueColorSet = colors;
        invalidate();
    }

    /**
     * 设置坐标轴文字大小
     * @param textSize
     */
    public void setmTextSize(float textSize) {
        this.mTextSize = textSize;
        invalidate();
    }

    /**
     * 设置坐标轴文字颜色
     * @param color
     */
    public void setTextColor(int color) {
        this.mTextColor = color;
        invalidate();
    }

    /**
     * 设置坐标轴文字与坐标轴之间的距离
     * @param dimension
     */
    public void setTextSpacing(float dimension) {
        this.mTextSpacing = dimension;
        invalidate();
    }
    /**
     * 数据准备 包括检查数据合法性 初始化基础数据
     */
    private void prepareData() {
        if(null == dataSet || dataSet.size() == 0 //数据和坐标轴内容不能为空
                || null == indexLabelSet || 0 ==indexLabelSet.size()) {
            isAvailableData = false;
            return;
        }
        int sizeOfDataParts = indexLabelSet.size();//维度(坐标轴)数量
        degreePerPart = 360f / sizeOfDataParts;//每个维度所占的角度

        parts = sizeOfDataParts;//维度(坐标轴)数量
        //检查数据合法性 维度必须大于等于3 否则不是图形而是线了
        if(3 > sizeOfDataParts || null == indexLabelSet || sizeOfDataParts != indexLabelSet.size()) {
            isAvailableData = false;
            return;
        }

        //检查数据颜色数量  数量不足或者小于数据集数量,则补充默认颜色
        if(null == mValueColorSet) {
            mValueColorSet = new ArrayList<>();
            for(int i=0; i<dataSet.size(); i++) {
                mValueColorSet.add(VALUE_COLOR);
            }
        } else if(mValueColorSet.size() < dataSet.size()) {
            for(int i = 0; i<dataSet.size() - mValueColorSet.size(); i++) {
                mValueColorSet.add(VALUE_COLOR);
            }
        }

        for(int i=0; i<dataSet.size(); i++) {//检查数据的维度数量与坐标轴是否一致
            List<Double> data = dataSet.get(i);
            if(null == data || data.size() != sizeOfDataParts) {
                isAvailableData = false;
                return;
            }
            for(int j=0; j<data.size(); j++) {//检查数据的范围是否在0-100
                Double numData = data.get(j);
                if(numData == -255) {
                    data.set(i, 0d);
                }
                if(numData > 100 || numData < 0) {
                    isAvailableData = false;
                    return;
                }
            }
        }
        //数据检查完毕 都是0-100的数据
        //分配半径数据，计算上下左右文字剩余空间
        int maxTextLengthPostion = 0;
        int maxTextLength = 0;
        for(int i=0; i<indexLabelSet.size(); i++) {//获取维度坐标文字最长的一个
            String text = indexLabelSet.get(i);
            int temTextLength = text.length();
            if(temTextLength > maxTextLength) {
                maxTextLength = temTextLength;
                maxTextLengthPostion = i;
            }
        }
        Rect textRect = new Rect();
        String maxText = indexLabelSet.get(maxTextLengthPostion);
        mPaintText.getTextBounds(maxText, 0, maxText.length(), textRect);
        int textWidth = textRect.width();
        int textHeight = textRect.height();
        float chartSizeWidth = width - 2 * textWidth - 2 * mTextSpacing; //除去左右文字长度
        float chartSizeHeight = height - 2 * textHeight - 2 * mTextSpacing;//除去上下文字高度
        maxChartLength = chartSizeWidth > chartSizeHeight ? chartSizeHeight * .5f:chartSizeWidth*.5f;
        //数据合规，可以绘制
        isAvailableData = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        prepareData();

        if(!isAvailableData) {
            return;//数据不合规
        }

        canvas.save();
        canvas.translate(centerX, centerY);//将画布移动到中心点,便于作画
        drawOutSideLine(canvas);//画辅助线框
        drawOutsideText(canvas);//画坐标轴文字
//        for(int i=0; i<dataSet.size(); i++) {
//
//        }
        drawValues(canvas, dataSet.get(0), mValueColorSet.get(0));//画数据
        canvas.restore();
    }

    /**
     * 画辅助线
     * @param canvas
     */
    private void drawOutSideLine(Canvas canvas) {
        for(int i = 1; i<=deep; i++) {
            mPath.reset();
            for(int j=0; j<parts; j++) {
                float degree = degreePerPart * j + START_DEGREE;
                float x = (float) (maxChartLength * i / deep * (Math.cos(Math.toRadians(degree))));
                float y = (float) (maxChartLength * i / deep * (Math.sin(Math.toRadians(degree))));
                if(j == 0) {
                    mPath.moveTo(x, y);
                } else {
                    mPath.lineTo(x, y);
                }
            }
            mPath.close();
            canvas.drawPath(mPath, mPaintLine);
        }
        for(int i=0; i<parts; i++) {//画中心点到维度顶点的连线
            float degree = degreePerPart * i + START_DEGREE;
            float x = (float) (maxChartLength * (Math.cos(Math.toRadians(degree))));
            float y = (float) (maxChartLength * (Math.sin(Math.toRadians(degree))));
            canvas.drawLine(0, 0, x, y, mPaintLine);
        }
    }

    private Rect temRect = new Rect();
    /**
     * 画文字 对应的坐标轴文字
     * @param canvas
     */
    private void drawOutsideText(Canvas canvas) {
        for (int i = 0; i < indexLabelSet.size(); i++) {
            String text = indexLabelSet.get(i);
            //计算最外层顶点位置
            float degree = (degreePerPart * i + START_DEGREE + 360) % 360;//因为画雷达图时从正上开始画第一个数据 所以判定文字位置时要考虑
            float x = (float) (maxChartLength  * (Math.cos(Math.toRadians(degree))));
            float y = (float) (maxChartLength  * (Math.sin(Math.toRadians(degree))));

            mPaintText.getTextBounds(text, 0, text.length(), temRect);
            int textWidth = temRect.width();
            int textHeight = temRect.height();
            Paint.FontMetricsInt fmi = mPaintText.getFontMetricsInt();
            float baseline = (fmi.descent + Math.abs(fmi.ascent)) * 0.5f - fmi.descent;
            //根据角度判断文字与图标x，y的距离
            float xSpacing = (float) Math.cos(Math.toRadians(degree)) * mTextSpacing;
            float ySpacing = (float) Math.sin(Math.toRadians(degree)) * mTextSpacing;
            x += xSpacing;
            y += ySpacing;
            //根据x， y位置来确定文字位置
            if( degree == 0) { //在四象限右边轴上 文字左端对齐顶点
                canvas.drawText(text, x, y + baseline, mPaintText);
            } else if(degree > 0 && degree < 90) { //文字在右下 文字左上对齐顶点
                canvas.drawText(text, x, y - fmi.ascent, mPaintText);
            } else if(degree == 90) { //文字正下 与文字中上对齐
                canvas.drawText(text, x - 0.5f * textWidth, y - fmi.ascent, mPaintText);
            } else if(degree > 90 && degree < 180) {//文字左下，与文字右上对齐
                canvas.drawText(text, x - textWidth, y - fmi.ascent, mPaintText);
            } else if (degree == 180) {//文字最左，右中对齐
                canvas.drawText(text, x - textWidth, y + baseline, mPaintText);
            } else if(degree > 180 && degree < 270) {//文字左上 右下对齐
                canvas.drawText(text, x - textWidth, y - fmi.descent , mPaintText);
            } else if(degree == 270) {//文字正上 中下对齐
                canvas.drawText(text, x - textWidth * 0.5f, y - fmi.descent , mPaintText);
            } else if(degree > 270 && degree < 360){//文字右上 左下对齐
                canvas.drawText(text, x , y - fmi.descent , mPaintText);
            }
        }
    }
    /**
     * 画单独一组数据
     * @param canvas
     * @param data
     */
    private void drawValues(Canvas canvas, List<Double> data, int color) {
        mPaintChart.setColor(color);
        mPaintDot.setColor(color);
        mPaintChart.setAlpha(64);
        mPaintDot.setAlpha(200);
        mPath.reset();
        mPath.moveTo((float) (maxChartLength /100 * data.get(0)), 0);
        for (int i = 0; i < data.size(); i++) {
            double value = data.get(i);
            float degree = i * degreePerPart + START_DEGREE;
            float x = (float) (maxChartLength / 100 * value * (Math.cos(Math.toRadians(degree))));
            float y = (float) (maxChartLength / 100 * value * (Math.sin(Math.toRadians(degree))));
            if(i == 0) {
                mPath.moveTo(x, y);
            } else {
                mPath.lineTo(x, y);
            }
            canvas.drawCircle(x, y, dp2px(2), mPaintDot);
        }
        mPath.close();
        canvas.drawPath(mPath, mPaintChart);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed) {
            width = getWidth();
            height = getHeight();
            centerX = (float) (width * .5);
            centerY = (float) (height * .5);
        }
    }

    private float dp2px(float dp) {
        return getContext().getResources().getDisplayMetrics().density * dp;
    }

    private float sp2px(float sp) {
        return getContext().getResources().getDisplayMetrics().scaledDensity * sp;
    }
}
