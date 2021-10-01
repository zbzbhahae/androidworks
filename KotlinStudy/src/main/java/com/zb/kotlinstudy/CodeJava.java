package com.zb.kotlinstudy;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.Throws;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class CodeJava {


    public static void main(String[] args) {
        //java中调用kotlin的函数
        System.out.printf(KotlinJava.sayHelloToJava());
        KotlinJavaA kj = new KotlinJavaA();
        List<String> data = kj.spells;
        System.out.printf(data.toString());

        //Java如果要支持kotlin的默认参数值 需要重载
        //2个默认参数重载出了3个方法 虽然不完美 但是能用
        KotlinJava.handOverFood("A", "B");
        KotlinJava.handOverFood("A");
        KotlinJava.handOverFood();

        //调用kotlin companion中的方法
        KotlinJavaA.Companion.getInfoMessage();
        KotlinJavaA.getInfoMessage();
        KotlinJavaA.INFO.toString();
        //异常处理
        try {
            KotlinJava.throwErrors();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("java handled kotlin's exception");
        }

        Function1<String, Unit> function1 = new Function1<String, Unit>() {
            @Override
            public Unit invoke(String s) {
                return null;
            }
        };
        KotlinJava.functionWithFunctionParam(function1);

        KotlinJava.functionWithFunctionParam(new Function1<String, Unit>() {
            @Override
            public Unit invoke(String s) {
                return null;
            }
        });

    }



    public void handleException() throws IOException {
        throw new IOException();
    }

    public static int value = 0;

    private int value2;

    public int getValue2() {
        System.out.println("  ------ getValue2------");
        return value2;
    }

    public void setValue2(int value2) {
        System.out.println("  ------ setValue2------");
        this.value2 = value2;
    }

    public static String getStringFromJava(int i) {
        return "java string is " + i + "  from kotlin";
    }

    public String getStringFromJavaNoStatic(int i) {
        return "java string is " + i + "  from kotlin";
    }

    @Nullable
    public static String getStringFromJavaWithNull(int i) {
        return null;
    }
}
