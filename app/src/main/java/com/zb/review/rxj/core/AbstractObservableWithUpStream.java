package com.zb.review.rxj.core;

public abstract class AbstractObservableWithUpStream<T, R> extends Observable<R>{

    protected final ObservableSource<T> source;


    protected AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }
}
