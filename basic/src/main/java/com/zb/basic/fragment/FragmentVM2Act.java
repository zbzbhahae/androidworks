package com.zb.basic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zb.basic.R;
import com.zb.basic.mvvm.viewmodel.DataViewModel;
import com.zb.common.utils.P;

import java.util.Random;

public class FragmentVM2Act extends Fragment {

    View rootView;

    private TextView txt;
    private Button btn;
    private DataViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        }
        txt = rootView.findViewById(R.id.fragment_blank_txt);
        btn = rootView.findViewById(R.id.fragment_blank_btn);

//        savedInstanceState
        txt.setText("IM FRAGMENT AAAA");

        initViewModelBinding();
        return rootView;

    }

    private void initViewModelBinding() {
//        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
//        viewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(DataViewModel.class);
        viewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
//        viewModel = DataViewModel.get();
        P.p("viewModel->" + viewModel.toString());
        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt.setText(s);
            }
        };
        viewModel.getDataFromActivity().observe(getViewLifecycleOwner(), observer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getDataFromFragment().setValue("来自fragment的消息:" + (char)(new Random().nextInt()));
            }
        });

    }
}
