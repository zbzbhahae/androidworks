package com.zb.basic.mvvm.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DataViewModelWithArgsFactory implements ViewModelProvider.Factory {
    private boolean tem;
    public DataViewModelWithArgsFactory(boolean isTrustMessage) {
        tem = isTrustMessage;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DataViewModelWithArgs(tem);
    }
}
