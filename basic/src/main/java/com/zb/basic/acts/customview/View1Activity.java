package com.zb.basic.acts.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.zb.basic.R;

public class View1Activity extends AppCompatActivity {

    private ViewGroup root;
//    FrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);

        //Activity的根View
        root = findViewById(android.R.id.content);
//        root = findViewById(R.id.content);
        root.setBackgroundColor(0xffff0000);
    }
}