package com.zb.basic.acts.okhttp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.basic.R;
import com.zb.basic.acts.BaseActivity;
import com.zb.common.http.OKHttpUtil;

public class OkHttpActivity extends BaseActivity implements View.OnClickListener {

    private TextView textView;
    private Button btn1, btn2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_button);

        textView = findViewById(R.id.text);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                click1();
                break;
            case R.id.button2:
                click2();
                break;
        }
    }

    private void click1() {
//        OKHttpUtil.get("https://tenapi.cn/lishi/", OkHttpActivity.this, null);
        OKHttpUtil.get("https://tenapi.cn/lishi/", OkHttpActivity.this, "format", "json");
    }
    private void click2() {

    }
}
