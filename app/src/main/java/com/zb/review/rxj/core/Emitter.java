package com.zb.review.rxj.core;

/**
 * 事件发射器的接口
 */
public interface Emitter<T> {

    void onNext(T t);
    void onComplete();
    void onError(Throwable throwable);
}
