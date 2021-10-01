package com.zb.basic.mvp.contract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.zb.basic.acts.BaseActivity;
import com.zb.basic.mvp.model.IBaseModel;
import com.zb.basic.mvp.presenter.AbsPresenter;
import com.zb.basic.mvp.view.IBaseView;

import java.util.List;

public interface MainContract {
    interface IMainView extends IBaseView {
        void refreshRecycler(List<String> data);
        void performanceDestination(Class<Activity> clz);
    }

    interface IMainMode extends IBaseModel {
        List<String> getInitData();
        Class getDestinationWithPosition(int position);
    }

    abstract class  AbsMainPresenter<T extends IBaseView, V extends IBaseModel> extends AbsPresenter<T, V> {

        protected abstract void initRecyclerData();

        protected abstract void performItemClick(int position);


    }


}
