package com.zb.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import com.zb.customview.widgets.CustomRattingBar;
import com.zb.customview.widgets.RoundProgressBar;

public class MainActivity extends AppCompatActivity {

    private RoundProgressBar mProgressBar;
    private CustomRattingBar rattingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.RoundProgressBar);
        rattingBar = findViewById(R.id.myRattingBar);

        ValueAnimator oa = ValueAnimator.ofFloat(0, 0.8543f);
        oa.setDuration(2000);
        oa.setInterpolator(new BounceInterpolator());
        ValueAnimator finalOa1 = oa;
        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float current = (float) finalOa1.getAnimatedValue();
                mProgressBar.setPercent(current);
            }
        });
        oa.start();

        oa = ValueAnimator.ofFloat(0, 0.8543f);
        oa.setDuration(2000);
        oa.setInterpolator(new LinearInterpolator());
        ValueAnimator finalOa = oa;
        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float current = (float) finalOa.getAnimatedValue();
                rattingBar.setRatting(current);
            }
        });
        oa.start();

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressBar.printTime();
            }
        }, 2500);
    }
}