package com.zb.basic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zb.basic.R;

public class FragmentB extends Fragment {


    View rootView;

    private TextView txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        }
        txt = rootView.findViewById(R.id.fragment_blank_txt);
//        savedInstanceState
        txt.setText("IM FRAGMENT BBBB");


        return rootView;
    }
}
