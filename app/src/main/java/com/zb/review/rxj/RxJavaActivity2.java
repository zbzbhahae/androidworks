package com.zb.review.rxj;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding4.view.RxView;
import com.tbruyelle.rxpermissions3.RxPermissions;
import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.utils.P;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;
import kotlin.Unit;

public class RxJavaActivity2 extends BaseActivity {

    private TextView textView;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_2);

        textView = findViewById(R.id.rxjava_text);
        RxView.clicks(textView)
                .compose(RxLifeCycler.bindLifeCycle(this))
                .throttleFirst(1, TimeUnit.SECONDS)//事件发射间隔1秒 其他时间发射的事件全部扔掉
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Throwable {
                        P.p("textview被点击了");
                    }
                });


        RxBus.get().toObservable(String.class)
                .compose(RxLifeCycler.bindLifeCycle(this))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        P.p("RxBus接收到事件-》" + s);
                    }
                });


        applyPermission();


    }

    void applyPermission() {
        new RxPermissions(this).request(Manifest.permission.CAMERA)
                .compose(RxLifeCycler.bindLifeCycle(this))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Throwable {
                        if(aBoolean) {
                            P.p("获得权限");
                        } else {
                            P.p("权限未给予");
                        }
                    }
                });
    }

    /**
     * AsyncSubject:无论发射多少条数据，无论在订阅前发射还是订阅后发射，
     *      都只会收到最后一条发射的数据
     * BehaviorSubject:只会接受到订阅前最后一条发射的数据以及订阅之后的
     *      所有数据
     * ReplaySubject:会接收到全部数据，无论订阅前后。
     * PublishSubject:只会收到订阅之后的所有数据
     */
    void testAsyncSubject() {
        AsyncSubject<Object> subject = AsyncSubject.create();
        testSubject(subject);
        subject.onComplete();//AsyncSubject只有调用onComplete之后才会真正发射事件
    }

    void testBehaviorSubject() {
        BehaviorSubject<String> subject = BehaviorSubject.create();
        testSubject(subject);
    }

    void testReplaySubject() {
        ReplaySubject<String> subject = ReplaySubject.create();
        testSubject(subject);
    }
    void testPublishSubject() {
        PublishSubject<String> subject = PublishSubject.create();
        testSubject(subject);
    }

    void  testSubject(Subject subject) {
        P.line();
        subject.onNext("A");
        subject.onNext("B");
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Throwable {
                P.p("Consumer->" + o);
            }
        });
        subject.onNext("C");
        subject.onNext("D");
//        subject.onComplete();//AsyncSubject只有调用onComplete之后才会真正发射事件
        P.line();
    }

    void testMLeak() {
        disposable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                P.p("开始请求数据");
                Thread.sleep(5000);
                P.p("数据请求结束");
                emitter.onNext("请求成功！");
            }
        })
                .compose(new SchedulersTransformer<Object>())
                .compose(RxLifeCycler.bindLifeCycle(this))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        P.p(o.toString());
                        textView.setText(o.toString());
                    }
                });
//        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
//        disposable.dispose();
        super.onDestroy();
//        disposable.dispose();
//        compositeDisposable.dispose();
    }

    void fun() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {

            }
        })
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Throwable {

                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Throwable {
                        return null;

                    }
                })
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Throwable {

                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
