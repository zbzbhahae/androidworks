package com.zb.roomdetail;

import android.app.Application;

import com.zb.roomdetail.utils.P;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        P.p("Application onCreate");
    }
}
