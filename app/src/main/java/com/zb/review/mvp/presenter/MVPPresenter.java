package com.zb.review.mvp.presenter;

import com.zb.review.mvp.model.IMVPModel;
import com.zb.review.mvp.model.MVPModel;
import com.zb.review.mvp.viewmodel.IMVPView;

import java.lang.ref.WeakReference;

public class MVPPresenter extends IBasePresenter<IMVPView> {
    IMVPModel imvpModel = new MVPModel();


    /**
     * 业务逻辑
     */
    public void fetch() {
        if(null != imvpModel && null != iBaseView.get()) {
            iBaseView.get().showProgressbar();
            imvpModel.getHttpData("", new IMVPModel.OnHttpResult() {
                @Override
                public void onSuccess(String result) {
                    if(null == iBaseView || null == iBaseView.get())
                        return;
                    //从model获得的数据给到view曾
                    iBaseView.get().showResult(result);
                    iBaseView.get().hideProgressbar();
                }
                @Override
                public void onFailed(String message) {
                    if(null == iBaseView || null == iBaseView.get())
                        return;
                    iBaseView.get().hideProgressbar();
                }
            });
        }
    }

}
