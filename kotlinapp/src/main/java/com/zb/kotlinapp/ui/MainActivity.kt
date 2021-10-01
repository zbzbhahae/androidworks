package com.zb.kotlinapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zb.kotlinapp.api.serviceApi
import com.zb.kotlinapp.databinding.ActivityMainBinding
import com.zb.kotlinapp.ui.acts.BaseActivityWithViewBinding
import com.zb.kotlinapp.ui.acts.XieChengScope
import com.zb.kotlinapp.ui.acts.XieChengViewModelDataBinding
import com.zb.kotlinapp.ui.acts.Xiecheng2
import com.zb.kotlinapp.ui.adapters.MainAdapter
import com.zb.kotlinapp.ui.adapters.MainAdapter.OnItemClickListener
import com.zb.kotlinapp.utils.P
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 *
 * 离谱的bug
 *
 * 如果请求函数加了Suspend 而函数返回是Call<String>类型
 * retrofit2将无法通过converter将请求转化为Call<String>
 *     但是suspend + 返回值String类型就可以
 */


class MainActivity : BaseActivityWithViewBinding(), View.OnClickListener {

    private val binding : ActivityMainBinding by inflate()
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainTextView.text = "1233333"
        binding.mainButton.setOnClickListener(this)

        adapter = MainAdapter()
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)

        initData()

        adapter.listener = object : OnItemClickListener {
            override fun onClick(position: Int) {
                P.p("onItemClick:$position")
                when(position) {
                    0->{goToNextActivity(Xiecheng2::class.java)}
                    1->{goToNextActivity(XieChengScope::class.java)}
                    2->{goToNextActivity(XieChengViewModelDataBinding::class.java)}
                    else->{}
                }
            }
        }

    }

    private fun initData() {
        var data : List<String> = listOf(
            "携程挂起",
            "协程scope",
            "databinding viewmodel 协程 scope")
        adapter.data = data
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.mainButton-> {
                //携程
                GlobalScope.launch(Dispatchers.Main) { getInfo() }


//                object : AsyncTask<Void, Void, String>() {
//                    override fun doInBackground(vararg params: Void?): String {
//                        return serviceApi.getInfo().execute().body().toString()
//                    }
//
//                    override fun onPostExecute(result: String?) {
//                        super.onPostExecute(result)
//                        P.p(result)
//                        binding.mainTextView.text = result?.filter {
//                            it.code in 0x4e01..0x9fa4
//                        }
//                    }
//
//                }.execute()



                }
            else->{}
        }
    }


    fun goToNextActivity(cls:Class<out Activity>) {
        var i = Intent(this, cls)
        startActivity(i)
    }

    private suspend fun getInfo() {
        val result = get()
        show(result)
    }

    private fun show(result: String) {
        P.p("thread2->${Thread.currentThread()}")
        binding.mainTextView.text = result
    }

    private suspend fun get(): String {
        return withContext(Dispatchers.IO) {
            P.p("thread->${Thread.currentThread()}")
            serviceApi.getInfo().filter { it.code in 0x4e01..0x9fa4 }
        }
    }
}