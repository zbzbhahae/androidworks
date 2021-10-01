package com.zb.common.utils;

import android.util.Log;

/**
 * log输出类
 *
 * 一、Log.v 的调试颜色为黑色的，任何消息都会输出，这里的v代表verbose啰嗦的意思，平时使用就是Log.v(“”,”“);
 *
 * 二、Log.d的输出颜色是蓝色的，仅输出debug调试的意思，但他会输出上层的信息，过滤起来可以通过DDMS的Logcat标签来选择
 *
 * 三、Log.i的输出为绿色，一般提示性的消息information，它不会输出Log.v和Log.d的信息，但会显示i、w和e的信息
 *
 * 四、Log.w的意思为橙色，可以看作为warning警告，一般需要我们注意优化Android代码，同时选择它后还会输出Log.e的信息。
 *
 * 五、Log.e为红色，可以想到error错误，这里仅显示红色的错误信息，这些错误就需要我们认真的分析，查看栈的信息了。
 */
public class P {

    private static final String TAG = "Review";

    private static final boolean DEBUG = CustomAppConfig.DEBUG;


    public static void p(String message) {
        if(DEBUG)
            Log.e(TAG, message);
    }
    public static void v(String message) {
        if(DEBUG)
            Log.e(TAG, message);
    }
    public static void d(String message) {
        if(DEBUG)
            Log.e(TAG, message);
    }
    public static void i(String message) {
        if(DEBUG)
            Log.e(TAG, message);
    }
    public static void w(String message) {
        if(DEBUG)
            Log.e(TAG, message);
    }
    public static void e(String message) {
        if(DEBUG)
            Log.e(TAG, message);
    }


    public static void p(String tag, String message) {
        if(DEBUG)
            Log.e(tag, message);
    }
    public static void i(String tag, String message) {
        if(DEBUG)
            Log.i(tag, message);
    }
    public static void e(String tag, String message) {
        if(DEBUG)
            Log.e(tag, message);
    }
    public static void d(String tag, String message) {
        if(DEBUG)
            Log.d(tag, message);
    }
    public static void v(String tag, String message) {
        if(DEBUG)
            Log.d(tag, message);
    }
    public static void w(String tag, String message) {
        if(DEBUG)
            Log.d(tag, message);
    }

}
