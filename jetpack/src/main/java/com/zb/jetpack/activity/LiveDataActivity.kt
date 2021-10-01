package com.zb.jetpack.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.zb.jetpack.R
import com.zb.jetpack.mvvm.viewmodel.IntViewModel
import com.zb.jetpack.mvvm.viewmodel.LiveDataViewModel
import com.zb.jetpack.utils.P
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class LiveDataActivity : com.zb.jetpack.activity.BaseActivity(), View.OnClickListener {


    private lateinit var btn: Button
    private lateinit var text: TextView
    private lateinit var viewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        btn = findViewById(R.id.button)
        text = findViewById(R.id.text_view)



        btn.text = "加一"

        btn.setOnClickListener(this)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application))
            .get(LiveDataViewModel::class.java)


        viewModel.data.observe(this) {
            text.text = "$it"
        }

        startTimer()
    }

    //每秒更新一次数据
    private fun startTimer() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                P.p("Timer run")
                //非ui线程使用postvalue
                //ui线程使用setvalue
                viewModel.data.postValue(viewModel.data.value!! + 1)
            }

        }, 1000, 1000)
    }

    override fun onClick(v: View?) {
        P.p("OnClick  number->${viewModel.data.value }")
        viewModel.data.postValue(viewModel.data.value!! + 1)
    }

}