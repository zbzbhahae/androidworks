package com.zb.reviewjava;

public class P {
    public static void line() {
        System.out.println("-----------------------------");
    }
    public static void p(String str) {
        System.out.println(str);
    }
    public static void p(long l) {
        p(l + "");
    }
    public static void p(int i) {
        p(i + "");
    }
    public static void p(float f) {
        p(f + "");
    }
    public static void p(double d) {
        p(d + "");
    }
    public static void p(boolean b) {
        p(b + "");
    }
    public static <T> void p(T t) {
        p(t.toString());
    }
}
