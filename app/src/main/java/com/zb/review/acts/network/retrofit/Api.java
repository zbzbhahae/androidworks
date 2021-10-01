package com.zb.review.acts.network.retrofit;

import com.zb.review.acts.network.okhttp.LoggingInterceptor;
import com.zb.review.acts.network.okhttp.UserAgentInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

public interface Api {

//    public static String BASE_URL = "http://dxfgj.zhongjue.net";
//    public static String BASE_URL = "https://api.github.com";
    public static String BASE_URL = "https://www.baidu.com";
    public static Api instance = createInstance();

    static Api createInstance() {

        Retrofit r = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkhttp())
                .baseUrl(Api.BASE_URL)
                .build();
        Api api = r.create(Api.class);
        return api;
    }


    /**
     * addInterceptor();
     * addNetworkInteceptor();
     * Interceptor(拦截器) 它能对call进行监测改写重试连接，它能够对请求和回复进行二次加工。
     *
     * 先看下这两种拦截器的区别：
     * Application Interceptor
     *
     * 不必关心url的重定向和重连
     * 只执行一次，即使Response是来自缓存
     * 只关心request的原始意图，而不关心额外添加Header的信息
     * NetworkInterceptor
     *
     * 能够详尽的追踪访问链接的重定向
     * 短时间内的网络访问，它将不执行缓存过来的回应
     * 监测整个网络过程的数据流量
     *

     * @return
     */
    static OkHttpClient createOkhttp() {
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        HttpLoggingInterceptor headerLog = new HttpLoggingInterceptor();
        headerLog.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        HttpLoggingInterceptor basicLog = new HttpLoggingInterceptor();
        basicLog.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(headerLog)
                .addNetworkInterceptor(basicLog)
                .addInterceptor(new UserAgentInterceptor())
//                .hostnameVerifier(new HostnameVerifier() { //取消ssl验证
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                })
                .build();
    }

    @GET(" ")
    Call<ResponseBody> getMainPage();

    @GET(" ")
    Observable<String> getMainPage2();
}
