package com.zb.basic.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.zb.basic.MainActivity;
import com.zb.basic.acts.BaseActivity;
import com.zb.basic.adapter.MainListAdapter;
import com.zb.basic.mvp.contract.MainContract;
import com.zb.basic.mvp.model.IBaseModel;
import com.zb.basic.mvp.model.MainModel;
import com.zb.basic.mvp.view.IBaseView;

import java.util.List;

public class MainPresenter extends MainContract.AbsMainPresenter<MainContract.IMainView, MainContract.IMainMode> {


    @Override
    public void initRecyclerData() {
        MainContract.IMainView act = getView();
        if(null != act)
            act.refreshRecycler(model.getInitData());
    }

    @Override
    public void performItemClick(int position) {
        Class clz = model.getDestinationWithPosition(position);
        if(null != getView())
            getView().performanceDestination(clz);
    }

    @Override
    protected void initModel() {
        model =  new MainModel();
    }


}
