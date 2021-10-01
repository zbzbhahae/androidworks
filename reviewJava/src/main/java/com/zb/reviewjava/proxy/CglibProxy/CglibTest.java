package com.zb.reviewjava.proxy.CglibProxy;

/**
 * JDK动态代理要求target对象是一个接口的实现对象，
 * 假如target对象只是一个单独的对象，并没有实现
 * 任何接口，这时候就会用到Cglib代理(Code Generation Library)
 * ，即通过构建一个子类对象，从而实现对target对象的代理，
 * 因此目标对象不能是final类(报错)，且目标对象的方法不能是
 * final或static（不执行代理功能）。
 * Cglib依赖的jar包
 *
 * 链接：https://www.jianshu.com/p/8ccdbe00ff06
 */
public class CglibTest {
    public static void test() {

    }
}
