package com.zb.reviewjava.gson.newHttp.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zWX1094027 on 2021/9/14.
 */

public class HttpResultBean<T> implements Serializable {

    @SerializedName("StatusCode")
    private int code;

    @SerializedName("Message")
    private String message;

    @SerializedName("JsonData")
    private T data;





    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
