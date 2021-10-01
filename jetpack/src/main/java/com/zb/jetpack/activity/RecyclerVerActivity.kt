package com.zb.jetpack.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zb.jetpack.R
import com.zb.jetpack.adapter.ListAdapter

class RecyclerVerActivity : com.zb.jetpack.activity.BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        recyclerView = findViewById(R.id.recycler_view)
        adapter = ListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        initData()
        initListener()

    }

    private fun initListener() {
        adapter.listener = object : ListAdapter.OnItemClicked {
            override fun onClick(position: Int) {
                when(position) {
                    0->{goToActivity(MainActivity::class.java)}
                    1->{goToActivity(ServiceLifeCycleActivity::class.java)}
                    2->{goToActivity(ViewModelSaveDataActivity::class.java)}
                    3->{goToActivity(com.zb.jetpack.activity.LiveDataActivity::class.java)}
                    4->{goToActivity(com.zb.jetpack.activity.LVVMInFragmentActivity::class.java)}
                    5->{goToActivity(UseDatabindingActivity::class.java)}
                    6->{goToActivity(UseBindingAdapterActivity::class.java)}
                    7->{goToActivity(TwoWayBindingActivity::class.java)}
                    8->{goToActivity(TwoWayBindingActivity2::class.java)}
                    9->{goToActivity(RecyclerDataBindingactivity::class.java)}
                    10->{goToActivity(MvvmActivity::class.java)}
                    else->{}
                }
            }

        }
    }

    private fun initData() {
        val data = listOf("监听activity生命周期",
            "监听service生命周期",
            "ViewModel使用",
            "LiveData使用",
            "ViewModel LiveData在fragment间共享数据",
            "databinding初体验",
            "使用bindingadapter加载网络图片",
            "双向绑定",
            "双向绑定2",
            "Recycler中使用databinding",
            "Mvvm计分板")
        adapter.data = data
    }

    private fun goToActivity(cls: Class<out Activity>) {
        val i = Intent(this, cls)
        startActivity(i)
    }
}