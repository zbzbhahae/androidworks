package com.zb.review.acts.network;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.utils.P;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpActivity extends BaseActivity implements View.OnClickListener {

    TextView infoTextView;
    Button button;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        infoTextView = findViewById(R.id.http_result_text);
        button = findViewById(R.id.http_button);

        infoTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        goHttp();
    }

    void goHttp() {
        infoTextView.setText("");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    InputStream in = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(in);

                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    String result = stringBuilder.toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            infoTextView.setText(result);
                        }
                    });

                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(null != connection)
                        connection.disconnect();
                }
            }
        };
        new Thread(r).start();
    }
}
