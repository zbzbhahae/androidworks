package com.zb.reviewjava.gson.newHttp;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;



/**
 * Created by zWX1094027 on 2021/9/13.
 */

public class OkHttpUtil {

    private static final int TIME_OUT_CONNECT = 30;
    private static final int TIME_OUT_READ = 30;
    private static final int TIME_OUT_WRITE = 30;


    private static volatile OkHttpClient client;

    public static synchronized OkHttpClient getClient() {
        if(null == client) {
            synchronized (OkHttpUtil.class) {
                if(null == client) {
                    client = new OkHttpClient();

                    client.setConnectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS);
                    client.setReadTimeout(TIME_OUT_READ, TimeUnit.SECONDS);// 设置读取超时时间
                    client.setWriteTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS);// 设置写入超时时间

                    SocketFactory sf = SSLSocketFactory.getDefault(); // mike:证书验证
                    client.setSslSocketFactory((SSLSocketFactory) sf);
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                    client.setHostnameVerifier(hv);


                    // 获取UI主线程 主线程在HttpUtil中获取
                }
            }
        }
        return client;
    }

    public static void get(Object tag, String url, Map<String, String> params, Map<String, String> headers) {

        Call call = getClient().newCall(getRequest(tag, url, params, headers));
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    public static void get(Object tag, String url, Map<String, String> params, Map<String, String> headers, Callback callback) {

        Call call = getClient().newCall(getRequest(tag, url, params, headers));
        call.enqueue(callback);
    }

    public static void post(Object tag, String url, Map<String, String> params, Map<String, String> headers, Callback callback) {
        Call call = getClient().newCall(getPostRequest(tag, url, params, headers));
        call.enqueue(callback);
    }

    public static Request getRequest(Object tag, String url, Map<String, String> params, Map<String, String> headers) {
        Request request = new Request.Builder()
                .headers(getHeaders(headers))
                .url(encodeGetParams(url, params))
                .get()
                .tag(tag)
                .build();
        return request;
    }

    /**
     * 获取一个post请求
     * @param tag
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request getPostRequest(Object tag, String url, Map<String, String> params, Map<String, String> headers) {
        Request request = new Request.Builder()
                .headers(getHeaders(headers))
                .url(encodeGetParams(url, params))
                .post(getPostBody(params))
                .tag(tag)
                .build();
        return request;
    }

    /**
     * 组装post的表单数据
     * @param params
     * @return
     */
    public static RequestBody getPostBody(Map<String, String> params) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if(null != params && 0 != params.size()) {
            for(Map.Entry<String, String> e : params.entrySet()) {
                String key = e.getKey();
                String value = e.getValue();
                if(null != key && null != value && !key.trim().isEmpty() && !value.trim().isEmpty()) {
                    builder.add(key.trim(), value.trim());
                }

            }
        }
        return builder.build();
    }

    /**
     * 将map中的数据组装成header
     * @param headers
     * @return
     */
    public static Headers getHeaders(Map<String, String> headers) {
        if(null == headers || 0 == headers.size()) {
            return new Headers.Builder().build();
        }
        Headers header = Headers.of(headers);
        return header;
    }

    /**
     * 将get请求的参数进行网络编码组装
     * @param url
     * @param params
     * @return
     */
    public static HttpUrl encodeGetParams(String url, Map<String, String> params) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();

        if (null != params && 0 == params.size()) {
            for(Map.Entry<String, String> e : params.entrySet()) {
                String key = e.getKey();
                String value = e.getValue();
                if(null != key && null != value && !key.trim().isEmpty() && !value.trim().isEmpty()) {
                    httpUrlBuilder.addEncodedQueryParameter(key, value);
                }
            }
        }
        return httpUrlBuilder.build();

    }


}
