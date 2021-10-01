package com.zb.review.rxj.core;

public class ObservableCreate<T> extends Observable<T> {

    ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        observer.onSubscribe();
        CreateEmitter<T> emitter = new CreateEmitter<T>(observer);
        source.subscribe(emitter);

    }

    static class CreateEmitter<T> implements  Emitter<T> {
        Observer<T> observer;
        boolean done;

        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            if(done) return;
            observer.onNext(t);
        }

        @Override
        public void onComplete() {
            if(done) return;
            observer.onComplete();
            done = true;
        }

        @Override
        public void onError(Throwable throwable) {
            if(done) return;
            observer.onError(throwable);
            done = true;
        }
    }
}
