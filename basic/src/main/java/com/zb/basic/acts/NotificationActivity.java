package com.zb.basic.acts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zb.basic.R;

import java.util.Random;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b1, b2;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

//        manager = (NotificationManagerCompat) getSystemService(NOTIFICATION_SERVICE);



//        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        /**
//         * 8.0加入channel概念
//         */
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
//            channel = new NotificationChannel("zb", "测试通知",
//                    NotificationManager.IMPORTANCE_HIGH);
//            manager.createNotificationChannel(channel);
//        }
//        Intent intent = new Intent(getApplicationContext(), ImageViewActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_ONE_SHOT);
//        notiy = new Notification.Builder(getApplicationContext(), "zb")
//                .setContentText("我是标题")
//                .setContentText("我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容")
//                .setSmallIcon(R.mipmap.s7)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.compose))
//                .setTicker("abc")//首次收到的时候，在状态栏中，图标的右侧显示的文字
//                .setDefaults(Notification.DEFAULT_ALL)//打开呼吸灯，声音，震动，触发系统默认行为
//               .setFullScreenIntent(pendingIntent, true)
////                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
//                .setPriority(Notification.PRIORITY_HIGH)
//                .addAction(new Notification.Action(R.mipmap.ic_launcher, "朕知道了", pendingIntent))
//                .build();

        Intent intent = new Intent(getApplicationContext(), ImageViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 100, intent, PendingIntent.FLAG_ONE_SHOT);

        manager  = NotificationManagerCompat.from(this);
        channel = new NotificationChannelCompat.Builder("Basic", NotificationManagerCompat.IMPORTANCE_HIGH)
                .setName("Basic")
                .setImportance(NotificationManagerCompat.IMPORTANCE_HIGH)
                .build();
        manager.createNotificationChannel(channel);
        notiy = new NotificationCompat.Builder(getApplicationContext(), channel.getId())
                .setContentText("我是标题")
                .setContentText("我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容我是一个更的观点超长内容")
                .setSmallIcon(R.mipmap.s7)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.compose))
                .setTicker("abc")//首次收到的时候，在状态栏中，图标的右侧显示的文字
                .setDefaults(Notification.DEFAULT_ALL)//打开呼吸灯，声音，震动，触发系统默认行为
               .setFullScreenIntent(pendingIntent, true)
//                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .addAction(new NotificationCompat.Action(R.mipmap.ic_launcher, "朕知道了", pendingIntent))
                .build();



    }
    private Notification notiy;
    private NotificationManagerCompat manager;
    private NotificationChannelCompat channel;

//    private NotificationManager manager;
//    private NotificationChannel channel;
//    private Notification notiy;
    void createNotification() {
        manager.notify(new Random().nextInt(), notiy);
    }
    void dismissNotification() {
        manager.cancel(1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                createNotification();
                break;
            case R.id.button2:
                dismissNotification();
                break;
        }
    }
}