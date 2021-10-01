package com.zb.review.rxj.core;

import com.zb.review.rxj.core.scheduler.Scheduler;

public abstract class Observable<T> implements ObservableSource<T> {
    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate<>(source);
    }

    public <R> ObservableMap<T, R> map(Function1<T, R> function1) {
        return new ObservableMap<>(this, function1);
    }

    public <R> ObservableFlatMap<T, R> flatMap(Function1<T, ObservableSource<R>> function1) {
        return new ObservableFlatMap<>(this, function1);
    }

    public ObservableSubscribeOn<T> subscribeOn(Scheduler scheduler) {
        return new ObservableSubscribeOn<>(this, scheduler);
    }

    public ObservableObserveOn<T> observeOn(Scheduler scheduler) {
        return new ObservableObserveOn<>(this, scheduler);
    }
}
