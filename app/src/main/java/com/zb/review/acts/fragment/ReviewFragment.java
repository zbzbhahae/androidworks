package com.zb.review.acts.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zb.review.utils.P;
import com.zb.review.R;

import org.jetbrains.annotations.NotNull;

public class ReviewFragment extends BaseFragment implements View.OnClickListener {

    private OnClickListener listener;
    private Button button;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public @org.jetbrains.annotations.Nullable View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        P.p("onCreateView (fragment) from " + getClass().getSimpleName());
        View view = inflater.inflate(R.layout.fragment_review_fragment, container, false);
        button = view.findViewById(R.id.fragment_review_button);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(null != listener)
            listener.onClick(this, v);
    }


    public interface OnClickListener {
        void onClick(Fragment fragment, View view);
    }
}
