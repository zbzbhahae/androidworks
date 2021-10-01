package com.zb.customview;

import android.os.Bundle;
import android.service.autofill.Dataset;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.zb.customview.adapter.GridScenesAdapter;
import com.zb.customview.controller.ScenesGridController;
import com.zb.customview.widgets.charts.HorizontalRounListBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    private ViewGroup scenesContainer;
    private ScenesGridController scenesController;
    private HorizontalRounListBar chart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_grid);
        chart = findViewById(R.id.chart);


        scenesContainer = findViewById(R.id.container);
        scenesController = new ScenesGridController(this);
        scenesController.addToViewGroup(scenesContainer);


        initData();

        scenesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chart.setShowAll(!chart.getShowAll());
            }
        });
    }

    private void initData() {
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        Random r = new Random();
        for(int i=0; i<1000; i++) {
            names.add(r.nextInt(10000) + "医医医医医医医医");
            values.add(r.nextInt(100) / 100d);
        }
        chart.setData(names, values);
    }
}
