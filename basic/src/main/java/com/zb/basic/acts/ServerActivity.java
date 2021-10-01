package com.zb.basic.acts;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.basic.R;
import com.zb.basic.service.AService;
import com.zb.common.utils.P;

public class ServerActivity extends BaseActivity implements View.OnClickListener {


    private TextView txt;
    private Button btn1, btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_button);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        txt = findViewById(R.id.text);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                button1Action();
                break;
            case R.id.button2:
                button2Action();
                break;
        }
    }





    private void button1Action() {
        P.p("text_button::button1");
        Intent i = new Intent(this, AService.class);
//        startService(i);
        bindService(i, sc, BIND_AUTO_CREATE);
    }

    private void button2Action() {
        P.p("text_button::button2");
        if(!bound)
            return;
        Intent i = new Intent(this, AService.class);
//        stopService(i);
        unbindService(sc);
        bound = false;

    }
    boolean bound = false;

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bound = true;
            P.p("bind::onServiceConnected()");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            P.p("bind::onServiceDisconnected");
        }
    };
}
