package com.zb.customview.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.zb.customview.R;

public class CustomRattingBar extends BaseView {

    private float mRatting = 0.5f;
    private Bitmap notRatting;
    private Bitmap ratted;
    private final int SPACING = 5;
    private float bitmapW, bitmapH;
    private Paint paint;
    private float rattingWidth = 0;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackBitmap(canvas);
        drawForegroundBitmap(canvas);
    }

    private void drawBackBitmap(Canvas canvas) {
        int x = 0;
        int y = 0;
        for (int i=0; i<5; i++) {
            canvas.drawBitmap(notRatting, 0f + i * bitmapW + i * SPACING,
                    0, paint);
        }
    }


    private void drawForegroundBitmap(Canvas canvas) {
        RectF rectF = new RectF();
        rectF.left = 0;
        rectF.top = 0;
        rectF.right = rectF.left + mRatting * rattingWidth;
        rectF.bottom = rectF.top + bitmapH;
        canvas.save();
        canvas.clipRect(rectF);
        for (int i=0; i<5; i++) {
            canvas.drawBitmap(ratted, 0f + i * bitmapW + i * SPACING,
                    0, paint);
        }
        canvas.restore();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float currentX = event.getX();
                if(currentX > rattingWidth || currentX < 0) {
                    return false;
                } else {
                    setRatting(currentX / rattingWidth);
                }
                return true;

        }




        return super.onTouchEvent(event);

    }

    public void setRatting(float percent) {
        if(percent > 1 || percent < 0)
            return;
        this.mRatting = percent;
        invalidate();
    }

    public CustomRattingBar(Context context) {
        this(context, null);
    }

    public CustomRattingBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        options.inScaled = true;
        options.inSampleSize = 4;
//        options.inScreenDensity = getResources().getDisplayMetrics().densityDpi;
        notRatting = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_ratting, options);
        ratted = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_ratting_select, options);
        bitmapH = notRatting.getHeight();
        bitmapW = notRatting.getWidth();
        paint = new Paint();
        rattingWidth = 4f * SPACING + 5f * bitmapW;
    }
}
