package com.zb.review.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.zb.review.utils.P;


/**
 * 扩展 IntentService 类
 * 由于大多数启动服务无需同时处理多个请求（实际上，这种多线程情况可能很危险），因此最佳选择是利用 IntentService 类实现服务。
 *
 * IntentService 类会执行以下操作：
 *
 * 创建默认的工作线程，用于在应用的主线程外执行传递给 onStartCommand() 的所有 Intent。
 * 创建工作队列，用于将 Intent 逐一传递给 onHandleIntent() 实现，这样您就永远不必担心多线程问题。
 * 在处理完所有启动请求后停止服务，因此您永远不必调用 stopSelf()。
 * 提供 onBind() 的默认实现（返回 null）。
 * 提供 onStartCommand() 的默认实现，可将 Intent 依次发送到工作队列和 onHandleIntent() 实现。
 * 如要完成客户端提供的工作，请实现 onHandleIntent()。不过，您还需为服务提供小型构造函数。
 */

public class ReviewIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ReviewIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        P.p("onHandleIntent ---");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
