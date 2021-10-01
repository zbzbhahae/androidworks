package com.zb.aidl2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.zb.aidl2.utils.P;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);


        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.zb.aidl",
                "com.zb.aidl.services.ServiceInOtherProcess"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                createOne();
                Window a;
                break;
            case R.id.button2:
                stopOne();
                break;
        }
    }

    private void stopOne() {
        P.line();
        P.p("stopOne");
        stopService(serviceIntent);
    }

    private void createOne() {
        P.line();
        P.p("createOne");
        startService(serviceIntent);
    }
}