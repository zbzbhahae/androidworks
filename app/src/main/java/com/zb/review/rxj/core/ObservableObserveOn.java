package com.zb.review.rxj.core;

import com.zb.review.rxj.core.scheduler.Scheduler;

import java.util.ArrayDeque;
import java.util.Deque;

public class ObservableObserveOn<T> extends AbstractObservableWithUpStream {

    final Scheduler scheduler;

    protected ObservableObserveOn(ObservableSource source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        Scheduler.Worker worker = scheduler.createWorker();
        source.subscribe(new ObserveOnObserver(observer, worker));
    }

    static final class ObserveOnObserver<T> implements Observer<T>, Runnable {

        final Observer<T> downStream;
        final Scheduler.Worker worker;
        final Deque<T> deque;

        volatile boolean done;
        volatile Throwable error;
        volatile boolean over;

        ObserveOnObserver(Observer<T> downStream, Scheduler.Worker worker) {
            this.downStream = downStream;
            this.worker = worker;
            this.deque = new ArrayDeque<>();
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            deque.offer(t);//offer不会抛异常 只会返回false

            schedule();
        }

        private void schedule() {
            worker.schedule(this);
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void run() {
            drainNormal();
        }

        /**
         * 从队列中排放事件并处理
         */
        private void drainNormal() {
            final Deque<T> q = deque;
            final Observer<T> o = downStream;
            while (true) {
                boolean d = done;
                T t = q.poll();//队列中去除并删除元素
                boolean empty = t == null;
                if(checkTerminated(d, empty, o))
                    break;

                downStream.onNext(t);

            }
        }

        private boolean checkTerminated(boolean d, boolean empty, Observer<T> o) {
            if(over) {
                deque.clear();
                return true;
            }
            if(d) {
                Throwable e = error;
                if(null != e) {
                    over = true;
                    o.onError(e);
                    return true;
                } else if(empty) {
                    over = true;
                    o.onComplete();
                    return true;
                }
            }
            return false;
        }
    }
}
