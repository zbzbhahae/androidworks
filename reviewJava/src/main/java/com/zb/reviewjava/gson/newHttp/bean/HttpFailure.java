package com.zb.reviewjava.gson.newHttp.bean;

/**
 * Created by zWX1094027 on 2021/9/14.
 */

public class HttpFailure {

    public static final int LOGIN_FAILED = 0x1;
    public static final int ERROR_EXCEPTION = 0x1 << 1;
    public static final int SUCCESS_NULL_VALUE = 0x1 << 2;
    public static final int SUCCESS = 0x1 << 3;



    private String message;
    private int errorCode;
    private String showMessage;

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
