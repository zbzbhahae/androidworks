package com.zb.review.acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zb.review.R;

import butterknife.BindView;
import butterknife.BindViews;

public class WebviewInteractiveActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.web_view_button1)
    AppCompatButton button1;
    @BindView(R.id.web_view_button2)
    AppCompatButton button2;
    @BindView(R.id.web_view_button3)
    AppCompatButton button3;
    @BindView(R.id.web_view_button4)
    AppCompatButton button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_interactive);

//        WebViewClient client = webView.getWebViewClient();
        webView.loadUrl("https://www.baidu.com");
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }
}