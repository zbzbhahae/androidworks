package com.zb.review.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.icu.text.RelativeDateTimeFormatter;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.zb.review.utils.P;

import org.jetbrains.annotations.NotNull;

public class CustomImageView extends AppCompatImageView {

    public CustomImageView(@NonNull @NotNull Context context) {
        super(context);
    }

    public CustomImageView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float w = getWidth();
        float h = getHeight();
        float r = w > h? h/2 : w/2;
        Path path = new Path();
        path.addCircle(w/2, h/2, r, Path.Direction.CW);
        canvas.save();
        canvas.clipPath(path);
        super.onDraw(canvas);
        canvas.restore();

    }


}
