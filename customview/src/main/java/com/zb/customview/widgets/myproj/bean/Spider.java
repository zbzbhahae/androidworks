package com.zb.customview.widgets.myproj.bean;

/**
 * Created by yWX419033 on 2019/6/10.
 */

public class Spider
{
    public String name;
    public double val;
    public String bigValue;
    public double value;

    public Spider(String name, String bigValue, double value, double val)
    {
        this.name = name;
        this.val = val;
        this.bigValue = bigValue;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Spider{" + "name='" + name + '\'' + ", val=" + val + ", bigValue='" + bigValue + '\'' + '}';
    }
}
