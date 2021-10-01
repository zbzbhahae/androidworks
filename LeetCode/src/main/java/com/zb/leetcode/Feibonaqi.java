package com.zb.leetcode;

import com.zb.leetcode.utils.P;
import com.zb.leetcode.utils.TimeCheck;

import java.util.HashMap;

public class Feibonaqi {

    /** 斐波那契数列
     * 0 1 1 2 3 5 8 ...
     * 后一项为前两项的合
     * **/
    public static void code() {
        //第n项的值
        int n = 33;
        TimeCheck.check("fun2", new TimeCheck.CheckCallback() {
            @Override
            public void callFun() {
                fun2(n);
                P.p("the value in position " +  " is :" + fun2(n));
                int i=0;
            }
        });

    }

    public static int fun1(int n) {
        if(n <= 2)
            return n + 1;
        return fun1(n-1) + fun1(n - 2);
    }

    public static int fun2(int n) {
        if(n <= 2)
            return n+1;
        int tem1 = 0;
        int tem2 = 1;
        int tem = 0;
        for(int i=0; i<n -2; i++) {
            tem = tem1 + tem2;
            tem1 = tem2;
            tem2 = tem;
        }
        return tem2;
    }
}
