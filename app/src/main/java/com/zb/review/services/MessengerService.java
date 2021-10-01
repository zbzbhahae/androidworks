package com.zb.review.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zb.review.utils.P;
import com.zb.review.mvp.bean.serialize.ParcelableServiceModel;


/**
 * 如果您需要让服务与远程进程通信，则可使用 Messenger 为您的服务提供接口。借助此方式，您无需使用 AIDL 便可执行进程间通信 (IPC)。
 *
 * 为接口使用 Messenger 比使用 AIDL 更简单，因为 Messenger 会将所有服务调用加入队列。纯 AIDL 接口会同时向服务发送多个请求，那么服务就必须执行多线程处理。
 *
 * 对于大多数应用，服务无需执行多线程处理，因此使用 Messenger 可让服务一次处理一个调用。如果您的服务必须执行多线程处理，请使用 AIDL 来定义接口。
 *
 * 以下是对 Messenger 使用方式的摘要：
 *
 * 1 服务实现一个 Handler，由其接收来自客户端的每个调用的回调。
 * 2 服务使用 Handler 来创建 Messenger 对象（该对象是对 Handler 的引用）。
 * 3 Messenger 创建一个 IBinder，服务通过 onBind() 将其返回给客户端。
 * 4 客户端使用 IBinder 将 Messenger（它引用服务的 Handler）实例化，然后再用其将 Message 对象发送给服务。
 * 5 服务在其 Handler 中（具体而言，是在 handleMessage() 方法中）接收每个 Message。
 * 这样，客户端便没有调用服务的方法。相反，客户端会传递服务在其 Handler 中接收的消息（Message 对象）。
 */
public class MessengerService extends Service {
    public static final int MSG_TYPE_1 = 1;
    /**
     * 第一步 服务实现一个handler
     */
    static class MessengerHander extends Handler {
        private Context appContext;

        public MessengerHander(Context context) {
            this.appContext = context;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            P.p("MessengerService->MessengerHandler->handleMessage");
            switch (msg.what) {
                case MSG_TYPE_1:
                    Bundle b = (Bundle) msg.getData();
                    b.setClassLoader(getClass().getClassLoader());
                    ParcelableServiceModel model = b.getParcelable("model");
                    P.p("MessengerService received a message type 1 \nand content is : " + model.getMsg());
                    break;
                default:
                    P.p("MessengerService ->handleMessage goes to default");
                    super.handleMessage(msg);
            }
        }
    }

    Messenger messenger;

    @Override
    public void onCreate() {
        P.p("MessengerService -> onCreate");
        super.onCreate();
        /** 虽然在不同进程 但是还是需要新开线程去做耗时事务，
         * 虽然app主进程不卡顿，但后续代码无法运行 */
//        ((Runnable) () -> {
//            while (true) {
//                //doNothing
//            }
//        }).run();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        P.p("MessengerService -> onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        /**
         * 第二步 使用实现的handler构建messenger
         */
        messenger = new Messenger(new MessengerHander(this));
        return messenger.getBinder();
    }
}
