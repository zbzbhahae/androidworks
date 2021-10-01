package com.zb.hiltapp.bean;

import android.content.Context;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

public class Student {

    public String info = "";
    private Context context;

//    @Inject
    public Student(){}

//    @Inject
//    public Student(@ActivityContext Context c) {
//        this.context = c;
//        info = "inject from bean, with activity context";
//    }

    public Context getContext() {
        return context;
    }

    public Student(Context context, String str) {
        this.context = context;
        this.info = str;
    }

    public Student(String info) {
        this.info = info;
    }
}
