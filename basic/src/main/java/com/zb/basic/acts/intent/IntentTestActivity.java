package com.zb.basic.acts.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zb.basic.R;
import com.zb.common.utils.P;
import com.zb.common.utils.T;

public class IntentTestActivity extends AppCompatActivity {


    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);

        btn = findViewById(R.id.intent_button_1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSendIntent();
            }
        });


    }

    private void doSendIntent() {

        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.baidu.com"));

        Intent chooser = Intent.createChooser(i, "浏览");

        ComponentName name = i.resolveActivity(getPackageManager());
//        P.p(name.toString());
//        if(i.resolveActivity(getPackageManager()) != null) {
//            startActivity(i);
//        }
        startActivity(chooser);
    }
}