package com.zb.review.rxj;

import com.zb.review.utils.P;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class RxBus {

    private final Subject<Object> mBus;

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }

    private RxBus(){
        //除了onSubscribe onNext onError onComplete 支持线程安全
        //toSerialized让上面的方法也支持线程安全
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus get() {
        return Holder.BUS;
    }

    public void post(Object event) {
        P.p("发射了一个事件");
        mBus.onNext(event);
    }

    public @NonNull <T> Observable<T> toObservable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

}
