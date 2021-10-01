package com.zb.hiltapp.acts;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.tencent.mmkv.MMKV;
import com.zb.hiltapp.P;
import com.zb.hiltapp.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HiltActivity2 extends BaseActivity {

    @Inject
    MMKV mmkv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt);

        P.p(" " + rootStu.info + "  " + rootStu);
        P.p("2-> mmkv is : " + mmkv + "  and v is ->" + mmkv.getString("key", ""));
    }
}
