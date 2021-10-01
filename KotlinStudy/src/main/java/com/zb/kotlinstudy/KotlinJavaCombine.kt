@file:JvmName("KotlinJava")
@file:JvmMultifileClass
/**
 *  @file:JvmName()  重新给kotlin文件定义名字
 *  java调用时不用在后面加KT
 *  如果同包名下的两个kt文件定义了同一个名称 会冲突
 *  这时使用 JvmMultifileClass  来表示合并这两个文件中的方法
 *
 *  @JvmField  让java能够使用.来调用kotlin类的属性，而不用get方法
 *  注意 private属性无效
 *
 *  @JvmOverloads  如果kotlin中方法参数有默认值 而java不支持xxx(参数名=xx)的调用方式
 *  就需要在方法上写上overloads注解 让编译器将方法重载为多个同名参数数量不用的方法
 *  来让java调用支持默认值
 *
 *  @JvmField
 *  @JvmStatic  用来支持kotlin类中compnion中的方法和属性能够在java中
 *  像静态方法、属性那样调用，而不用XXX.Compnion.xxx 来调用
 *
 *@Throws(IOException::class)
 * 如果kotlin方法会抛出异常，使用上面的注解，可以让java在调用时，
 * 编译器自动提示异常捕获
 *
 *
 * 在java中调用kotlin带函数参数的方法时，会将函数变成匿名内部类的形式传入
 * 或者使用Function类型 有几个参数用FunctionX
 * 和RxJava类似
 */

package com.zb.kotlinstudy

import java.io.IOException
import java.lang.Exception

/**
 * kotlin java互相操作
 * Java所有的对象都可能是null
 */

class KotlinJavaA {
    @JvmField
    val spells = listOf("A", "B", "C")

    val name = "赵日天"


    companion object {
        @JvmField
        val INFO = "ABCDE"
        @JvmStatic
        fun getInfoMessage() : String {
            return "InfoMessage is $INFO"
        }
    }

}

fun sayHelloToJava(): String = "hello java"

fun functionWithFunctionParam(func:(String)->Unit) {
}

@JvmOverloads
fun handOverFood(leftHand:String = "浆果", rightHand:String = "牛肉") {
    p("leftHand $leftHand ; rightHand  $rightHand ~")
}

@Throws(IOException::class)
fun throwErrors() {
    throw IOException()
}


fun main() {
    var a = CodeJava()
    a.getStringFromJavaNoStatic(12).p()
    CodeJava.getStringFromJava(11).p()
    var b : String? = CodeJava.getStringFromJavaWithNull(11)
    b?.lowercase().p()

    var c = CodeJava.value.javaClass.p()
    a.value2 = 2;  //访问java的private属性 还是会通过getset方法
    //如果java文件中没有get set方法 这里编译会报错
    //如果是public属性 就不会走get set方法
    a.value2.p()

    /**
     * 调用抛出的异常
     */
    try {
        a.handleException()
    } catch (e:Exception) {
        p("kotlin捕获异常")
    }
}