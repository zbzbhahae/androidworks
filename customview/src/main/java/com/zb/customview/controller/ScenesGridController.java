package com.zb.customview.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.zb.customview.R;
import com.zb.customview.adapter.GridScenesAdapter;
import com.zb.customview.widgets.decoration.GridDividerItemDecoration;

public class ScenesGridController extends BaseController implements View.OnClickListener {

    //市级场景内容
    private RecyclerView gridView;
    private GridScenesAdapter adapter;

    //标题部分
    private TextView title1;

    public ScenesGridController(Context context) {
        super(context);
    }

    @Override
    protected void initViews(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.controller_scenes, null, false);
        gridView = find(R.id.scene_grid);
        gridView.setHasFixedSize(true);
        gridView.setNestedScrollingEnabled(false);
        gridView.addItemDecoration(new GridDividerItemDecoration());
        gridView.setLayoutManager(
                new GridLayoutManager(context, 3,
                        RecyclerView.VERTICAL, false));
        adapter = new GridScenesAdapter();
        gridView.setAdapter(adapter);

        title1 =  find(R.id.scenes_title_1);


        title1.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scenes_title_1://排序数据，重新显示
                Snackbar snackbar = Snackbar.make(rootView, "点击了标题1", Snackbar.LENGTH_SHORT);
                snackbar.show();
            break;
        }
    }
}
