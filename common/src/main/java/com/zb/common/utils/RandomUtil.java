package com.zb.common.utils;

import java.util.Random;

/**
 * 随机生成工具类
 */
public class RandomUtil {
    private static Random r;

    private static Random instance() {
        if(null == r) {
            r = new Random();
        }
        return r;
    }

    /**
     * 获得一个随机int值
     * @return
     */
    public static int getRandomInt() {
        return instance().nextInt();
    }
    /**
     * 获得一个随机int值
     * 0-bounds 左闭右开
     * @return
     */
    public static int getRandomInt(int bounds) {
        return instance().nextInt(bounds);
    }
}
