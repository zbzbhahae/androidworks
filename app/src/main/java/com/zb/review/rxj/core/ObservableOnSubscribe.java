package com.zb.review.rxj.core;

public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter);
}
