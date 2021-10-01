package com.zb.kotlinapp.ui.acts

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.zb.kotlinapp.databinding.ActivityMainBinding
import com.zb.kotlinapp.utils.P
import java.lang.Exception

open class BaseActivityWithViewBinding : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    /**
     * 耗时70+ms 于子activity中写无异
     */
    inline fun<reified VB : ViewBinding> inflate() = lazy {
        val startT = System.currentTimeMillis();
        (VB::class.java.getMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as VB).apply {setContentView(root) }
    }


//    fun initBinding() {
//                try {
//
//            P.p(this.javaClass.name)
//            var bindingName = localClassName
//                .split(".").last().let {subs->
//                    subs.substring(0, subs.length-8)
//                }
//                .plus("Binding").let {
//                    "com.zb.kotlinapp.databinding.Activity$it"
//                }
//
//
//            P.p(bindingName)
//            viewBinding = ClassLoader
//                .getSystemClassLoader()
//                .loadClass(bindingName)
//                .getDeclaredMethod("inflate", LayoutInflater::class.java)
//                .invoke(null, layoutInflater) as T
//            setContentView(viewBinding.root)
//        } catch (e:Exception) {
//            P.p(e.localizedMessage)
//        }
//        viewBinding =
//        setContentView(viewBinding.root)
//    }

}