package com.zb.review.rxj;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ToolOperatorDemo {

    public static void main(String[] args) {
        ToolOperatorDemo demo = new ToolOperatorDemo();
        demo.testRange();
    }

    private void testSchedulers() {

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                //创建发射事件
                P.p("send on " + Thread.currentThread());
                Thread.sleep(2000);
                emitter.onNext("a");
                Thread.sleep(1000);
                emitter.onNext("b");
                emitter.onComplete();
            }
        })
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        P.p("doOnNext->" + o.toString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object s) throws Throwable {
                        P.p(s.toString() + "   map on " + Thread.currentThread());
                        return "";
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(getObserver());

        try {
            Thread.sleep(6666);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 过滤操作符 符合规则的返回true继续向下游发送
     */
    private void testFilter() {
        Observable.just('a', 'b', 'c', 'd')
                .filter(new Predicate<Character>() {
                    @Override
                    public boolean test(Character character) throws Throwable {
                        return character>'b';
                    }
                })
                .subscribe(getObserver());
    }

    /**
     * range 发射一组事件
     * 获得1-10中的偶数
     */
    private void testRange() {
        Observable.range(1, 10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return integer % 2 ==0;
                    }
                })
                .subscribe(getObserver());
    }


    private Observer getObserver() {
        Observer observer = new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                P.p("onSubscribe   " + Thread.currentThread());
            }
            @Override
            public void onNext(@NonNull Object o) {
                P.p("onNext->" + o.toString() + "   " + Thread.currentThread());
            }
            @Override
            public void onError(@NonNull Throwable e) {
                P.p("onError->" + e.getLocalizedMessage() + "  " + Thread.currentThread());
            }
            @Override
            public void onComplete() {
                P.p("onComplete   " + Thread.currentThread());
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
