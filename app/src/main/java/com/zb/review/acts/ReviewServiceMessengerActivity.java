package com.zb.review.acts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.zb.review.utils.P;
import com.zb.review.R;
import com.zb.review.mvp.bean.serialize.ParcelableServiceModel;
import com.zb.review.services.MessengerService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReviewServiceMessengerActivity extends BaseActivity implements View.OnClickListener {
    private Button start, addWork, stop;

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

    boolean SERVER_BIND = false;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            P.p("MessengerServer bind success!");
            messenger = new Messenger(service);
            SERVER_BIND = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            SERVER_BIND = false;
        }
    };

    Messenger messenger;

    private void doStart() {
        if(!SERVER_BIND) {
            Intent intent = new Intent(this, MessengerService.class);
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    private void doAddWork() {
        if(SERVER_BIND && null != messenger) {
            Message msg = Message.obtain(null, MessengerService.MSG_TYPE_1);
            try {
                ParcelableServiceModel obj = new ParcelableServiceModel("", 1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                obj.setMsg("The message is the date now is : " + sdf.format(new Date()));
                Bundle bundle = new Bundle();
                bundle.putParcelable("model", obj);
                msg.setData(bundle);
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void doStop() {
        if(SERVER_BIND) {
            unbindService(conn);
            SERVER_BIND = false;
        }
    }
}
