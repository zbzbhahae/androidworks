package com.zb.common.http;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zb.common.utils.P;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OKHttpUtil {

    private static final int TIMEOUT_CONNECT = 30;
    private static final int TIMEOUT_READ = 30;
    private static final int TIMEOUT_WRITE = 30;


    private static volatile  OkHttpClient client;
    private static final Object o = new Object();

    public static synchronized OkHttpClient getClient() {
        if(null == client) {
            synchronized (o) {
                if(null == client) {
                    client = new OkHttpClient();
                    client.setConnectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS);
                    client.setReadTimeout(TIMEOUT_READ, TimeUnit.SECONDS);
                    client.setWriteTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS);

                }
            }
        }
        return client;
    }

    public static Headers getHeaders() {
        //键值不能为空或者""
        Headers headers = new Headers.Builder().add("", "").add("","").build();
        return headers;
    }

    public static void cancel(Object tag) {
        getClient().cancel(tag);
    }

    public static void get(String url, Object tag, String key, String value) {
        Map<String, String> params = new HashMap<>();
        params.put(key, value);
        get(url, tag, params);
    }

    public static void get(String url, Object tag, Map<String, String> params) {
        cancel(tag);

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if(null != params && params.size() > 0) {
            for(Map.Entry<String, String> e : params.entrySet()) {
                urlBuilder.addEncodedQueryParameter(e.getKey(), e.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
//                .headers(getHeaders())
                .tag(tag).build();
        Call call = getClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                P.p("failure:" + e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                byte[] result = response.body().bytes();
                int responseCode = response.code();
                String result1 = new String(result, Charset.forName("UTF-8"));
                P.p("Thread:" + Thread.currentThread().getName() + "   - " + result1);
            }
        });
    }
}
