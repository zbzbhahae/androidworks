package com.zb.basic.acts.surface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;

import com.zb.basic.R;

public class SurfaceActivity extends AppCompatActivity {

    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);

        surfaceView = findViewById(R.id.surface);

//        surfaceView.setBackgroundColor(0xffff0000);
    }
}