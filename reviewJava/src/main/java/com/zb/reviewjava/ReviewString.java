package com.zb.reviewjava;


/**
 * String 是 final 类，不能被继承。对于已经存在的 Stirng 对象，修改它的值，就是重新创建一个对象
 * StringBuffer 是一个类似于 String 的字符串缓冲区，使用 append() 方法修改 Stringbuffer 的值，使用 toString() 方法转换为字符串，是线程安全的
 * StringBuilder 用来替代于 StringBuffer，StringBuilder 是非线程安全的，速度更快
 */
public class ReviewString {

    public static void d() {
        StringBuilder sBuilder = new StringBuilder("000");
        sBuilder.append("123456", 0, 1);
        P.p(sBuilder.toString());
    }
}
