package com.zb.review.dagger.bean;

public class User {

    public String info;


    public User() {
        info = "create from custructor";
    }

    public User(String info) {
        this.info = info;
    }
}
