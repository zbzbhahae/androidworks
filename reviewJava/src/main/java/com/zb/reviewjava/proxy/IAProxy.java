package com.zb.reviewjava.proxy;

import com.zb.reviewjava.P;

public class IAProxy implements IA {

    private IA ia;

    public IAProxy(IA ia) {
        this.ia = ia;
    }

    @Override
    public void doA() {
        P.p("pre doA in proxy");
        ia.doA();
        P.p("after doA in proxy");
    }

    @Override
    public void doB() {
        P.p("pre doB in proxy");
        ia.doB();
        P.p("after doB in proxy");
    }
}
