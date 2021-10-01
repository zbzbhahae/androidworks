package com.zb.basic.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DataViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> dataFromActivity;
    private MutableLiveData<String> dataFromFragment;

    private static DataViewModel mInstance;

    public static DataViewModel get() {
        if(null == mInstance) {
            mInstance = new DataViewModel();
        }
        return mInstance;
    }

    public MutableLiveData<String> getName() {
        if(null == name) {
            name = new MutableLiveData<>("");
        }
        return name;
    }

    public MutableLiveData<String> getDataFromActivity() {
        if(null == dataFromActivity) {
            dataFromActivity = new MutableLiveData<>("");
        }
        return dataFromActivity;
    }

    public MutableLiveData<String> getDataFromFragment() {
        if(null == dataFromFragment) {
            dataFromFragment = new MutableLiveData<>("");
        }
        return dataFromFragment;
    }

    public DataViewModel() {
        super();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
