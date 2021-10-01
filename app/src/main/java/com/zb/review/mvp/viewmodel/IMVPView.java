package com.zb.review.mvp.viewmodel;

public interface IMVPView extends IBaseView {
    void showResult(String result);
    void showProgressbar();
    void hideProgressbar();
}
