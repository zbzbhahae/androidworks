package com.zb.basic.acts;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zb.basic.mvp.presenter.AbsPresenter;
import com.zb.common.utils.P;

public class BaseActivity<T extends AbsPresenter> extends AppCompatActivity {

    protected T presenter;

    public void performActivityIntent(Intent intent) {
        startActivity(intent);
    }







    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        P.p(this.getClass().getSimpleName() + "::onCreate");
    }
    @Override
    protected void onStart() {
        super.onStart();
        P.p(this.getLocalClassName() + "::onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        P.p(this.getLocalClassName() + "::onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        P.p(this.getLocalClassName() + "::onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        P.p(this.getLocalClassName() + "::onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        P.p(this.getLocalClassName() + "::onDestroy");
    }
}
