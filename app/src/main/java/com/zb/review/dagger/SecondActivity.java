package com.zb.review.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zb.review.App;
import com.zb.review.R;
import com.zb.review.dagger.bean.Student;
import com.zb.review.dagger.bean.User;
import com.zb.review.dagger.component.DaggerStuComponent;

import javax.inject.Inject;
import javax.inject.Named;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Named("user1")
    @Inject
    User u1;
    @Inject
    Student s1;
    @Inject
    Student s2;

    Button btn;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        DaggerStuComponent.builder().appComponent(App.getComponent()).build().inject(this);
        P.p(u1 + "");
        P.p(s1 + "");
        P.p(s2 + "");

        btn = findViewById(R.id.dagger_button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, ThirdActivity.class);
        startActivity(i);
    }
}
