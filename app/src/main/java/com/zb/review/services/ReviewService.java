package com.zb.review.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.zb.review.utils.P;
import com.zb.review.mvp.bean.ServiceWorkModel;

import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * 绑定服务是客户端-服务器接口中的服务器。借助绑定
 * 服务，组件（例如 Activity）可以绑定到服务、发送
 * 请求、接收响应，以及执行进程间通信 (IPC)。绑定
 * 服务通常只在为其他应用组件提供服务时处于活动
 * 状态，不会无限期在后台运行。
 */
public class ReviewService extends Service {

    private ConcurrentLinkedDeque<Object> deque;
    private Thread workThread;
    private Boolean THREAD_WORKING_MARK = true;
    private IBinder binder = new LocalBinder();
    private int idelCount = 0, workCount = 0;




    /**
     * 首次创建服务时，系统会（在调用 onStartCommand()
     * 或 onBind() 之前）调用此方法来执行一次性设置程序。
     * 如果服务已在运行，则不会调用此方法
     */
    @Override
    public void onCreate() {
        super.onCreate();
        P.p("ReviceService service onCreate");
        deque = new ConcurrentLinkedDeque<>();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                while(THREAD_WORKING_MARK) {
                    P.p("ReviceService looped~");
                    if(null != deque && deque.size() > 0) {
                        Object work = deque.pop();
                        if(work instanceof ServiceWorkModel) {
                            if(((ServiceWorkModel) work).getState() == ServiceWorkModel.STATE_QUITE) {
                                P.p("info from ReviewService: recevice quite signal");
                                break;
                            } else {
                                workCount++;
                                P.p("info from ReviewService: get a work to do, work count is : " + workCount);
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        try {
                            Thread.sleep(5000);
                            idelCount++;
                            P.p("ReviewService : work thread has no works to do! idel loop times : " + idelCount);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                stopSelf();
            }
        };
        workThread = new Thread(runnable);
        workThread.start();
    }

    public void addWorks(Object o) {
            deque.add(o);
        P.p("ReviewService->LocalBinder->addWorks()");
    }

    /**
     * 自定义一个binder 这样在绑定返回binder时可以实用自定义中的接口
     */
    public class LocalBinder extends Binder {
        /**
         * 自定义binder提供一个返回service实例的方法，
         * activity就可以通过实例来访问service中的公共方法
         * @return
         */
        public ReviewService getService() {
            return ReviewService.this;
        }
    }


    @Override
    public void onDestroy() {
        THREAD_WORKING_MARK = false;
        super.onDestroy();
        P.p("ReviceService service onDestory");
    }

    /**
     * 当另一个组件想要与服务绑定（例如执行 RPC）时，系统
     * 会通过调用 bindService() 来调用此方法。在此方法的实现
     * 中，您必须通过返回 IBinder 提供一个接口，以供客户端
     * 用来与服务进行通信。请务必实现此方法；但是，如果您
     * 并不希望允许绑定，则应返回 null
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * 当另一个组件（如 Activity）请求启动服务时，系统会
     * 通过调用 startService() 来调用此方法。执行此方法时，
     * 服务即会启动并可在后台无限期运行。如果您实现此
     * 方法，则在服务工作完成后，您需负责通过调用 stopSelf()
     * 或 stopService() 来停止服务。（如果您只想提供绑定，则
     * 无需实现此方法。）
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        P.p("ReviceService onStartCommand!");
        return super.onStartCommand(intent, flags, startId);
    }
}
