package com.zb.okhttp

import java.io.IOException
import kotlin.jvm.Throws

/**
 * 观察、修改和潜在地短路发出的请求和返回的相应响应。
 * 通常拦截器添加、删除或转换请求或响应上的标头。
 * 此接口的实现抛出 [IOException] 以表示连接失败。
 * 这包括自然异常，例如无法访问的服务器，以及当响应
 * 属于意外类型或无法解码时的合成异常。其他异常类型
 * 取消当前调用：对于使用 [Call.execute] 进行的同步调用，
 * 异常会传播给调用者。对于使用 [Call.enqueue] 进行的异步
 * 调用，[IOException] 会传播给调用者，指示调用已取消。
 * 拦截器的异常被传递到当前线程的[未捕获异常处理程序]
 * [Thread.UncaughtExceptionHandler]。默认情况下，这会使
 * Android 上的应用程序崩溃并在 JVM 上打印堆栈跟踪。
 * （崩溃报告库可以自定义此行为。）发出故障信号的一个好方法
 * 是使用合成 HTTP 响应
 */
interface Interceptor {




    interface Chain {
        
    }

}