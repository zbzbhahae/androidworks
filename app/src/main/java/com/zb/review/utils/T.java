package com.zb.review.utils;

import android.content.Context;
import android.widget.Toast;

public class T {

    private static final boolean DEBUG = true;



    public static void t(Context context, String msg) {
        toast(context, msg, Toast.LENGTH_SHORT);
    }
    public static void tl(Context context, String msg) {
        toast(context, msg, Toast.LENGTH_LONG);
    }

    public static void toast(Context context, String message, int duration) {
        if(DEBUG)
            Toast.makeText(context, message, duration).show();
    }

    public static Toast makeToast(Context context, String message, int duration) {
        return Toast.makeText(context, message, duration);
    }
}
