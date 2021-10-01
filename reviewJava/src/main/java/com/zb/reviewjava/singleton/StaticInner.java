package com.zb.reviewjava.singleton;

public class StaticInner {
    /**
     * 静态内部类来实现单例 无法传入参数 由jvm保证线程安全
     */
    private StaticInner(){}
    private static class SingletoneHolder {
        private static StaticInner instance = new StaticInner();
    }

    public static StaticInner getInstance() {
        return SingletoneHolder.instance;
    }
}
