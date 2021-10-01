package com.zb.basic.mvp.presenter;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.zb.basic.acts.BaseActivity;
import com.zb.basic.mvp.model.IBaseModel;
import com.zb.basic.mvp.view.IBaseView;
import com.zb.common.utils.P;

import java.lang.ref.WeakReference;

public abstract class AbsPresenter<T extends IBaseView, V extends IBaseModel> implements LifecycleObserver {

    protected V model;
    WeakReference<T> activityWeakReference;

    public  void attach(T activity) {
        P.e("Presenter::attach()");
        if(activity instanceof ComponentActivity)
            ((ComponentActivity) activity).getLifecycle().addObserver(this);
        activityWeakReference = new WeakReference<>(activity);
        initModel();
    }



    protected abstract void initModel();


    protected T getView() {
        if(null != activityWeakReference)
            return activityWeakReference.get();
        return null;
    }


    public void dettach() {
        P.e("Presenter::dettach()");
        activityWeakReference.clear();
        activityWeakReference = null;
        model = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onActivityCreate(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onActivityStart(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onActivityStop(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onActivityResume(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onActivityPause(LifecycleOwner owner) {
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onActivityDestroy(LifecycleOwner owner) {
        dettach();
    }


}
