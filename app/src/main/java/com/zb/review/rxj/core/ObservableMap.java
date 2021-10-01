package com.zb.review.rxj.core;

public class ObservableMap<T, R> extends AbstractObservableWithUpStream<T, R>{

    Function1<T, R> function1;

    protected ObservableMap(ObservableSource<T> source, Function1<T, R> function1) {
        super(source);
        this.function1 = function1;
    }

    @Override
    protected void subscribeActual(Observer<R> observer) {
        source.subscribe(new MapObserver<T, R>(observer, function1));
    }

    static class MapObserver<T, R> implements Observer<T> {

        final Observer<R> downStream;

        final Function1<T, R> mapper;

        MapObserver(Observer<R> downStream, Function1<T, R> mapper) {
            this.downStream = downStream;
            this.mapper = mapper;
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            R r = mapper.apply(t);
            downStream.onNext(r);
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
}
