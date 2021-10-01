package com.zb.review.acts.network.okhttp;

import android.webkit.WebSettings;

import androidx.annotation.NonNull;

import com.zb.review.App;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UserAgentInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .removeHeader("User-Agent")
                .addHeader("User-Agent",
                        WebSettings.getDefaultUserAgent(App.getInstance()))
                .build();
        return chain.proceed(request);
    }
}
