package com.zb.reviewjava.proxy.dynamic;

import com.zb.reviewjava.P;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IAInvocation implements InvocationHandler {

    private Object target;

    public IAInvocation(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        P.p("pre do in IAInvocation");
        Object object = method.invoke(target, args);
//        Object object = method.invoke(target);
        P.p("after do in IAInvocation");
        return object;
    }
}
