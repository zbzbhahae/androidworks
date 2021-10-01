package com.zb.aidl.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;

import com.zb.aidl.utils.P;
import com.zb.aidl.utils.ProcessUtils;

public class ServiceInOtherProcess extends Service {
    public ServiceInOtherProcess() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        P.p("service onCreate Pid" + ProcessUtils.pid());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        P.p("service onDestroy Pid" + ProcessUtils.pid());
    }
}