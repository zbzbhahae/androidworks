package com.zb.reviewjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception、Error 是 Throwable 类的子类
 * Error 类对象由 Java 虚拟机生成并抛出，不可捕捉
 * 不管有没有异常，finally 中的代码都会执行
 * 当 try、catch 中有 return 时，finally 中的代码依然会继续执行
 */
public class ExceptionClass {
    public static void main(String[] args) {
        P.p(1);
    }
}


