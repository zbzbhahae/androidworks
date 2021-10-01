package com.zb.reviewjava;

/**
 * 多态
 * 父类的引用可以指向子类的对象
 * 创建子类对象时，调用的方法为子类重写的方法或者继承的方法
 * 如果我们在子类中编写一个独有的方法，此时
 * 就不能通过父类的引用创建的子类对象来调用该方法
 */

public class SonClass extends FatherClass {

    public int c = 100;
    public SonClass() {
        super(1);
    }
    public SonClass(int b) {
        super(b);
        this.a = 2;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    static void staticFunc() {

    }

    @Override
    public void doSomeThing() {
        super.doSomeThing();
    }

    public void showInfo(String str) {
        P.p(str);
    }
}


