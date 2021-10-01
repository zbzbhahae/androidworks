package com.zb.review.rxj;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class CreateOperatorDemo {

    static P p = new P();

    public static void main(String[] args) {
        CreateOperatorDemo demo = new CreateOperatorDemo();
        demo.testFrom();
    }

    /**
     * 最基本用法
     * 创建一个被观察者，发送onNext onComplete等事件
     * 观察者订阅接收相关事件
     * onComplete与onError只会存在一个(互斥)
     * onComplete后使用onError会报错 onComplete发送后 观察者已经取消了
     * The exception could not be delivered to the consumer because it has
     * already canceled/disposed the flow or the exception has nowhere to
     * go to begin with
     */
    private void createO() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onError(new Throwable("a"));
                emitter.onComplete();
                //会报错 因为onComplete发送后 观察者已经取消了
//                emitter.onError(new Throwable("a"));
            }
        }).subscribe(new Observer<Object>() {
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
        });
    }


    /**
     * 单一的观察者 comsumer  订阅时可以传入是否处理异常的consumer
     */
    private void cunsumerO() {
        getObservable().subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Throwable {
                P.p(o);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                P.p(throwable.getLocalizedMessage());
            }
        });
    }


    /**
     * just最多可以传入10个事件
     */
    private Observable getJustObservable() {
        Observable observable = Observable.just("a", "b");
        return observable;
    }


    /**
     * 测试just
     * just操作符 快速创建被观察者事件
     */
    private void testJust() {
        getJustObservable().subscribe(getObserver());
    }

    /**
     * fromArray 比just能传入更多
     */
    private void testFromArray() {
        Observable.fromArray("a","b").subscribe(getObserver());
    }

    private void testFrom() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        Observable.fromIterable(list).subscribe(getObserver());

        //兼容并发库
        Observable.fromFuture(new Future<Object>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }
            @Override
            public boolean isCancelled() {
                return false;
            }
            @Override
            public boolean isDone() {
                return false;
            }
            @Override
            public Object get() throws ExecutionException, InterruptedException {
                return "a";
            }
            @Override
            public Object get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                return null;
            }
        }).subscribe(getObserver());

        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "null";
            }
        }).subscribe(getObserver());
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
