package com.zb.review.rxj.core.scheduler;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Schedulers {

    private static Scheduler MAIN_THREAD = null;
    private static Scheduler NEW_THREAD = null;

    static {
        MAIN_THREAD = new HandlerScheduler(new Handler(Looper.getMainLooper()));
        NEW_THREAD = new NewThreadScheduler(Executors.newScheduledThreadPool(2));
    }

    public static Scheduler mainThread() {
        return MAIN_THREAD;
    }

    public static Scheduler newThread() {
        return NEW_THREAD;
    }
}
