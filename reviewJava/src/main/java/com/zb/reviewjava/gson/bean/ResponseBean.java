package com.zb.reviewjava.gson.bean;

import com.google.gson.annotations.SerializedName;

public class ResponseBean<T> {

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
