package com.zb.reviewjava;

/**
 * 抽象类不能有对象（不能用 new 关键字来创建抽象类的对象）
 * 抽象类中的抽象方法必须在子类中被重写
 *
 * 接口中的所有属性默认为：public static final ****；
 * 接口中的所有方法默认为：public abstract ****；
 * 抽象类继承接口可以不必实现接口方法
 */

public abstract class AbstractClass implements Appendable {
    public abstract void doA();
}
