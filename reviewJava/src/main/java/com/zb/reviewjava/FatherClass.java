package com.zb.reviewjava;


public class FatherClass {
    int a = 0;
    private int b = 1;

    public FatherClass(int b) {
        this.a = b;
    }
    @Override
    public String toString() {
        return "FatherClass{" +
                "a=" + a +
                '}';
    }

    protected int getB() {
        return b;
    }

    /**
     * 可以声明成员变量、方法、类以及本地变量
     * final 成员变量必须在声明的时候初始化或者在构造器中初始化，否则就会报编译错误
     * final 变量是只读的
     * final 申明的方法不可以被子类的方法重写
     * final 类通常功能是完整的，不能被继承
     * final 变量可以安全的在多线程环境下进行共享，而不需要额外的同步开销
     * final 关键字提高了性能，JVM 和 Java 应用都会缓存 final 变量，会对方法、变量及类进行优化
     * 方法的内部类访问方法中的局部变量，但必须用 final 修饰才能访问
     */
    final void finalFunc() {

    }

    static void staticFunc() {

    }

    public void doSomeThing() {

    }

    private void doA() {

    }
}
