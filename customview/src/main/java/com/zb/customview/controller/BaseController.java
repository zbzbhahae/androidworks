package com.zb.customview.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import java.lang.ref.WeakReference;

/**
 * Created by zWX1094027 on 2021/9/9.
 *
 * 将复杂页面的布局分块
 */

public abstract class BaseController
{

    protected WeakReference<Context> weakContext;
    protected View rootView;
    protected boolean isAddedToViewGroup = false;

    private BaseController()
    {

    }

    public BaseController(Context context)
    {
        weakContext = new WeakReference<Context>(context);
        initViews(context);
        initData();
    }

    protected abstract void initViews(Context context);

    protected abstract void initData();

    /**
     * 从rootView上find控件
     * 
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T find(int id)
    {
        if (null != rootView)
        {
            return (T) rootView.findViewById(id);
        }
        return null;
    }

    //    public View getView() {
    //        return rootView;
    //    }

    public void hide()
    {
        rootView.setVisibility(View.GONE);
    }

    public void visiable()
    {
        rootView.setVisibility(View.VISIBLE);
    }

    public void inVisiable()
    {
        rootView.setVisibility(View.INVISIBLE);
    }

    /**
     * 将ViewController的view添加到布局上
     * 
     * @param father
     * @return
     */
    public boolean addToViewGroup(ViewGroup father)
    {
        if (null != father && !isAddedToViewGroup)
        {
            father.addView(rootView);
            isAddedToViewGroup = true;
            return isAddedToViewGroup;
        }
        return false;
    }

    /**
     * 检查上下文是否可以用
     * 
     * @return
     */
    protected boolean isContextAvailable()
    {
        if (null != weakContext && null != weakContext.get())
            return true;
        return false;
    }
}
