package com.zb.basic;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zb.basic.acts.BaseActivity;
import com.zb.basic.acts.TextViewActivity;
import com.zb.basic.adapter.MainListAdapter;
import com.zb.basic.mvp.contract.MainContract;
import com.zb.basic.mvp.model.MainModel;
import com.zb.basic.mvp.presenter.MainPresenter;
import com.zb.common.utils.P;
import com.zb.common.utils.RandomUtil;
import com.zb.common.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    private static List<String> data = new ArrayList<>();
    private static List<Class> destinations = new ArrayList<>();


    private RecyclerView recyclerView;
    private MainListAdapter adapter;


    private void test() {
        boolean a = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initViews();
        initListener();
        test();


        presenter = new MainPresenter();
        presenter.attach(this);
        presenter.initRecyclerData();

        recyclerView.smoothScrollToPosition(adapter.getItemCount()-1);
    }

    private void initListener() {
        adapter.setOnItemClickedListener(new MainListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {
                presenter.performItemClick(position);
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.main_recycler_view);
        adapter = new MainListAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void refreshRecycler(List<String> data) {
        adapter.setData(data);
    }

    @Override
    public void performanceDestination(Class<Activity> clz) {
        if(null != clz) {
            Intent i = new Intent(this, clz);
            startActivity(i);
        }
    }


}