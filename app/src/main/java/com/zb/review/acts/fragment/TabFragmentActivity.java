package com.zb.review.acts.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.utils.P;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.OnItemSelected;

public class TabFragmentActivity extends BaseActivity {

    @BindView(R.id.tab_fragment_tabs)
    BottomNavigationView navigationTab;
    @BindView(R.id.tab_fragment_refresh)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_fragment);


//        navigationTab.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//                P.p("setOnNavigationItemSelectedListener  " + item.getItemId());
//                return false;
//            }
//        });

        Handler h = new Handler(getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        refreshLayout.setRefreshing(false);
                        return true;
                }
                return false;
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //go fech data
                h.sendEmptyMessageDelayed(1, 5000);
            }
        });

//        navigationTab.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//                return false;
//            }
//        });
    }


}
