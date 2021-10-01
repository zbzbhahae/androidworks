package com.zb.review.mvp.model;


/**
 * model层 提供数据
 * 供presenter调用
 */
public interface IMVPModel {

    void getHttpData(String url, OnHttpResult onHttpResult);

    interface  OnHttpResult {
        void onSuccess(String result);
        void onFailed(String message);
    }
}
