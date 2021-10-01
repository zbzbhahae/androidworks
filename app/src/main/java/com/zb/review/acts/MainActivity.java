package com.zb.review.acts;



import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.zb.review.acts.camera.TakePictureActivity;
import com.zb.review.acts.data.FileCacheActivity;
import com.zb.review.acts.data.GreendaoActivity;
import com.zb.review.acts.data.RoomDatabaseActivity;
import com.zb.review.acts.data.SqliteActivity;
import com.zb.review.acts.fragment.TabFragmentActivity;
import com.zb.review.acts.network.GlideActivity;
import com.zb.review.acts.network.HttpActivity;
import com.zb.review.acts.network.RxJavaActivity;
import com.zb.review.acts.network.okhttp.OkhttpActivity;
import com.zb.review.acts.network.retrofit.RetrofitActivity;
import com.zb.review.broadcast.TelephoneReceiver;
import com.zb.review.dagger.FirstActivity;
import com.zb.review.rxj.RxBus;
import com.zb.review.rxj.RxJavaActivity2;
import com.zb.review.utils.DeviceUtils;
import com.zb.review.utils.P;
import com.zb.review.R;
import com.zb.review.adapter.MainRecyclerViewAdapter;
import com.zb.review.mvp.bean.MainRecycleModel;
import com.zb.review.utils.T;

import java.util.ArrayList;
import java.util.List;


/**
 * Activity A 启动另一个Activity B，回调如下:
 * Activity A 的onPause() → Activity B的onCreate()
 * → onStart() → onResume() → Activity A的onStop()；
 * 如果B是透明主题又或则是个DialogActivity，则不会回调A的onStop；
 *
 *
 * LaunchMode	        说明
 * standard	            系统在启动它的任务中创建 activity 的新实例
 * singleTop	            如果activity的实例已存在于当前任务的顶部，则
 *                              系统通过调用其onNewIntent()，否则会创建新实例
 * singleTask	            系统创建新 task 并在 task 的根目录下实例化 activity。
 *                               但如果 activity 的实例已存在于单独的任务中，则调用
 *                               其 onNewIntent() 方法，其上面的实例会被移除栈。
 *                               一次只能存在一个 activity 实例
 * singleInstance	    相同 singleTask，activity始终是其task的唯一成员; 任何
 *                               由此开始的activity 都在一个单独的 task 中打开
 */

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<MainRecycleModel> data = new ArrayList<>();
    private MainRecyclerViewAdapter adapter ;
    private Context mContext;
    TelephoneReceiver r;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != r)
            unregisterReceiver(r);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;


        getDeviceInfo();
        requestPermission();

