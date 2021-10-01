package com.zb.reviewjava.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class IADynamicProxy {
    private Object target;
    private InvocationHandler invocationHandler;

    public IADynamicProxy(Object target, InvocationHandler invocationHandler) {
        this.target = target;
        this.invocationHandler = invocationHandler;
    }

    public Object getDynamicProxy() {
        Object object = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), invocationHandler);

        return object;
    }
}
