

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GCLineChart extends LineChart {

    private boolean isPercentageValue = false;

    private static DecimalFormat df = new DecimalFormat("#.##");

    public void setData(List<String> labels, List<Double> valueList) {
        setData(labels, valueList, false);
    }

    /**
     * 将数据设置到图表上
     * @param labels
     * @param valueList
     */
    public void setData(final List<String> labels, List<Double> valueList, boolean isPercentageValue) {
        this.isPercentageValue = isPercentageValue;
        if (null == labels || labels.size() == 0 || null == valueList || valueList.size() == 0
                || labels.size() != valueList.size()) {
            setData(null);
            return;
        }
        List<Entry> values = new ArrayList<>();
        List<ILineDataSet> dataSets = new ArrayList<>();

        double maxValue = 0;

        for (int i=0; i<labels.size(); i++) {
            if (-255 == valueList.get(i)) {
                if (values.size() > 0) {
                    // 遇到一个“断开”的点 如果之前有添加过点，先将之前的点组装成一个新的数据集
                    LineDataSet set1 = new LineDataSet(values, "set1" + i);
                    // 设置数据集
                    setupDataSet(set1);
                    dataSets.add(set1);

                    // 将“断点”前的点加入数据集后 将断点也组装成新的数据集加入 Float.NaN表示这个点不展示
                    values = new ArrayList<>();
                    values.add(new Entry(i, Float.NaN));
                    set1 = new LineDataSet(values, "set1" + i);
                    // 设置数据集
                    setupDataSet(set1);
                    dataSets.add(set1);

                    values = new ArrayList<>();
                } else {
                    // 遇到一个“断开”的点 之前没有添加过点，直接将"断点"组装成数据集加入
                    values.add(new Entry(i, Float.NaN));
                    LineDataSet set1 = new LineDataSet(values, "set1" + i);
                    // 设置数据集
                    setupDataSet(set1);
                    dataSets.add(set1);

                    values = new ArrayList<>();
                }
            } else {
                // 计算最大值
                maxValue = Math.max(maxValue, valueList.get(i).floatValue());
                // 添加正常的数据
                if (isPercentageValue) {
                    values.add(new Entry(i, valueList.get(i).floatValue() * 100));
                } else {
                    values.add(new Entry(i, valueList.get(i).floatValue()));
                }
            }
        }
        if (values.size() > 0) {
            if (0 != maxValue) {
                if (isPercentageValue) {
                    getAxisLeft().setAxisMaximum((float) (maxValue * 120f));
                } else {
                    getAxisLeft().setAxisMaximum((float) (maxValue * 1.2f));
                }
            } else {
                getAxisLeft().resetAxisMaximum();
            }
            // 数据从0开始，设置x轴最小值为-1 可以空出一个数值的距离
            getXAxis().setAxisMinimum(-1f);
            // 设置x轴最大值 右侧空出一个数据空间
            getXAxis().setAxisMaximum(values.size());
            LineDataSet set1 = new LineDataSet(values, "set1");
            // 设置数据集
            setupDataSet(set1);
            dataSets.add(set1);
        }

        LineData data = new LineData(dataSets);
        setData(data);
        notifyDataSetChanged();
        getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (null != labels && index >=0 && index < labels.size()) {
                    return labels.get(index);
                } else {
                    return "";
                }
            }
        });
        getAxisLeft().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (isPercentageValue) {
                    // 在加入数据集时已经乘过100 这里不用再乘
                    return df.format(value) + "%";
                } else {
                    return df.format(value);
                }

            }
        });
        setAutoScaleMinMaxEnabled(false);
    }

    /**
     * 为图表数据设置 显示风格
     * @param dataSet
     */
    private void setupDataSet(LineDataSet dataSet) {
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value != Float.NaN) {
                    if (isPercentageValue) {
                        // 在加入数据集时已经乘过100 这里不用再乘
                        return df.format(value) + "%";
                    } else {
                        return super.getFormattedValue(value);
                    }
                } else {
                    return super.getFormattedValue(value);
                }
            }
        });
        dataSet.disableDashedLine(); // 不使用虚线
        dataSet.setColor(0xFF4F7AFD);
        dataSet.setCircleColor(0xFF4F7AFD);
        dataSet.setCircleHoleColor(0xFFFFFFFF);
        dataSet.setCircleRadius(3);
        dataSet.setLineWidth(2);
        dataSet.setHighlightEnabled(false);
        dataSet.setDrawCircles(true); // 设置画折现连接处的圆点
        dataSet.setDrawCircleHole(true); // 画空心样式圆点
        dataSet.setDrawValues(true); // 画数值文字
        dataSet.setDrawFilled(false); // 不画 x轴到折现的透明层
    }



    private void setGCLineStyle() {
        getDescription().setEnabled(false);// 右下角的描述信息
        setTouchEnabled(true);
//        setDrawGridBackground(false);
        getAxisRight().setEnabled(false); // 右侧y轴去除
        getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // x轴设置到下方
        setScaleYEnabled(false); // 禁止竖向缩放
        getXAxis().setDrawGridLines(false); // 不显示纵向的标线
        getXAxis().setAvoidFirstLastClipping(true); //
        getXAxis().setDrawAxisLine(false); // 不画x轴
//        getXAxis().setSpaceMin(0.5f); // 第一个坐标距离原点 (误)
        getAxisLeft().setDrawGridLines(true); // 画y轴横线
        getAxisLeft().setDrawAxisLine(false); // 不画Y轴轴线
        getLegend().setEnabled(false); // 不显示下方的图例
        getAxisLeft().setAxisMinimum(0); // 设置y轴纵坐标最小为0
        getXAxis().setGranularity(1f); // 设置x轴最小间距 防止重复x轴数据
        getAxisLeft().setSpaceTop(20f); // 设置在图表上最高处的值相比轴上最高值的顶端空间（总轴范围的百分比）
//        getXAxis().setCenterAxisLabels(true); // 为true 则x轴文字显示在两个点中间
        getAxisLeft().setDrawTopYLabelEntry(true);
        getAxisLeft().setGranularity(1);
//        getAxisLeft().setLabelCount(5,false);


        setDragEnabled(true);
        setScaleEnabled(false);
        setScaleXEnabled(false);
        setScaleYEnabled(false);
        setPinchZoom(false); // 禁止双指缩放


        setNoDataText("暂无数据");
        setNoDataTextColor(Color.BLACK);
    }

    public GCLineChart(Context context) {
        super(context);
        setGCLineStyle();
    }

    public GCLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGCLineStyle();
    }

    public GCLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setGCLineStyle();
    }

    private int dp2px(int dp) {
        int px = (int) (getContext().getResources().getDisplayMetrics().density * dp + .5);
        return px;
    }

    private float sp2px(int sp) {
        float px = getContext().getResources().getDisplayMetrics().scaledDensity * sp;
        return px;
    }
}
