package com.zb.review.data.sharedperference;



import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * getSharedPreferences() - 如果您需要多个由名称（使用
 *                       第一个参数指定）标识的共享偏好设置文件，则
 *                       使用此方法。您可以从您的应用中的任何 Context
 *                       调用此方法。
 * getPreferences()           - 如果您只需使用 Activity 的一个共享
 *                       首选项，请从 Activity 中使用此方法。由于这会检索
 *                       属于该 Activity 的默认共享偏好设置文件，因此您
 *                       无需提供名称(name为activity的名称)。
 *
 *  如果您使用 SharedPreferences API 保存应用设置，则应改用
 *  getDefaultSharedPreferences() 获取整个应用的默认共享偏好设置文件。
 *  (文件名称context.getPackageName() + "_preferences";)
 *
 *  editor可以使用commit或者apply提交数据 apply会立即改变内存中的数据，
 *  异步存入文件，commit为立即同步存储。
 */
public class S {

    private static final String FILE_NAME = "ReviewAndroidShared";
    private static final int FILE_MODE = Context.MODE_PRIVATE;


    private static SharedPreferences g(Context context) {
        return context.getSharedPreferences(FILE_NAME, FILE_MODE);
    }

    private static SharedPreferences.Editor e(Context context) {
        return g(context).edit();
    }

    public static boolean s(Context context, String key, int value) {
        return e(context).putInt(key, value).commit();
    }

    public static boolean s(Context context, String key, String value) {
        return e(context).putString(key, value).commit();
    }

    public static boolean s(Context context, String key, long value) {
        return e(context).putLong(key, value).commit();
    }

    public static boolean s(Context context, String key, boolean value) {
        return e(context).putBoolean(key, value).commit();
    }

    public static boolean s(Context context, String key, float value) {
        return e(context).putFloat(key, value).commit();
    }

    public static boolean s(Context context, String key, Set<String> value) {
        return e(context).putStringSet(key, value).commit();
    }

    public static int g(Context context, String key, int defaultValue) {
        return g(context).getInt(key, defaultValue);
    }

    public static String g(Context context, String key, String defaultValue) {
        return g(context).getString(key, defaultValue);
    }

    public static long g(Context context, String key, long defaultValue) {
        return g(context).getLong(key, defaultValue);
    }

    public static boolean g(Context context, String key, boolean defaultValue) {
        return g(context).getBoolean(key, defaultValue);
    }

    public static float g(Context context, String key, float defaultValue) {
        return g(context).getFloat(key, defaultValue);
    }

    public static Set<String> g(Context context, String key, Set<String> defaultValue) {
        return g(context).getStringSet(key, defaultValue);
    }
}
