package com.zb.basic.acts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zb.basic.R;
import com.zb.common.animation.MyAnimationDrawable;
import com.zb.common.utils.P;

public class FrameAnimationActivity extends AppCompatActivity {

    private boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        FrameLayout fl = findViewById(R.id.frame);
        ImageView iv = findViewById(R.id.frame_imge);

//        MyAnimationDrawable.animateRawManuallyFromXML(R.drawable.frame_animation, iv, new Runnable() {
//            @Override
//            public void run() {
//                P.p("开始动画了");
//            }
//        }, new Runnable() {
//            @Override
//            public void run() {
//                P.p("结束动画了");
//            }
//        });

        AnimationDrawable ad = (AnimationDrawable) iv.getDrawable();

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag) {
                    ad.start();
                    flag = false;
                } else {
                    ad.stop();
                    flag = true;
                }
            }
        });
    }
}