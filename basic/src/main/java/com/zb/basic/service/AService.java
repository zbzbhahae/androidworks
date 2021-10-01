package com.zb.basic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.zb.common.utils.P;

public class AService extends Service {


    @Override
    public void onCreate() {
        P.p("AService::onCreate()");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        P.p("AService::onStart() 弃用");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        P.p("AService::onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        P.p("AService::onBind()");
        return new Binder();
    }

    @Override
    public void onDestroy() {
        P.p("AService::onDestroy()");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        P.p("AService::onUnbind()");
        return super.onUnbind(intent);
    }
}
