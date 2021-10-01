package com.zb.basic.acts.material;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.snackbar.Snackbar;
import com.zb.basic.R;
import com.zb.basic.acts.BaseActivity;

import android.os.Bundle;
import android.view.View;

public class CardViewActivity extends BaseActivity {

    private CardView cd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        cd1 = findViewById(R.id.card_view_1);
        cd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "cardview点击了", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}