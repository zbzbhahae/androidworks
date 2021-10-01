package com.zb.review.rxj.core;

/**
 * 被观察者的顶层接口
 */
public interface ObservableSource<T> {

    void subscribe(Observer<T> observer);
}
