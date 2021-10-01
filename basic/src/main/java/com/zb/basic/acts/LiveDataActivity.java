package com.zb.basic.acts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zb.basic.R;
import com.zb.basic.mvvm.viewmodel.DataViewModel;
import com.zb.basic.mvvm.viewmodel.DataViewModelWithArgs;
import com.zb.basic.mvvm.viewmodel.DataViewModelWithArgsFactory;
import com.zb.common.utils.P;

import java.util.Random;

public class LiveDataActivity extends BaseActivity {


    private TextView txt;
    private Button btn;
    private DataViewModel viewModel;
    private DataViewModelWithArgs viewModelWithArgs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        txt = findViewById(R.id.livedata_text);
        btn = findViewById(R.id.livedata_button);


        //使用LiveData有4步
        //1.提供ViewModel ，里面有LiveData等等。写get方法
        //2.在activity中通过new ViewModelProvider().get() 加载所写的ViewModel
        //3.创建观察者用来观察属性改变
        //4.将观察者设置到第二步中获得的ViewModel的对象的get方法返回的MutableLiveData中去
        //5.如果想改变属性值，那么调用MutableLiveData的setValue或者postValue方法 (据说非主线程用post)
        //注意。。ViewModel这种方式加载出来只有唯一实例
        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        P.p(viewModel.toString());
        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        P.p(viewModel.toString());

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt.setText(s);
            }
        };

        viewModel.getName().observe(this, nameObserver);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new Random().nextInt() % 2 == 0) {
                    LiveDataActivity.this.viewModel.getName().setValue("我改变，故我在" + (char)(new Random().nextInt()));
                } else {
                    LiveDataActivity.this.viewModel.getName().postValue("我改变，故我在" + (char)(new Random().nextInt()));
                }

            }
        });

        /****************************************带参数的viewmodel构建使用************************/
        viewModelWithArgs = new ViewModelProvider(this,
                new DataViewModelWithArgsFactory(true))
                .get(DataViewModelWithArgs.class);

        final Observer<String> messageObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt.setText(s);
            }
        };

        viewModelWithArgs.getMessage().observe(this, nameObserver);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new Random().nextInt() % 2 == 0) {
                    LiveDataActivity.this.viewModelWithArgs.getMessage().setValue("带参数的viewmodel" + (char)(new Random().nextInt()));
                } else {
                    LiveDataActivity.this.viewModelWithArgs.getMessage().postValue("带参数的viewmodel" + (char)(new Random().nextInt()));
                }

            }
        });
    }
}
