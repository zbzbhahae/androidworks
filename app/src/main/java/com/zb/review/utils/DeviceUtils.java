package com.zb.review.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.util.UUID;

public class DeviceUtils {

    /**
     * 获取设备ID 基本唯一
     * @param context
     * @return
     */
    public static String getDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * API 26以下获取设备序列号
     * @return null 或者 设备序列号
     */
    public static String getSerialNumber() {
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
//            return android.os.Build.SERIAL;
//        return null;
        return Build.SERIAL;
    }

    /**
     * 生成随机的UUID
     * @return
     */
    public static String createUUID() {
        return UUID.randomUUID().toString();
    }
}
