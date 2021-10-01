package com.zb.review.acts;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zb.review.R;
import com.zb.review.adapter.ViewpagerAdapter;
import com.zb.review.utils.P;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ViewpagerActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager2 viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;


    private ViewpagerAdapter adapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        initData();
        initView();
    }

    void initData() {
        data.add("https://img1.baidu.com/it/u=558332831,4064459262&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=4048355283,3954849849&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=23556030,3880179134&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img1.baidu.com/it/u=558332831,4064459262&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=4048355283,3954849849&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=23556030,3880179134&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img1.baidu.com/it/u=558332831,4064459262&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=4048355283,3954849849&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=23556030,3880179134&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img1.baidu.com/it/u=558332831,4064459262&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=4048355283,3954849849&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=23556030,3880179134&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img1.baidu.com/it/u=558332831,4064459262&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=4048355283,3954849849&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=23556030,3880179134&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img1.baidu.com/it/u=558332831,4064459262&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=4048355283,3954849849&fm=26&fmt=auto&gp=0.jpg");
        data.add("https://img2.baidu.com/it/u=23556030,3880179134&fm=26&fmt=auto&gp=0.jpg");
//        tabLayout.addTab(tabLayout.newTab().setText(" 页 "));
//        tabLayout.addTab(tabLayout.newTab().setText(" 页 "));
//        tabLayout.addTab(tabLayout.newTab().setText(" 页 "));
    }

    void initView() {


        adapter = new ViewpagerAdapter();
        viewPager.setAdapter(adapter);
        adapter.setData(data);
        tabLayout.setTabMode(TabLayout.MODE_AUTO);//设置当tab多时 可以滚动显示
        tabLayout.setTabTextColors(getResources().getColorStateList(R.color.buttton));

        TabLayoutMediator tm = new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                tab.setText("页" + position);
            }
        });
        tm.attach();
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                P.p("ViewPager registerOnPageChangeCallback position->" + position);
            }
        });
    }

}
