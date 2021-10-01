package com.zb.musicplayer.utils;

import android.util.Log;

public class P {

    private static final boolean DEBUG = true;
    private static final String TAG = "MusicPlayer";
    public static void p(String str) { if(DEBUG) Log.e(TAG, str); }
    public static void pe(String str) {
        if(DEBUG)Log.e(TAG, str);
    }
    public static void pi(String str) { if(DEBUG)Log.i(TAG, str); }
}
