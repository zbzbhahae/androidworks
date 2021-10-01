package com.zb.review.acts;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.zb.review.mvp.presenter.IBasePresenter;
import com.zb.review.mvp.viewmodel.IBaseView;
import com.zb.review.utils.P;

import org.jetbrains.annotations.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity<T extends IBasePresenter, V extends IBaseView> extends AppCompatActivity {

    protected Unbinder unbinder;
    protected T presenter;
    protected ActionBar actionBar;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        if(null != unbinder)
            unbinder.unbind();
        if(null != presenter)
            presenter.onDettach();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        P.p("onCreate from" + this.getClass().getName());
        actionBar = getSupportActionBar();
        getSupportActionBar().hide();
        presenter = createPresenter();
        if(null != presenter)
            presenter.onAttach((V)this);

    }

    protected T createPresenter() {
        return null;
    }

    /**
     * 使用lifecycle来管理生命周期
     */
    protected void initLifeCycle() {
        if(null != presenter)
            getLifecycle().addObserver(presenter);
    }
//        @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        P.p("onDestroy from" + this.getClass().getName());
//    }

//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        P.p("onStart from" + this.getClass().getName());
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        P.p("onResume from" + this.getClass().getName());
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        P.p("onStop from" + this.getClass().getName());
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        P.p("onRestart from" + this.getClass().getName());
//    }
//
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        P.p("onSaveInstanceState from" + this.getClass().getName());
//    }
//
//    /**
//     * 横竖屏切换时会调用到(切换会重新走onCreate方法)
//     * 只有在Activity真的被系统非正常杀死过，恢复
//     * 显示Activity的时候，才会调用onRestoreInstanceState
//     * @param savedInstanceState
//     */
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        P.p("onRestoreInstanceState from" + this.getClass().getName());
//    }
//

//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        P.p("onPause from" + this.getClass().getName());
//    }
}
