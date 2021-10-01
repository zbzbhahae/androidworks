package com.zb.reviewjava.controlflow;

import com.zb.reviewjava.P;

public class BreakOutTest {

    static char c;

    public static void main(String[] args) {

        testBreakOuter();
    }

    static void testBreakOuter() {
        outer:
        for (int i = 0; i < 100; i++) {
            P.p("i----:" + i);
            for (int k = 0; k < 100; k++) {
                P.p("k--:" + i);
                for (int j = 0; j < 100; j++) {
                    P.p("j:" + j);
                    if (j > 10) {
                        break outer;
                    }
                }
            }
        }

        P.p("END!啊" + c);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

    }

    class A {
        int i;
        {
            i = 100;
        }
    }

    static void fun(int... arg) {}
    static void fun(Integer... arg) {}
    static void fun(float... arg) {}
    static {
//        fun();

    }

    public enum Type {
        A, B, C, D
    }

    static {
        Type a = Type.A;
        for(Type t : Type.values()) {
            System.out.println(t + "---" + t.ordinal());
        }
    }
}
