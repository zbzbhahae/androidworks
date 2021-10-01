package com.zb.reviewjava.singleton;

public class HungerMan {
    /**
     * 饿汉式 空间换时间
     */
    private static HungerMan instance = new HungerMan();
    private HungerMan(){}
    public static HungerMan getInstance() {
        return instance;
    }
}
