package com.zb.basic.acts.constraint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.zb.basic.R;
import com.zb.basic.acts.BaseActivity;

public class ConstraintSetActivity extends BaseActivity {

    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_set);


        view = findViewById(R.id.constraint_view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startConstraintSet();
                modifyLayoutParams();
            }
        });
    }

    private void startConstraintSet() {
        ConstraintSet cs = new ConstraintSet();
        cs.clone((ConstraintLayout) view.getParent());
        cs.setRotation(R.id.constraint_view, 45.1f);
        cs.setAlpha(R.id.constraint_view, 0.5f);
        cs.setMargin(R.id.constraint_view, 3, 300);
        TransitionManager.beginDelayedTransition((ViewGroup) view.getParent());
        cs.applyTo((ConstraintLayout) view.getParent());

    }

    private void modifyLayoutParams() {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        TransitionManager.beginDelayedTransition((ViewGroup) view.getParent());
        params.topMargin = 300;
        view.setLayoutParams(params);
        view.setAlpha(0.5f);
        view.setRotation(45.1f);
    }
}