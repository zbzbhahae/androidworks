package com.zb.review.rxj;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 转换类操作符
 */
public class TransOperatorDemo {

    public static void main(String[] args) {
        TransOperatorDemo demo = new TransOperatorDemo();
        demo.testBuffer();
    }

    /**
     * 直接对发射的事件进行处理，并产生新的事件再发射
     */
    private void testMap() {
        getObservable().map(new Function() {
            @Override
            public Object apply(Object o) throws Throwable {
                if(o instanceof String) {
                    o = o + " mapped";
                }
                return o;
            }
        }).subscribe(getObserver());
    }

    /**
     *concatMap与flatmap基本相似 不过concatMap会保持事件有序性
     */
    private void testFlatMap() {
        Observable.just("a", "b", "c", "d").flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Throwable {
                return Observable.just(s);
            }
        }).subscribe(getObserver());

        Observable.just("a", "b", "c", "d").concatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Throwable {
                return Observable.just(s);
            }
        }).subscribe(getObserver());

    }

    /**
     *
     * 输入4个String  产生2个String  list 一个长度为3，另一个长度不够3
     */
    private void testBuffer() {
        Observable.just("a", "b", "c", "d").buffer(3).subscribe(getObserver());
    }




    private Observer getObserver() {
        Observer observer = new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                P.p("onSubscribe");
            }
            @Override
            public void onNext(@NonNull Object o) {
                P.p("onNext->" + o.toString());
            }
            @Override
            public void onError(@NonNull Throwable e) {
                P.p("onError->" + e.getLocalizedMessage());
            }
            @Override
            public void onComplete() {
                P.p("onComplete");
            }
        };
        return observer;
    }
    private Observable getObservable() {
        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onError(new Throwable("Throwable from observable"));
//                emitter.onComplete();
                //会报错 因为onComplete发送后 观察者已经取消了
//                emitter.onError(new Throwable("a"));
            }
        });
        return observable;
    }
}
