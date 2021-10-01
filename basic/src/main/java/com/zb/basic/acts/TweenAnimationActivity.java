package com.zb.basic.acts;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zb.basic.R;

public class TweenAnimationActivity extends BaseActivity {

    private ImageView iv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        iv = findViewById(R.id.frame_imge);

        iv.setImageResource(R.mipmap.compose2);

        Animation ad = AnimationUtils.loadAnimation(this, R.anim.tween_animation);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.startAnimation(ad);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
