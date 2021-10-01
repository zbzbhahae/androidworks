package com.zb.basic.acts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.zb.basic.R;
import com.zb.basic.widget.LayoutA;
import com.zb.basic.widget.ViewA;
import com.zb.common.utils.P;

public class ViewTouchActivity extends BaseActivity {

    private LayoutA la;
    private ViewA va;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_touch);

        la = findViewById(R.id.touch_la);
        va = findViewById(R.id.touch_va);

        test();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        P.p("activity::dispatchTouchEvent()");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        P.p("activity::onTouchEvent()");
        return super.onTouchEvent(event);
    }

    private void test() {


//        la.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                P.p("la::setOnClickListener::onClick");
//            }
//        });
//
//        va.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                P.p("va::setOnClickListener::onClick");
//            }
//        });
    }


}