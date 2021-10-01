package com.zb.review.acts.network.okhttp;

import android.os.Bundle;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.utils.P;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends BaseActivity implements View.OnClickListener {

    TextView infoTextView;
    Button button;
    private OkHttpClient client;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        infoTextView = findViewById(R.id.http_result_text);
        button = findViewById(R.id.http_button);

        infoTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        button.setOnClickListener(this);

        client = new OkHttpClient();
    }

    @Override
    public void onClick(View v) {
        doHttp();
    }

    void doHttp() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Request r = new Request.Builder().url(new URL("https://news-at.zhihu.com/api/4/news/9660723")).build();
//                    Response response = client.newCall(r).execute();
//                    String result = response.body().string();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            infoTextView.setText(result);
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        infoTextView.setText("");
        try {
            Request request = new Request.Builder().url(new URL("https://news-at.zhihu.com/api/4/news/9660723")).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                infoTextView.setText(response.body().string());
                                P.p("runOnUiThread--------是否在主线程 ： " + (Looper.getMainLooper().getThread() == Thread.currentThread()? "是" : "否"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    P.p("onRespose--------是否在主线程 ： " + (Looper.getMainLooper().getThread() == Thread.currentThread()? "是" : "否"));
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}

