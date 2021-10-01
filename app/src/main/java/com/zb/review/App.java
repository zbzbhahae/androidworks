package com.zb.review;

import android.app.Application;
import android.os.Handler;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatDelegate;

import com.tencent.mmkv.MMKV;
import com.zb.review.dagger.component.AppComponent;
import com.zb.review.dagger.component.DaggerAppComponent;
import com.zb.review.utils.P;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;


public class App extends Application {

    private static AppComponent appComponent = DaggerAppComponent.create();

    public static AppComponent getComponent() {
        return appComponent;
    }

    static App instance;

    public static App getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = App.this;
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED);
        MMKV.initialize(this.getApplicationContext());
        MMKV.defaultMMKV();

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                if(null != throwable)
                    P.p("APP->setErrorHandler->" + throwable.toString());
            }
        });
    }
}
