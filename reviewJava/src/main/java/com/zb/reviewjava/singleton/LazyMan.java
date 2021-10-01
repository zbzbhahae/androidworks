package com.zb.reviewjava.singleton;


import java.io.Serializable;

/**
 * 1、防止反射
 *
 *    定义一个全局变量，当第二次创建的时候抛出异常
 *
 * 2、防止克隆破坏
 *
 *       重写clone(),直接返回单例对象
 *
 * 3、防止序列化破坏
 *
 *    添加readResolve(),返回Object对象
 */
public class LazyMan implements Serializable {
    /**
     * 当把变量声明为 volatile 类型后，编译器
     * 与运行时都会注意到这个变量是共享的，
     * 因此不会将该变量上的操作与其他内存操
     * 作一起重排序。volatile 变量不会被缓存
     * 在寄存器或者对其他处理器不可见的地方
     * ，JVM 保证了每次读变量都从内存中读，
     * 跳过 CPU cache 这一步，因此在读取
     * volatile 类型的变量时总会返回最新写入的值。
     */
    private static volatile LazyMan instance = null;

    public LazyMan getInstance(Object o) {
        if(null == instance) {
            synchronized (LazyMan.class) {
                if(null == instance)
                    instance = new LazyMan(o);
            }
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return instance.getInstance(new Object());
    }

    /**
     * 防止序列化破坏
     * @return
     */
    private Object readResolve() {
        return getInstance(new Object());
    }

    /**
     * 私有化构造函数
     */
    private LazyMan() {

    }
    private LazyMan(Object o) {
    }
}
