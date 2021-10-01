package com.zb.common.acts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zb.common.R;

public class TextButtonActivity extends ZBBaseActivity {

    protected TextView txt;
    protected Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_button);

        initViews();
        initListener();
    }

    protected void onClickBtn1(View button) {
    }

    protected void onClickBtn2(View button) {
    }

    private void initListener() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtn1(v);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtn2(v);
            }
        });
    }

    private void initViews() {
        txt = findViewById(R.id.text);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
    }
}