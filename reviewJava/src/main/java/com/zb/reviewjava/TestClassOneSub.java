package com.zb.reviewjava;

import com.zb.reviewjava.classbean.ClassOne;

public class TestClassOneSub extends ClassOne {

    public void testAccess() {
//        a = 1;//外层包中不能访问friendly和private的属性
        b = 2;
//        c = 3;
        d = 4;
    }
}