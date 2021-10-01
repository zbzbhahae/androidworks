package com.zb.reviewjava.proxy.dynamic;

import com.zb.reviewjava.proxy.IA;
import com.zb.reviewjava.proxy.IAImpl;

import java.lang.reflect.Proxy;

/**
 * 为解决静态代理对象必须实现接口的所有方法的问题，Java给出
 * 了动态代理，动态代理具有如下特点：
 * 1.Proxy对象不需要implements接口；
 * 2.Proxy对象的生成利用JDK的Api，在JVM内存中动态的构建
 * Proxy对象。需要使用java.lang.reflect.Proxy类的方法。
 *
 */
public class DynamicProxyTest {

    public static void test() {
        //sample1:
        IA ia = new IAImpl();
        IAInvocation iaInvocation = new IAInvocation(ia);
        IA proxy = (IA) new IADynamicProxy(ia, iaInvocation)
                .getDynamicProxy();
        proxy.doA();

        //jsample2:
        IA proxy2 = (IA) Proxy.newProxyInstance(
                ia.getClass().getClassLoader(),
                ia.getClass().getInterfaces(),
                iaInvocation);
        proxy2.doB();
    }
}