//        r = new TelephoneReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("com.zb.review.custom");
//        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
//        registerReceiver(r, intentFilter);



        Handler h = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                RxBus.get().post("abc");
            }
        }, 5000);


        initData();
        adapter = new MainRecyclerViewAdapter(data);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnImteClickedListener(new MainRecyclerViewAdapter.OnMainItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                P.p("OnMainItemClicked at position " + position);
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(mContext, ReviewFragmentActivity.class);
                        break;
                    case 1:
                        intent = new Intent(mContext, ReviewServiceActivity.class);
                        break;
                    case 2:
                        intent = new Intent(mContext, ReviewServiceMessengerActivity.class);
                        break;
                    case 3:
                        intent = new Intent(mContext, SqliteActivity.class);
                        break;
                    case 4:
                        intent = new Intent(mContext, FileCacheActivity.class);
                        break;
                    case 5:
                        intent = new Intent(mContext, RoomDatabaseActivity.class);
                        break;
                    case 6:
                        intent = new Intent(mContext, GreendaoActivity.class);
                        break;
                    case 7:
                        //使用系统自带Android Sharesheet 分享网址
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, "http://www.baidu.com");
                        intent.setType("text/html");
                        break;
                    case 8:
                        intent = new Intent(Intent.ACTION_VIEW);
                        Uri uri = Uri.parse("http://www.baidu.com");
                        intent.setData(uri);
                        break;
                    case 9:
                        intent = new Intent(mContext, TabFragmentActivity.class);
                        break;
                    case 10:
                        intent = new Intent(mContext, HttpActivity.class);
                        break;
                    case 11:
                        intent = new Intent(mContext, OkhttpActivity.class);
                        break;
                    case 12:
                        intent = new Intent(mContext, RxJavaActivity.class);
                        break;
                    case 13:
                        intent = new Intent(mContext, RetrofitActivity.class);
                        break;
                    case 14:
                        intent = new Intent(mContext, GlideActivity.class);
                        break;
                    case 15:
                        intent = new Intent(mContext, CustomViewActivity.class);
                        break;
                    case 16:
                        intent = new Intent(mContext, AnimatorActivity.class);
                        break;
                    case 17:
                        intent = new Intent(mContext, TouchActivity.class);
                        break;
                    case 18:
                        intent = new Intent(mContext, ViewpagerActivity.class);
                        break;
                    case 19:
                        intent = new Intent(mContext, TakePictureActivity.class);
                        break;
                    case 20:
                        intent = new Intent(mContext, MVPActivity.class);
                        break;
                    case 21:
                        intent = new Intent(mContext, PopupActivity.class);
                        break;
                    case 22:
                        intent = new Intent(mContext, FirstActivity.class);
                        break;
                    case 23:
                        intent = new Intent(mContext, PhotoViewActivity.class);
                        break;
                    case 24:
                        intent = new Intent(mContext, CardViewActivity.class);
                        break;
                    case 25:
                        intent = new Intent(mContext, RxJavaActivity2.class);
                        break;
                    case 26:
                        intent = new Intent(mContext, ActionBarActivity.class);
                        break;
                    case 27:
                        intent = new Intent(mContext, WebviewInteractiveActivity.class);
                        break;
                    default:
                        intent = null;
                        break;
                }
                if(null != intent)
                    startActivity(intent);

            }

            @Override
            public void onTiemLongClick(int position) {
                P.p("OnMainItemLongClick at position " + position);
//                data.remove(position);
            }
        });

        recyclerView.scrollToPosition(14);
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        P.p("ACTION_DOWN");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        P.p("ACTION_MOVE");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        P.p("ACTION_UP");
//                        break;
//                    default:
//                        P.p(" default: " + event.getAction());
//                        break;
//                }
//                return false;
//            }
//        });
    }

    /**
     * 申请权限
     */
    void requestPermission() {
        int permission = ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_PHONE_STATE");

        switch (permission) {
            case PackageManager
                    .PERMISSION_DENIED:
                P.p("未获得权限");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                break;
            case PackageManager.PERMISSION_GRANTED:
                P.p("已获得权限");
                break;
        }
    }
    /**
     * 需要权限
     */
    void listenToPhoneState() {
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                P.p("onCallStateChanged:" + phoneNumber);
            }
        };
        TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        if(null != mgr) {
            P.p("TelephonyManager is not null");
            mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        } else {
            P.p("TelephonyManager is null");
        }
    }

    void getDeviceInfo() {
        P.p("getSerialNumber : " + DeviceUtils.getSerialNumber());
        P.p("getDeviceID : " + DeviceUtils.getDeviceID(this));
        P.p("createUUID : " + DeviceUtils.createUUID());
    }

    private void initData() {
        data.add(new MainRecycleModel("fragment页面"));
        data.add(new MainRecycleModel("服务的启动"));
        data.add(new MainRecycleModel("MESSAGE跨进程服务通信"));
        data.add(new MainRecycleModel("SQLite数据库"));
        data.add(new MainRecycleModel("文件存储"));
        data.add(new MainRecycleModel("ROOM数据库"));
        data.add(new MainRecycleModel("GreenDao数据库"));
        data.add(new MainRecycleModel("Android Sharesheet 分享网址"));
        data.add(new MainRecycleModel("使用外部浏览器intent"));
        data.add(new MainRecycleModel("底部导航tabfragment"));
        data.add(new MainRecycleModel("HttpUrlConnection访问网络"));
        data.add(new MainRecycleModel("使用Okhttp"));
        data.add(new MainRecycleModel("RxJava"));
        data.add(new MainRecycleModel("Retrofit"));
        data.add(new MainRecycleModel("Glide"));
        data.add(new MainRecycleModel("自定义View"));
        data.add(new MainRecycleModel("动画"));
        data.add(new MainRecycleModel("touch事件传递"));
        data.add(new MainRecycleModel("ViewPager2"));
        data.add(new MainRecycleModel("拍照"));
        data.add(new MainRecycleModel("MVP模式"));
        data.add(new MainRecycleModel("popup弹出"));
        data.add(new MainRecycleModel("Dagger2的使用"));
        data.add(new MainRecycleModel("photoView库"));
        data.add(new MainRecycleModel("CardView使用"));
        data.add(new MainRecycleModel("测试RxJava内存泄露"));
        data.add(new MainRecycleModel("ActionBar"));
        data.add(new MainRecycleModel("Webview交互"));
        for(int i=0; i<7; i++) {
            data.add(new MainRecycleModel("生命周期"));
        }
    }

    long backTime = 0;
    @Override
    public void onBackPressed() {
        if(backTime != 0 && System.nanoTime() - backTime > 2000) {
            super.onBackPressed();
        } else {
            backTime = System.nanoTime();
            T.t(this, "再点击一次返回退出应用");
        }

    }
}