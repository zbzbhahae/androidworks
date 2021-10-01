package com.zb.customview.widgets.myproj;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.zb.common.utils.DensityUtil;
import com.zb.customview.widgets.myproj.bean.Spider;

import java.util.List;


/**
 * Created by yWX419033 on 2019/6/10.
 */

public class RadarView extends View
{
    //设置View的默认大小
    //    private final static int DEFAULTSIZE = 500;
    private float mDefaultMaxR;
    private float mMaxR; //最长的线段  最大值
    private int mCount = 5; //多边形层数
    private float mR; //根据mMaxR 和 mCount计算   多边形之间间距
    private float mSizeCount; //多边形N
    private float mAngle; //计算角度
    private double mMaxNumber = 100; //mMaxR代表的线段的长度   用来计算各个连线的比例
    private int mCenterW; //中心位置
    private int mCenterH;
    private Paint mSpiderPaint; //蛛网画笔
    private Paint mDataOutPaint; //数据区域2画笔
    private Paint mTextPaint; //文字画笔
    private List<Spider> spiders1;
    private int currentColor = Color.parseColor("#7EBEFF");
    private Paint mValueTextPaint;
    private static final float DOT_FIVE = 0.5f;
    private Paint mBgPaint;

    public RadarView(Context context)
    {
        super(context);
        init(context);
    }

