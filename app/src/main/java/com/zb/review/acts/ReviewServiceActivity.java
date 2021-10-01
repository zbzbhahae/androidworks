package com.zb.review.acts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.zb.review.R;
import com.zb.review.mvp.bean.ServiceWorkModel;
import com.zb.review.services.ReviewService;
import com.zb.review.services.ReviewService2;

public class ReviewServiceActivity extends BaseActivity implements View.OnClickListener {

    private Button start, addWork, stop;
    private ReviewService service;
    private ReviewService.LocalBinder iBinder;
    private ServiceConnection conn;



    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_service);
        start = findViewById(R.id.review_service_start);
        addWork = findViewById(R.id.review_service_add_work);
        stop = findViewById(R.id.review_service_stop);

        start.setOnClickListener(this);
        addWork.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.review_service_start:
                doStart();
                break;
            case R.id.review_service_add_work:
                doAddWork();
                break;
            case R.id.review_service_stop:
                doStop();
                break;
        }
    }

    private void doStart() {
//        startTypeService();
        bindTypeService();
    }

    private void startTypeService() {
        Intent intent = new Intent(this, ReviewService2.class);
        startService(intent);
    }

    /**
     * 使用binder的场景：无序跨进程通信，如需跨进程(IPC)，则需要使用Messenger(底层实现为AIDL)或AIDL
     * bind其他进程的service会出错，返回的IBinder是经过系统包装的BinderProxy
     */
    private boolean SERVER_BINDED = false;
    private void bindTypeService() {
        if(SERVER_BINDED)
            return;
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                SERVER_BINDED = true;
                iBinder = (ReviewService.LocalBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                SERVER_BINDED = false;
                iBinder = null;
            }
        };
        Intent intent = new Intent(this, ReviewService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private void doAddWork() {
        if(null != iBinder)
            iBinder.getService().addWorks(new ServiceWorkModel());
    }

    private void doStop() {
        sendStopSignal();
        if(null != conn)
            unbindService(conn);
    }

    private void sendStopSignal() {
        if(null != iBinder) {
            iBinder.getService().addWorks(new ServiceWorkModel(ServiceWorkModel.STATE_QUITE));
        }
    }
}
