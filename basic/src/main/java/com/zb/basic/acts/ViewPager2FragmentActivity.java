package com.zb.basic.acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.zb.basic.R;
import com.zb.basic.adapter.ViewPager2Adapter;

public class ViewPager2FragmentActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPager2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2_fragment);

        adapter = new ViewPager2Adapter(this);
        viewPager = findViewById(R.id.fragment_viewpager2);
        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(0);

    }
}