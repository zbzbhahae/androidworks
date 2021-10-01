package com.zb.kotlinapp.ui.acts

import android.os.Bundle
import com.zb.kotlinapp.api.serviceApi
import com.zb.kotlinapp.databinding.ActivityButtonTextBinding
import com.zb.kotlinapp.utils.P
import kotlinx.coroutines.*
import java.lang.Exception


/**
 * CoroutineScope分类
 * GlobalScope:声明周期是process级别的，activity销毁后依然可以执行直到进程结束
 * MainScope:  在activity中使用，可以在onDestory中取消
 * 
 *
 *
 */
class XieChengScope : BaseActivityWithViewBinding(), CoroutineScope by MainScope() {

    private val binding : ActivityButtonTextBinding by inflate()

    private val mainScope = MainScope()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding

        binding.button.setOnClickListener {
//            mainScope.launch {
//                try {
//                    delay(6000)
//                    val result = serviceApi.getInfo().filter { it.code in 0x4e01..0x9fa4 }
//                    binding.textView.text = result
//                } catch (e:Exception) {
//                    P.p("协程中的异常${e.localizedMessage}")
//                }
//            }
            launch {
                try {
                    delay(6000)
                    val result = serviceApi.getInfo().filter { it.code in 0x4e01..0x9fa4 }
                    binding.textView.text = result
                } catch (e:Exception) {
                    P.p("协程中的异常${e.localizedMessage}")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
        cancel()
    }

}