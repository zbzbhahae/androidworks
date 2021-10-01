package com.zb.kotlinapp.ui.acts

import android.os.Bundle
import com.zb.kotlinapp.databinding.ActivityButtonTextBinding
import com.zb.kotlinapp.utils.P
import kotlin.coroutines.*


/**
 * CoroutineScope分类
 * GlobalScope:声明周期是process级别的，activity销毁后依然可以执行直到进程结束
 * MainScope:  在activity中使用，可以在onDestory中取消
 *
 *
 *
 */
class Xiecheng2 : BaseActivityWithViewBinding() {

    private val binding : ActivityButtonTextBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding

        //协程体
        //协程的挂起点通过continuation保存
        val continuation = suspend {
            //xx
            5
        }.createCoroutine(object : Continuation<Int> {
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<Int>) {
                P.p("携程执行完成$result")
            }
        })
        continuation.resume(Unit)
    }
}