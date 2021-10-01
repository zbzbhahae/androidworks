package com.zb.reviewjava;

public class ReviewObject {

    static int a = 1;
    private int b = 2;

    public static void review() {
        //equals 方法 比较两个对象是否是同一引用
        //hashCode 方法  返回对象hash值，用于hashmap等
            // 如果一个对象提供给 equals 做比较的信息没有被修改的话，该对象多次调用 hashCode() 方法，
            // 该方法必须始终如一返回同一个 integer
    }

    /**
     *非静态内部类没法在外部类的静态方法中实例化。
     * 非静态内部类的方法可以直接访问外部类的所有数据，包括私有的数据。
     * 在静态内部类中调用外部类成员，成员也要求用 static 修饰。
     * 创建静态内部类的对象可以直接通过外部类调用静态内部类的构造器；
     * 创建非静态的内部类的对象必须先创建外部类的对象，通过
     * 外部类的对象调用内部类的构造器。
     */

    public static void newInner() {
//        Inner i = new Inner(); 在外部类的静态方法中实例化 X
    }

    class Inner {
        int c = a + 1; //在静态内部类中调用外部类成员，成员也要求用 static 修饰


        private void getB() {
            b = a + 1; //静态内部类的方法可以直接访问外部类的所有数据，包括私有的数据
        }
    }


    /**
     * 匿名内部类不能定义任何静态成员、方法
     * 匿名内部类中的方法不能是抽象的
     * 匿名内部类必须实现接口或抽象父类的所有抽象方法
     * 匿名内部类不能定义构造器
     * 匿名内部类访问的外部类成员变量或成员方法必须用 final 修饰
     */

    @Override
    public String toString() {
        return "ReviewObject";
    }
}
