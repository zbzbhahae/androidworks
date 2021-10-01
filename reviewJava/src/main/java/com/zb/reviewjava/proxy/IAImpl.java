package com.zb.reviewjava.proxy;

import com.zb.reviewjava.P;

public class IAImpl implements  IA {
    @Override
    public void doA() {
        P.p("doA in IAImpl");
    }

    @Override
    public void doB() {
        P.p("doB in IAImpl");
    }
}
