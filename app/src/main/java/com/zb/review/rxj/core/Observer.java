package com.zb.review.rxj.core;

public interface Observer<T> {

    void onSubscribe();
    void onNext(T t);
    void onComplete();
    void onError(Throwable throwable);
}
