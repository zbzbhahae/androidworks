package com.zb.review.acts.network;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.utils.P;
import com.zb.review.utils.T;

import java.net.URL;

import butterknife.BindView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class RxJavaActivity extends BaseActivity {


    @BindView(R.id.http_result_text) TextView textView;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        newSample4();
    }

    void newSample() {

        Observable.just("aaa")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        P.p("Rxjava String is " + s);
                    }
                 });
    }

//    void sample1() {
//        Bitmap b = null;
//
//        File[] folders = null;
//        Subscription png = Observable.from(folders)
//                .flatMap(new Func1<File, Observable<File>>() {
//                    @Override
//                    public Observable<File> call(File file) {
//                        return Observable.from(file.listFiles());
//                    }
//                })
//                .filter(new Func1<File, Boolean>() {
//                    @Override
//                    public Boolean call(File file) {
//                        return file.getName().endsWith("png");
//                    }
//                })
//                .map(new Func1<File, Bitmap>() {
//                    @Override
//                    public Bitmap call(File file) {
//                        return b;
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//
//                    }
//                });
//    }

    void newSample2() {
        String names[] = {"111", "222", "333"};
        Observable.fromArray(names)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        P.p("rxjava    --   " + s);
                    }
                });
    }
//    void sample2() {
//        String names[] = {"111", "222", "333"};
//        Observable.from(names)
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        P.p("rxjava    --   " + s);
//                    }
//                });
//    }

    void newSample3() {
        String text = "ABC";
        Observable.just(text)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        P.p("Rxjava newSample3 onSubscribe!");
                    }
                    @Override
                    public void onNext(@NonNull String s) {
                        textView.setText(s);
                        P.p("Rxjava newSample3 onNext!");
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        P.p("Rxjava newSample3 onComplete!");
                    }
                });
    }

//    void sample3() {
//        String text = "fdsfs";
//        //将字符串显示在textview上
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                String str = "fdfdsafs";
//                subscriber.onNext(str);
//                subscriber.onCompleted();
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                T.t(RxJavaActivity.this, "显示成功");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                T.t(RxJavaActivity.this, "显示失败");
//            }
//
//            @Override
//            public void onNext(String s) {
//                TextView tv = RxJavaActivity.this.findViewById(R.id.http_result_text);
//                tv.setText(s);
//            }
//        });
//    }

    void newSample4() {
        String urlStr = "https://www.baidu.com";
        Observable.just(urlStr)
                .map(new Function<String, URL>() {
                    @Override
                    public URL apply(String s) throws Throwable {
                        URL url = new URL(s);
                        return url;
                    }
                }).map(new Function<URL, String>() {
            @Override
            public String apply(URL url) throws Throwable {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        P.p("Rxjava  onSubscribe-------");
                    }
                    @Override
                    public void onNext(@NonNull String s) {
                        textView.setText(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        P.p("Rxjava  onError-------" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    void sample4() {
//        String urlStr = "https://www.baidu.com";
//        Observable.just(urlStr)
//                .map(new Func1<String, URL>() {
//                    @Override
//                    public URL call(String s) {
//                        URL url = null;
//                        try {
//                            url = new URL(s);
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        }
//                        return url;
//                    }
//                })
//                .map(new Func1<URL, String>() {
//                    @Override
//                    public String call(URL url) {
//                        String result = null;
//                        OkHttpClient client = new OkHttpClient();
//                        Request request = new Request.Builder().url(url).build();
//                        try {
//                            Response response = client.newCall(request).execute();
//                            result = response.body().string();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        return result;
//                    }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        T.t(RxJavaActivity.this, "获取数据出现错误");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        T.t(RxJavaActivity.this, "获取数据成功---");
//                        ((TextView)RxJavaActivity.this.findViewById(R.id.http_result_text)).setText(s);
//                    }
//                });
//    }

    void newSample5() {
        String[] infos = {"a", "b", "c"};
        Observable.fromArray(infos)
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Throwable {
                        Thread.sleep(3000);
                        String result[] = {s + "1", s + "2", s + "3"};
                        return Observable.fromArray(result);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        P.p("------- " + s + " ------");
                    }
                });
    }
    /**
     * 输出结果 9秒后 a1 a2 a3   9秒  b1 b2 b3   9秒 c1 c2 c3
     */
//    void sample5() {
//        String[] infos = {"a", "b", "c"};
//        Observable.from(infos)
//                .flatMap(new Func1<String, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(String s) {
//                        String[] result = {s + "1", s + "2", s + "3"};
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        return Observable.from(result);
//
//                    }
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        P.p("------- " + s + " ------");
//                    }
//                });
//
//    }

    void newSample6() {

    }
}
