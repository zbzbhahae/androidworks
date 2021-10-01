package com.zb.review.rxj.core.scheduler;


import android.os.Handler;

public class HandlerScheduler extends Scheduler {

    final Handler handler;

    public HandlerScheduler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(handler);
    }

    static final class HandlerWorker implements Worker {

        final Handler mapper;

        HandlerWorker(Handler mapper) {
            this.mapper = mapper;
        }

        @Override
        public void schedule(Runnable runnable) {
            mapper.post(runnable);
        }
    }
}
