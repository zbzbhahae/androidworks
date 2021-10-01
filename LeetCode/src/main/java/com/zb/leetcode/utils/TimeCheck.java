package com.zb.leetcode.utils;

public class TimeCheck {

    public static void check(String funName, CheckCallback callback) {
        if(null == callback)
            return;
        P.p("[" + funName + "]");
        P.line();
        long startT = System.currentTimeMillis();
        callback.callFun();
        long stopT = System.currentTimeMillis();
        P.line();
        P.p("------TIME USED : " + (stopT - startT) + "  ms ---------");
    }

    public static void checkNano(String funName, CheckCallback callback) {
        if(null == callback)
            return;
        P.p("[" + funName + "]");
        P.line();
        long startT = System.nanoTime();
        callback.callFun();
        long stopT = System.nanoTime();
        P.line();
        P.p("------TIME USED : " + (stopT - startT) / 1000 + "  kns ---------");
    }

    public interface CheckCallback {
        public void callFun();
    }


}
