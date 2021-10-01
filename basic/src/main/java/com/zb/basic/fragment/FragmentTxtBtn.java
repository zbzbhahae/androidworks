package com.zb.basic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zb.basic.R;
import com.zb.common.utils.P;

public class FragmentTxtBtn extends BaseFragment {

    private View root;
    private TextView txt;
    private Button btn;

    private String text = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        text = b.getString("message");
        P.p("来自activity的消息:" + text);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null) {
            root = inflater.inflate(R.layout.fragment_text_button,
                    container, false);
        }
        txt = root.findViewById(R.id.fragment_text);
        btn = root.findViewById(R.id.fragment_button);

        txt.setText(text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("Yes, I am, and you?");
            }
        });

        return root;
    }
}