    public RadarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    //初始化画笔  正式使用的时候可以在这里获取一些 用户自定义的attrs参数
    private void init(Context context)
    {

        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setColor(Color.WHITE);
        mBgPaint.setStrokeWidth(2);
        mBgPaint.setStyle(Paint.Style.FILL);

        mSpiderPaint = new Paint();
        mSpiderPaint.setAntiAlias(true);
        mSpiderPaint.setColor(Color.parseColor("#AEDBFF"));
        mSpiderPaint.setAlpha(150);
        mSpiderPaint.setStrokeWidth(2);
        mSpiderPaint.setStyle(Paint.Style.STROKE);

        mDataOutPaint = new Paint();
        mDataOutPaint.setAntiAlias(true);
        mDataOutPaint.setColor(currentColor);
        //        mDataOutPaint.setAlpha(50);
        mDataOutPaint.setStrokeWidth(2);
        mDataOutPaint.setStyle(Paint.Style.FILL);

        mValueTextPaint = new Paint();
        mValueTextPaint.setTextSize(DensityUtil.dip2px(context, 13));
        mValueTextPaint.setStyle(Paint.Style.STROKE);
        mValueTextPaint.setColor(Color.parseColor("#7EBEFF"));
        //        mValueTextPaint.setAlpha(98);
        mValueTextPaint.setTextAlign(Paint.Align.CENTER);
        mValueTextPaint.setAntiAlias(true);
        mValueTextPaint.setTypeface(Typeface.DEFAULT_BOLD);

        mTextPaint = new Paint();
        mTextPaint.setTextSize(DensityUtil.dip2px(context, 10));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.parseColor("#7C7C7C"));
        //        mTextPaint.setAlpha(98);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //可以在这里处理View自身的测量情况  wrap
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST)
        {
            //            heightSpecSize = DEFAULTSIZE;
            heightSpecSize = getMeasuredHeight();
        }
        else
        {
            heightSpecSize = Math.min(heightSpecSize, widthSpecSize);
        }
        //        widthSpecSize = heightSpecSize + 310;
        widthSpecSize = getMeasuredWidth();

        setMeasuredDimension(widthSpecSize, heightSpecSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterW = w / 2;
        mCenterH = h / 2 - dip2px(20);
        mDefaultMaxR = h / 2f - dip2px(40);//随便计算一下 最大值
        //        Log.e("SpiderView", String.format("控件默认的最大的R为%f", mDefaultMaxR));
        //防止用户设置的值 突破天际
        if (mMaxR > 0f)
        {
            mMaxR = Math.min(mDefaultMaxR, mMaxR);
        }
        else
        {
            mMaxR = mDefaultMaxR;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (spiders1 == null || spiders1.size() < 3)
        {//没有值或者值小于3 直接不画
            mSizeCount = 6;
            mR = mMaxR / (mCount - 1);
            mAngle = (float) Math.PI * 2 / mSizeCount;
            canvas.translate(mCenterW, mCenterH + 30); //将原点移动至中心
            drawSpiderBase(canvas);
            drawSpiderSupport(canvas);
            return;
        }
        //        mSizeCount = spiders1.size();
        mSizeCount = 12;
        mR = mMaxR / (mCount - 1);
        mAngle = (float) Math.PI * 2 / mSizeCount;
        canvas.translate(mCenterW, mCenterH + 30); //将原点移动至中心
        //分别绘制
        drawSpiderBase(canvas);
        drawSpiderSupport(canvas);
        //        drawSpiderData(canvas);  //上月数据
        drawSpiderDataOut(canvas); //最新月份数据
        drawSpiderText(canvas);
    }

    /**
     * 绘制数据区域
     */
    private void drawSpiderDataOut(Canvas canvas)
    {
        if (spiders1 == null || spiders1.size() == 0)
        {
            return;
        }
        Path path = new Path();
        //连接整个数据区域
        for (int i = 0; i < mSizeCount; i++)
        {
            //            float curR = (float) (spiders1.get(i).val / mMaxNumber * mMaxR);
            float curR = (float) (spiders1.get(i).val / spiders1.get(i).value * mMaxR);
            float y = -(float) (curR * Math.cos(mAngle * i));
            float x = (float) (curR * Math.sin(mAngle * i));
            if (i == 0)
            {
                path.moveTo(0, -curR);
            }
            else
            {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x, y, 2, mDataOutPaint);
        }
        path.close();
        mDataOutPaint.setColor(currentColor);

        mDataOutPaint.setStyle(Paint.Style.STROKE);
        mDataOutPaint.setStrokeWidth(2);
        canvas.drawPath(path, mDataOutPaint);

        mDataOutPaint.setAlpha(80);

        mDataOutPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, mDataOutPaint);
        //绘制小圆点
        mDataOutPaint.setStyle(Paint.Style.STROKE);
        mDataOutPaint.setColor(Color.WHITE);
        mDataOutPaint.setAlpha(220);
        mDataOutPaint.setStrokeWidth(1);

    }

    /**
     * 为每一个点增加名字说明
     */
    private void drawSpiderText(Canvas canvas)
    {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < mSizeCount; i++)
        {
            //计算说明文字的坐标
            float y = -(float) ((mMaxR + fontHeight / 2) * Math.cos(mAngle * i));
            float x = (float) ((mMaxR + fontHeight / 2) * Math.sin(mAngle * i));
            //记录画布位置
            canvas.save();
            canvas.translate(x, y);
            float curAngle = (mAngle) * i;
            //旋转角度  摆正说明文字的位置

            switch (i)
            {
                case 0:
                    canvas.rotate(-curAngle + 0);//上
                    canvas.drawText(spiders1.get(i).name, x, 0, mTextPaint);
                    break;
                case 1:
                    canvas.rotate(curAngle - 1);
                    canvas.rotate(-curAngle + 1);//右  //-x + y / 2 + dis / 2   中心点  45
                    canvas.drawText(spiders1.get(i).name, x - dip2px(13), dip2px(5f), mTextPaint);
                    break;
                case 2:
                    canvas.rotate(curAngle - 2);
                    canvas.rotate(-curAngle + 2);//右下
                    canvas.drawText(spiders1.get(i).name, x - dip2px(37), dip2px(7f), mTextPaint);
                    break;
                case 3:
                    canvas.rotate(curAngle - 3);
                    canvas.rotate(-curAngle + 3);//坐下
                    canvas.drawText(spiders1.get(i).name, x - dip2px(55), dip2px(4), mTextPaint);
                    break;
                case 4:
                    canvas.rotate(curAngle - 4);
                    canvas.rotate(-curAngle + 4);//左
                    canvas.drawText(spiders1.get(i).name, x - dip2px(35), dip2px(3), mTextPaint);
                    break;
                case 5:
                    canvas.rotate(curAngle - 5);
                    canvas.rotate(-curAngle + 5);//左
                    canvas.drawText(spiders1.get(i).name, x - dip2px(20), dip2px(3), mTextPaint);
                    break;
                case 6:
                    canvas.rotate(curAngle - 6);//左
                    canvas.rotate(-curAngle + 6);//左
                    canvas.drawText(spiders1.get(i).name, x, dip2px(8), mTextPaint);
                    break;
                case 7:
                    canvas.rotate(curAngle - 7);//左
                    canvas.rotate(-curAngle + 7);//左
                    canvas.drawText(spiders1.get(i).name, x + dip2px(20), dip2px(3), mTextPaint);
                    break;
                case 8:
                    canvas.rotate(curAngle - 8);//左
                    canvas.rotate(-curAngle + 8);//左
                    canvas.drawText(spiders1.get(i).name, x + dip2px(45), dip2px(3), mTextPaint);
                    break;
                case 9:
                    canvas.rotate(curAngle - 9);//左
                    canvas.rotate(-curAngle + 9);//左
                    canvas.drawText(spiders1.get(i).name, x + dip2px(50), dip2px(4), mTextPaint);
                    break;
                case 10:
                    canvas.rotate(curAngle - 10);//左
                    canvas.rotate(-curAngle + 10);//左
                    canvas.drawText(spiders1.get(i).name, x + dip2px(45), dip2px(6), mTextPaint);
                    break;
                case 11:
                    canvas.rotate(curAngle - 11);//左
                    canvas.rotate(-curAngle + 11);//左
                    canvas.drawText(spiders1.get(i).name, x + dip2px(10), dip2px(5f), mTextPaint);
                    break;
            }
            canvas.restore();
        }
        mSpiderPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 3, mSpiderPaint);
        mSpiderPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * dip to px 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dip)
    {
        float density = getDensity(getContext());
        return (int) (dip * density + DOT_FIVE);
    }

    /**
     * get screen density
     */
    private float getDensity(Context context)
    {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 链接蛛网的线
     */
    private void drawSpiderSupport(Canvas canvas)
    {
        mSpiderPaint.setColor(Color.parseColor("#AFDAFF"));
        for (int i = 0; i < mSizeCount; i++)
        {
            canvas.drawLine(0f, 0f, (float) (mMaxR * Math.sin(mAngle * i)), -(float) (mMaxR * Math.cos(mAngle * i)),
                mSpiderPaint);
        }

        mSpiderPaint.setColor(Color.parseColor("#FFA389"));
        for (int i = 0; i < mSizeCount; i++)
        {
            canvas.drawLine(0f, 0f, (float) (mMaxR * 0.5f * Math.sin(mAngle * i)),
                -(float) (mMaxR * 0.5f * Math.cos(mAngle * i)), mSpiderPaint);
        }
    }

    /**
     * 画出蛛网
     */
    private void drawSpiderBase(Canvas canvas)
    {

        Path path2 = new Path();

        for (int j = 0; j < mSizeCount; j++)
        {
            if (j == 0)
            {
                path2.moveTo(0, -mR * 2); //5是5角型
            }
            else
            {
                //利用三角函数计算 坐标x, y的值
                path2.lineTo((float) (mR * 2 * Math.sin(mAngle * j)), -(float) (mR * 2 * Math.cos(mAngle * j)));
            }
        }
        path2.close();
        canvas.drawPath(path2, mBgPaint);

        Path path = new Path();
        for (int i = 1; i < mCount; i++)
        { //最中心的点不用画出
            path.reset();
            if (i == 1)
            {
                mSpiderPaint.setColor(Color.parseColor("#AFDAFF"));
                canvas.drawCircle(0, 0, 2, mSpiderPaint);
            }
            else if (i == 2)
            {
                mSpiderPaint.setColor(Color.parseColor("#9EE09C"));
                canvas.drawCircle(0, 0, 2, mSpiderPaint);
            }
            for (int j = 0; j < mSizeCount; j++)
            {
                if (j == 0)
                {
                    path.moveTo(0, -mR * i);
                }
                else
                {
                    //利用三角函数计算 坐标x, y的值
                    path.lineTo((float) (mR * i * Math.sin(mAngle * j)), -(float) (mR * i * Math.cos(mAngle * j)));
                    //Log.e("SpiderView",String.format("x,y的坐标分别为：%f,%f",(float) (mR*i*Math.cos(mAngel*j)),(float) (mR*i*Math.sin(mAngel*j))));
                }
            }
            path.close();
            canvas.drawPath(path, mSpiderPaint);
        }

    }

    /**
     * 设置蜘蛛网的层级
     */
    public void setSpiderCount(int spiderCount)
    {
        if (spiderCount < 2)
            return;
        mCount = spiderCount;
        invalidate();
    }

    /**
     * 设置蜘蛛网的最长半径
     */
    public void setSpiderMaxR(float spiderMaxR)
    {
        if (mDefaultMaxR > 0f)
        {
            mMaxR = Math.min(mDefaultMaxR, spiderMaxR);
        }
        else
        {
            mMaxR = spiderMaxR;
        }
        invalidate();
    }

    /**
     * 设置数据
     */
    public void setDate(List<Spider> spiders1, double maxValue)
    {
        this.spiders1 = spiders1; //新的
        //        this.spiders2 = spiders2;
        this.mMaxNumber = maxValue;
        invalidate();
    }

    /**
     * 设置R最长代表的值，用于计算实际占用比例
     *
     * @param mMaxNumber 默认100
     */
    public void setMaxNumber(float mMaxNumber)
    {
        this.mMaxNumber = mMaxNumber;
        invalidate();
    }

    //    @Override
    //    public boolean onTouchEvent(MotionEvent event) {
    //        float yStart;
    //        float xStart;
    //        switch (event.getAction()) {
    //
    //            case MotionEvent.ACTION_DOWN:
    //                yStart = event.getY();
    //                xStart = event.getX();
    //                //计算说明文字的坐标
    //                for (int i = 0; i < chartXYList.size(); i++) {
    //                    if (xStart > chartXYList.get(i).startX && xStart < chartXYList.get(i).endX
    //                            && yStart > chartXYList.get(i).startY && yStart < chartXYList.get(i).endY) {
    //                        touchTag = i;
    //                        break;
    //                    } else {
    //                        if (currentType == 0) {
    //                            touchTag = -1;
    //                        }
    //                    }
    //                }
    ////                onTypeClickListener.onClickType(touchTag);
    //                invalidate();
    //                break;
    //            case MotionEvent.ACTION_MOVE:
    //
    //                break;
    //            case MotionEvent.ACTION_UP:
    //                break;
    //
    //            default:
    //                break;
    //        }
    //
    //        return super.onTouchEvent(event);
    //    }

    //    public void setOnTypeClickListener(OnTypeClickListener onTypeClickListener) {
    //        this.onTypeClickListener = onTypeClickListener;
    //    }
    //
    //    public void reset() {
    //        touchTag = -1;
    //        invalidate();
    //    }

    /**
     * 获取丈量文本的矩形
     */
    private Rect getTextBounds(String text, Paint paint)
    {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }

}
