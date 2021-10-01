package com.zb.review.rxj.core;

import com.zb.review.rxj.core.scheduler.Scheduler;

public class ObservableSubscribeOn<T> extends AbstractObservableWithUpStream<T, T> {

    final Scheduler scheduler;

    protected ObservableSubscribeOn(ObservableSource source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        observer.onSubscribe();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(new SubscribeTask(new SubsribeOnObserver<>(observer)));
    }

    class SubsribeOnObserver<T> implements Observer<T> {
        final Observer<T> downStream;

        SubsribeOnObserver(Observer<T> downStream) {
            this.downStream = downStream;
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            downStream.onNext(t);
        }

        @Override
        public void onComplete() {
            downStream.onComplete();
        }

        @Override
        public void onError(Throwable throwable) {
            downStream.onError(throwable);
        }
    }

    final class SubscribeTask implements Runnable {

        private final SubsribeOnObserver<T> parent;

        SubscribeTask(SubsribeOnObserver<T> observable) {
            this.parent = observable;
        }

        @Override
        public void run() {
            source.subscribe(parent);
        }
    }
}
