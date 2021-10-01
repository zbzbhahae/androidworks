package com.zb.hiltapp.acts;

import androidx.appcompat.app.AppCompatActivity;

import com.zb.hiltapp.bean.Student;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {

//    @Named("shared")
    @Inject
    Student rootStu;
}
