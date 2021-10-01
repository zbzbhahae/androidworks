package com.zb.basic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zb.common.utils.P;

public class BaseFragment extends Fragment {

    private String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        P.p(getName() + "::onSaveInstanceState()");
    }

    @Override
    public void setInitialSavedState(@Nullable SavedState state) {
        super.setInitialSavedState(state);
        P.p(getName() + "::setInitialSavedState()");
    }




    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        P.p(getName() + "::onViewStateRestored()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        P.p(getName() + "::onHiddenChanged()");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        P.p(getName() + "::onAttach()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        P.p(getName() + "::onActivityCreated()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        P.p(getName() + "::onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        P.p(getName() + "::onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        P.p(getName() + "::onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
    }



    @Override
    public void onStart() {
        super.onStart();
        P.p(getName() + "::onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        P.p(getName() + "::onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        P.p(getName() + "::onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        P.p(getName() + "::onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        P.p(getName() + "::onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        P.p(getName() + "::onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        P.p(getName() + "::onDetach()");
    }
}
