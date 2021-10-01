package com.zb.review.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zb.review.utils.P;

public class ReviewService2 extends Service {

    private WorkHandler handler;

    private final class WorkHandler extends Handler {

        public WorkHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            try {
                P.p("ReviewSerivce2   thread looped,begin to work.  startId is " + msg.arg1);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            P.p("ReviewService2 work completed, ready to stopself. startId is " + msg.arg1);
            //停止service  会根据startId判断，如果startId不等于最新从startOnCommand获得的id 则不会停止。
            //每次通过startService启动服务，startId都会自增一次
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HandlerThread thread = new HandlerThread("Thread args", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        handler = new WorkHandler(thread.getLooper());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        P.p("ReviewService onDestory");
    }

    /**
     * 如果服务同时处理多个对 onStartCommand() 的请求，
     * 则您不应在处理完一个启动请求之后停止服务，因为您
     * 可能已收到新的启动请求（在第一个请求结束时停止服务
     * 会终止第二个请求）。为避免此问题，您可以使用 stopSelf(int)
     * 确保服务停止请求始终基于最近的启动请求。换言之，在调用
     * stopSelf(int) 时，您需传递与停止请求 ID 相对应的启动请求 ID
     * （传递给 onStartCommand() 的 startId）。此外，如果服务在
     * 您能够调用 stopSelf(int) 之前收到新启动请求，则 ID 不匹配，
     * 服务也不会停止。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg = handler.obtainMessage();
        msg.arg1 = startId;
        handler.sendMessage(msg);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
