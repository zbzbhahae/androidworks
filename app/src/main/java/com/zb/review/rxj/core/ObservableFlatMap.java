package com.zb.review.rxj.core;

public class ObservableFlatMap<T, R> extends AbstractObservableWithUpStream<T, R>{

    Function1<T, ObservableSource<R>> function1;

    protected ObservableFlatMap(ObservableSource<T> source, Function1<T, ObservableSource<R>> function1) {
        super(source);
        this.function1 = function1;
    }

    @Override
    protected void subscribeActual(Observer<R> observer) {

        source.subscribe(new MergeObserver<>(observer, function1));
    }

    static class MergeObserver<T, R> implements Observer<T> {

        final Observer<R> downStream;

        final Function1<T, ObservableSource<R>> mapper;

        MergeObserver(Observer<R> downStream, Function1<T, ObservableSource<R>> mapper) {
            this.downStream = downStream;
            this.mapper = mapper;
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            ObservableSource<R> observableSource = mapper.apply(t);
            observableSource.subscribe(new Observer<R>() {
                @Override
                public void onSubscribe() {

                }

                @Override
                public void onNext(R r) {
                    downStream.onNext(r);
                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
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
