package com.zb.reviewjava;

import com.zb.reviewjava.proxy.ProxyTest;
import com.zb.reviewjava.proxy.dynamic.DynamicProxyTest;

public class MainClass {

    /**
     * 静态代理模式在不改变目标对象的前提下，实现
     * 了对目标对象的功能扩展。
     * 不足：静态代理实现了目标对象的所有方法，一
     * 旦目标接口增加方法，代理对象和目标对象都要
     * 进行相应的修改，增加维护成本。
     */
    public static void main(String args[]) {
        P.line();
        System.out.println("java review, Hello World! ");

//        DynamicProxyTest.test();

        P.p("   " + (Integer.MAX_VALUE + 199 - 1));

        short a = 1;
        a = (short) (a<<2);
        P.p(a);

    }
}