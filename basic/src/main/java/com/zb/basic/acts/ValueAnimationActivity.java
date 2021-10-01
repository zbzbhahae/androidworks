package com.zb.basic.acts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.basic.R;
import com.zb.common.utils.DensityUtil;
import com.zb.common.utils.P;
import com.zb.common.utils.SystemUtil;

public class ValueAnimationActivity extends BaseActivity {

    private ImageView iv;
    private TextView txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);


        iv = findViewById(R.id.frame_imge);
        iv.setImageResource(R.mipmap.compose2);

//        ValueAnimator va = ValueAnimator.ofFloat(0f, 1f);
//        va.setDuration(2000);
//        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                P.p("属性动画::onAnimationUpdate() value:" + value);
//            }
//        });
//        va.start();

        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "alpha", 1f, 0.5f);
        oa.setDuration(2000);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oa.start();
            }
        });
//        oa.setRepeatMode(ObjectAnimator.REVERSE);
        oa.setRepeatCount(5);
        oa.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                P.p("动画结束 alpha值:" + iv.getAlpha());
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }
            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });

        testDp();

        P.p("100dp = " + DensityUtil.dp2Px(this, 100)
                + "px  &  100px = " +
                DensityUtil.px2Dp(this, 100) + "dp");
    }

    private void testDp() {
        txt = findViewById(R.id.frame_text);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemUtil.killProgress();
            }
        });

        ObjectAnimator oa = new ObjectAnimator();
        oa.setTarget(txt);
    }

    class TextInterpolator extends BounceInterpolator {


    }
}
