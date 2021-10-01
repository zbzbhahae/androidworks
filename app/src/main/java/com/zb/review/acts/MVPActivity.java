package com.zb.review.acts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zb.review.R;
import com.zb.review.mvp.presenter.MVPPresenter;
import com.zb.review.mvp.viewmodel.IMVPView;

import butterknife.BindView;
import butterknife.OnClick;

public class MVPActivity extends BaseActivity<MVPPresenter, IMVPView> implements IMVPView {

    @BindView(R.id.http_button)
    Button button;
    @BindView(R.id.http_result_text)
    TextView textView;
    @BindView(R.id.http_progress)
    ProgressBar pb;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

    }

    @Override
    protected MVPPresenter createPresenter() {
        return new MVPPresenter();
    }

    @OnClick(R.id.http_button)
    void onClick(Button b) {
        presenter.fetch();
    }

    @Override
    public void showResult(String result) {
        textView.setText(result);
    }

    @Override
    public void showProgressbar() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        pb.setVisibility(View.GONE);
    }
}
