package com.zb.review.acts.network.okhttp;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoggingInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        long t1 = System.currentTimeMillis();
        e("URL--: " + request.url());
        e("Headers--: " + request.headers());
        e("chain connection--: " + chain.connection());//applicationInterceptor中显示null

        Response response = chain.proceed(request);
        long t2 = System.currentTimeMillis();
        //不能直接使用response.body（）.string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，
        // 我们需要创建出一个新的response给应用层处理
        ResponseBody rb = response.peekBody(1 * 1024 * 1024);
        e("响应URL--:" + response.request().url());
        e("响应耗时--:" + (t2 - t1) + "ms");
        e("响应Headers--:" + response.headers());
        e("响应内容--:" +  rb.string());

        return response;
    }

    private static void e(String info) {
        Log.e("AndroidReview-okhttp", "------------------------------------------------------------------");
        Log.e("AndroidReview-okhttp", info);
    }
}
