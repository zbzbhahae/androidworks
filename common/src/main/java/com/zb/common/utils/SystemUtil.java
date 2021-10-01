package com.zb.common.utils;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.os.Build;

import androidx.core.app.ActivityManagerCompat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

/**
 * 与系统、进程有关的工具类
 */
public class SystemUtil {




    /**
     * 获得系统环境信息
     * @return
     */
    public static Properties getSystemProperties() {
        return System.getProperties();
    }

    public static List<ActivityManager.AppTask> getAppTaskInfo(Context context, String packageName) {
        ActivityManager am = getActivityManager(context);
        return am.getAppTasks();
    }

    public static List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses(Context context) {
        ActivityManager am = getActivityManager(context);
        return am.getRunningAppProcesses();
    }


    /**
     * 不推荐 需要权限
     * @param context
     * @param packageName
     */
    public static void killSelf(Context context, String packageName) {
        ActivityManager am = getActivityManager(context);
        am.killBackgroundProcesses(packageName);
    }

    /**
     * 光速有效
     * 其实也没效
     */
    public static void killProgress() {
        System.exit(0);
    }

    /**
     * 获得Activity管理
     * @param context
     * @return
     */
    private static ActivityManager getActivityManager(Context context) {
        return (ActivityManager) context.getApplicationContext()
                .getSystemService(Service.ACTIVITY_SERVICE);
    }
}
