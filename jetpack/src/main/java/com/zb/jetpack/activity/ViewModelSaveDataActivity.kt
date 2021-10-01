package com.zb.jetpack.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.zb.jetpack.R
import com.zb.jetpack.mvvm.viewmodel.IntViewModel
import com.zb.jetpack.utils.P


/**
 * 不要向viewmodel中传入context
 * 如果要使用 请继承使用AndroidViewModel中的Application
 */
class ViewModelSaveDataActivity : com.zb.jetpack.activity.BaseActivity(), View.OnClickListener {

    private lateinit var btn:Button
    private lateinit var text:TextView
    private lateinit var viewModel: IntViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_text)

        btn = findViewById(R.id.button)
        text = findViewById(R.id.text_view)


        btn.text = "加一"

        btn.setOnClickListener(this)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application))
            .get(IntViewModel::class.java)
        text.text = "${viewModel.number}"
    }

    override fun onClick(v: View?) {
        P.p("OnClick  number->${viewModel.number}")
        viewModel.number++
        text.text = "${viewModel.number}"
    }
}