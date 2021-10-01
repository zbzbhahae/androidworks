package com.zb.basic.acts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zb.basic.R;
import com.zb.basic.fragment.FragmentVM2Act;
import com.zb.basic.mvvm.viewmodel.DataViewModel;
import com.zb.common.utils.P;

import java.util.Random;

public class ViewModelBetweenFragmentActivity extends BaseActivity {

    private TextView txt;
    private Button btn;
    private DataViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model_between_fragment);

        txt = findViewById(R.id.livedata_out_text);
        btn = findViewById(R.id.livedata_out_button);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_container, FragmentVM2Act.class, null).commit();


        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
//        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(DataViewModel.class);
//        viewModel = DataViewModel.get();
        P.p("viewModel:" + viewModel.toString());
        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt.setText(s);
            }
        };
        viewModel.getDataFromFragment().observe(this, observer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getDataFromActivity().setValue("来自activity的消息:" + (char)(new Random().nextInt()));
            }
        });

    }
}