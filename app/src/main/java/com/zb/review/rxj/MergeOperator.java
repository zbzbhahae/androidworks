package com.zb.review.rxj;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 组合操作符
 */
public class MergeOperator {

    public static void main(String[] args) {
        MergeOperator mergeOperator = new MergeOperator();
        mergeOperator.testConcat();
    }

    /**
     * concat 将多个(最多4个)observable产生的事件打包组合
     * concatArray比concat能传入更多的observable
     * 被观察者中的complete事件会被打断,并且放在最后
     * 抛出异常能打断整个链条
     * 使用concatArrayDelayError可以防止中断并且将error放到最后
     *
     * merge操作符与concat相似 不过concat是串行， merge是并行发送
     * merge方法与concat基本一致
     */
    private void testConcat() {
        Observable.concatArrayDelayError(getObservable(), getObservable(), Observable.just("a"))
                .subscribe(getObserver());
        P.line();
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
                emitter.onError(new Throwable("error"));
                emitter.onComplete();
                //会报错 因为onComplete发送后 观察者已经取消了
//                emitter.onError(new Throwable("a"));
            }
        });
        return observable;
    }
}
