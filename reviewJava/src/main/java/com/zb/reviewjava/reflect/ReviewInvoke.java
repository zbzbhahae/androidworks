package com.zb.reviewjava.reflect;

import com.zb.reviewjava.P;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReviewInvoke {

    private static void doInvoke() {
        try {
            Class sonClass = Class.forName("com.zb.reviewjava.SonClass");
            //使用这句 目标类必须有无参构造函数
            Object son = sonClass.getDeclaredConstructor().newInstance();
            //只能获取公开的属性和方法
            Method method = sonClass.getMethod("showInfo", String.class);
            Field f = sonClass.getDeclaredField("c");
            P.p("invoke SonClass, the c is " + f.get(son));
            method.invoke(son, "this is from invoke class");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sample() {
        try {
            Class cls = Class.forName("com.zb.reviewjava");
            //获取构造方法
            Constructor[] publicConstructors = cls.getConstructors();
            //获取全部构造方法
            Constructor[] declaredConstructors = cls.getDeclaredConstructors();
            //获取公开方法
            Method[] methods = cls.getMethods();
            //获取全部方法
            Method[] declaredMethods = cls.getDeclaredMethods();
            //获取公开属性
            Field[] publicFields = cls.getFields();
            //获取全部属性
            Field[] declaredFields = cls.getDeclaredFields();
            Object clsObject = cls.getDeclaredConstructor().newInstance();
            Method method = cls.getDeclaredMethod("showInfo");
            Object object = method.invoke(null);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
