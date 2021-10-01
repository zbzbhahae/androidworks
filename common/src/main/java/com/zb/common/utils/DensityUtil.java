package com.zb.common.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * DP、PX、SP转换类
 * 不会引用Context 放心使用
 */
public class DensityUtil {

    private static float DISPLAY_SCALE = 0;

    private static float getScale(Context context) {
        if(0 == DISPLAY_SCALE) {
            DISPLAY_SCALE = context.getResources().getDisplayMetrics().density;
        }
        if(0 == DISPLAY_SCALE)//如果设备太奇葩 还是给原始值保险
            DISPLAY_SCALE = 1f;
        return DISPLAY_SCALE;
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getDisplayWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getDisplayHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * dp(dip)转换成px
     * @param context
     * @param
     * @return
     */
    public static int dp2Px(Context context, int dp) {
        float scale = getScale(context);
        return (int)(dp * scale + 0.5f);
    }
    public static int dp2px(Context context, int dp) {
        float scale = getScale(context);
        return (int)(dp * scale + 0.5f);
    }
    public static int dip2Px(Context context, int dp) {
        float scale = getScale(context);
        return (int)(dp * scale + 0.5f);
    }
    public static int dip2px(Context context, int dp) {
        float scale = getScale(context);
        return (int)(dp * scale + 0.5f);
    }

    /**
     * px转换成dp(dip)
     * @param context
     * @param
     * @return
     */
    public static int px2Dp(Context context, int px) {
        float scale = getScale(context);
        return (int) (px / scale + 0.5f);
    }
}
