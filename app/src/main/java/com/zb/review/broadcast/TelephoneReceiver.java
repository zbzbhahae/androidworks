package com.zb.review.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zb.review.utils.P;

public class TelephoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        P.p("接收到系统来电信息了");
    }
}
