package com.zb.review.acts.network.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.utils.P;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends BaseActivity {

    @BindView(R.id.http_result_text)
    TextView textView;
    @BindView(R.id.http_button)
    Button button;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
    }

    @OnClick(R.id.http_button)
    public void onClick(Button v) {
        textView.setText("");

        Call<ResponseBody> call = Api.instance.getMainPage();

        Api.instance.getMainPage2()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                textView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                P.p("RetrofitActivity--- onError  : " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });


    }

}
