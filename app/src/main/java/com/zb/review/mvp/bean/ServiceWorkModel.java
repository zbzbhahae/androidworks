package com.zb.review.mvp.bean;

import java.io.Serializable;

public class ServiceWorkModel implements Serializable {

    public static int STATE_QUITE = 110;
    public static int STATE_WORK = 11;

    private int state = STATE_WORK;
    private String message;

    public ServiceWorkModel() {}
    public ServiceWorkModel(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
