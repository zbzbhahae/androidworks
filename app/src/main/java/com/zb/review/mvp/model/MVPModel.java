package com.zb.review.mvp.model;

import com.zb.review.acts.network.retrofit.Api;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MVPModel implements IMVPModel {
    @Override
    public void getHttpData(String url, OnHttpResult onHttpResult) {
        Observable<String> result = Api.instance.getMainPage2();
        result.delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onNext(@NonNull String s) {
                        onHttpResult.onSuccess(s);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        onHttpResult.onFailed(e.getLocalizedMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
