package com.zb.reviewjava.gson.newHttp.callback;


import com.zb.reviewjava.gson.newHttp.bean.HttpFailure;

/**
 * Created by zWX1094027 on 2021/9/14.
 * 网络访问最外层的callback T为HttpResultBean<T>
 *  在最终返回时返回的是HttpResultBean.data
 *  与IJsonParser中的T不同
 */

public interface IHttpCallBack<T> {

    void onSuccess(T t, String message, int code);
    void onFailure(HttpFailure failed);
}
