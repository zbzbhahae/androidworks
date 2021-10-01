package com.zb.review.dagger;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zb.review.App;
import com.zb.review.R;
import com.zb.review.dagger.bean.Person;
import com.zb.review.dagger.bean.User;

import javax.inject.Inject;

public class ThirdActivity extends AppCompatActivity {

    @Inject
    User u1;
    @Inject
    Person p1;
    @Inject
    Person p2;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        App.getComponent().personFactoryProvider().create().inject(this);
        P.p(u1.info + "  " + u1);
        P.p(p1 + "");
        P.p(p2 + "");
    }
}
