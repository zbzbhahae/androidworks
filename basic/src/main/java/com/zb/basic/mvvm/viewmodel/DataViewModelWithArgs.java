package com.zb.basic.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DataViewModelWithArgs extends ViewModel {

    private boolean isTrustMessage = false;
    private MutableLiveData<String> message;

    public DataViewModelWithArgs(boolean isTrustMessage) {
        this.isTrustMessage = isTrustMessage;
    }



    public MutableLiveData<String> getMessage() {
        if(!isTrustMessage)
            return null;
        if(null == message) {
            //构造方法中可以传入默认值
            message = new MutableLiveData<>("");
        }
        return message;
    }
}