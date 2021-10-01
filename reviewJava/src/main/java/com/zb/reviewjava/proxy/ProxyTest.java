package com.zb.reviewjava.proxy;

public class ProxyTest {

    public static void test() {
        IA ia = new IAImpl();
        IAProxy proxy = new IAProxy(ia);
        proxy.doA();
        proxy.doB();
    }
}
