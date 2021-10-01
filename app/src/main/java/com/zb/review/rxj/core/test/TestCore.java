package com.zb.review.rxj.core.test;

import com.zb.review.rxj.P;
import com.zb.review.rxj.core.Emitter;
import com.zb.review.rxj.core.Function1;
import com.zb.review.rxj.core.Observable;
import com.zb.review.rxj.core.ObservableOnSubscribe;
import com.zb.review.rxj.core.ObservableSource;
import com.zb.review.rxj.core.Observer;

public class TestCore {

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(Emitter<Object> emitter) {
                emitter.onNext("a");
                emitter.onNext("a");
                emitter.onNext("a");
                emitter.onError(new Throwable("发生错误"));
                emitter.onComplete();
            }
        })
                .map(new Function1<Object, Object>() {
                    @Override
                    public String apply(Object o) {
                        return o.toString() + "  mapped";
                    }
                })
                .flatMap(new Function1<Object, ObservableSource<Object>>() {
                    @Override
                    public ObservableSource<Object> apply(Object o) {
                        return Observable.create(new ObservableOnSubscribe<Object>() {
                            @Override
                            public void subscribe(Emitter emitter) {
                                emitter.onNext("flatmap处理后  " + o.toString());
                            }
                        });
                    }
                })
                .subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe() {
                P.p("onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                P.p("onNext->" + o.toString());
            }

            @Override
            public void onComplete() {
                P.p("onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                P.p("onError->" + throwable.getLocalizedMessage());
            }
        });
    }
}
