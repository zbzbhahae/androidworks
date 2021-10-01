package com.zb.review.rxj;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.zb.review.utils.P;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class RxLifeCycler<T> implements LifecycleObserver, ObservableTransformer<T, T> {

    final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        P.p("RxLifeCycler->ON_DESTROY");
//        if(!compositeDisposable.isDisposed())
        //里面有判断是否终止
            compositeDisposable.dispose();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        P.p("RxLifeCycler->ON_RESUME");
    }

    @Override
    public @NonNull ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Throwable {
                compositeDisposable.add(disposable);
            }
        });
    }

    public static <T> RxLifeCycler<T> bindLifeCycle(LifecycleOwner owner) {
        RxLifeCycler<T> lifeCycler = new RxLifeCycler<>();
        owner.getLifecycle().addObserver(lifeCycler);
        return lifeCycler;
    }
}
