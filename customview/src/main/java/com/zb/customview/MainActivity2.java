package com.zb.customview;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.zb.common.utils.P;
import com.zb.customview.widgets.LetterSideBar;

public class MainActivity2 extends AppCompatActivity {
    private LetterSideBar sideBar;
    private TextView letterTxt;
//    RecyclerView
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sideBar = findViewById(R.id.letterSideBar);
        letterTxt = findViewById(R.id.letterText);

        getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        sideBar.setOnLetterSelectedListener(new LetterSideBar.OnLetterSelectedListener() {
            @Override
            public void onSelected(String letter) {
                if(TextUtils.isEmpty(letter)) {
                    P.p("取消选中");
                    letterTxt.setVisibility(View.GONE);
                } else {
                    P.p("选中" + letter);
                    letterTxt.setText(letter);
                    letterTxt.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
