package com.zb.basic.acts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.zb.basic.R;
import com.zb.basic.adapter.ViewPagerAdapter;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private static final float MIN_SCALE = 0.70f;
    private static final float MIN_ALPHA = 0.5f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter());
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageMargin(30);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                if (position < 0) {
                    float scaleX = 1 + 0.3f * position;
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                } else {
                    float scaleX = 1 - 0.3f * position;
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                }
                page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            }
        });
    }
}