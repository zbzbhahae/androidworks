package com.zb.basic.acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.zb.basic.R;

public class SnackBarActivity extends AppCompatActivity {

    private Button btn;
    private ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);

        btn = findViewById(R.id.snackbar_button);
        rootLayout = findViewById(R.id.snackbar_layout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSnackBar();
            }
        });
    }

    private void makeSnackBar() {
        Snackbar snackbar = Snackbar.make(rootLayout, "我是信息", Snackbar.LENGTH_SHORT);
        snackbar.setAction("干掉button", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setText("我被干掉了");
            }
        });
        snackbar.setActionTextColor(0xFFFF0000);
        snackbar.show();
    }
}