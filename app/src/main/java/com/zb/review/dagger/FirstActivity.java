package com.zb.review.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zb.review.App;
import com.zb.review.R;
import com.zb.review.dagger.bean.User;
import com.zb.review.dagger.component.AppComponent;
import com.zb.review.dagger.component.DaggerAppComponent;
import com.zb.review.dagger.interfaces.InterfaceA;

import javax.inject.Inject;
import javax.inject.Named;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    @Named("user2")
    @Inject
    User u1;
    @Named("user1")
    @Inject
    User u2;
    @Named("user2")
    @Inject
    User u3;
    @Named("user1")
    @Inject
    User u4;
    @Inject
    InterfaceA impl;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        AppComponent ac = DaggerAppComponent.create();
        ac.inject(this);
        P.p("1->" + App.getComponent() + "   2->" + ac);
        P.p("1->" + u1 + "   2->" + u2);
        P.p("1->" + u1.info + "   2->" + u2.info);
        App.getComponent().inject(this);
        P.p("1->" + u3 + "   2->" + u4);
        P.p("" + impl);

        btn = findViewById(R.id.dagger_button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }
}