package com.zb.ndk.utils;

import android.util.Log;

public class L {
    public static void i(String str) {
        Log.i("AndroidReviewLog", str);
    }
    public static void line() {
        Log.e("AndroidReviewLog", "========================");
    }

    public static void e(String str) {
        Log.e("AndroidReviewLog", str);
    }
}
