package com.zb.common.utils.handleerror;

import android.os.Build;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 处理错误的工具类
 */
public class WarningUtil {

    /**
     * Detected problems with API compatibility 警告窗口
     * 在Application的onCreate()中使用
     * Android P 后谷歌限制了开发者调用非官方公开API 方法或接口，也就是说，
     * 你用反射直接调用源码就会有这样的提示弹窗出现，非 SDK 接口指的是 Android
     * 系统内部使用、并未提供在 SDK 中的接口，开发者可能通过 Java 反射、JNI
     * 等技术来调用这些接口。这些都是危险行为：非 SDK 接口没有任何公开文档，
     * 你需要查看源代码才能理解其行为逻辑。
     * 原文链接：https://blog.csdn.net/qXing123456789/article/details/88864434
     */
    public static void clearAPIWarningDialog() {
        if (Build.VERSION.SDK_INT < 28)
            return;
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
        }
    }
}
