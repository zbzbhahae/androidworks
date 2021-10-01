package com.zb.hiltapp.acts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mmkv.MMKV;
import com.zb.hiltapp.P;
import com.zb.hiltapp.R;
import com.zb.hiltapp.bean.Student;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint //activity需要是ComponentActivity的子类
public class HiltActivity extends BaseActivity implements View.OnClickListener {

    @Inject
    Student a;
    @Inject
    Student b;
    @Inject
    MMKV mmkv;

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt);

        P.p("a->" + a + "  =  " + a.info);
        P.p("b->" + b + "  =  " + b.info + "  context is " + b.getContext());
        P.p(" " + rootStu.info + "  " + rootStu);
        P.p("mmkv->" + mmkv.toString() + "   ---" + mmkv);

        mmkv.putString("key", "value");

        P.p("mmkv value is : " + mmkv.getString("key", ""));

        button = findViewById(R.id.hilt_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, HiltActivity2.class);
        startActivity(i);
    }
}
