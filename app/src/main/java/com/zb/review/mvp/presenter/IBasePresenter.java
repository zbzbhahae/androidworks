package com.zb.review.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.zb.review.mvp.viewmodel.IBaseView;
import com.zb.review.mvp.viewmodel.IMVPView;

import java.lang.ref.WeakReference;

public class IBasePresenter<T extends IBaseView> implements LifecycleObserver {

    public WeakReference<T> iBaseView;


    public void onAttach(T view) {
        if(null == iBaseView)
            iBaseView = new WeakReference<>(view);
    }
    public void onDettach() {
        if(null != iBaseView) {
            iBaseView.clear();
            iBaseView = null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
    }

}
