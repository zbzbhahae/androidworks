package com.zb.hiltapp.module;

import android.content.Context;

import com.tencent.mmkv.MMKV;
import com.zb.hiltapp.bean.Student;

import java.util.Random;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.components.SingletonComponent;

/**
 * InstallIn 表明将模组装在到某个组件上 推荐ApplicationComponent  2.37为SingletonComponent
 */
@Module@InstallIn(SingletonComponent.class)
public class StuModule {

    @Named("shared")
    @Singleton  //表示在同一个activity周期内获得的实例为同一个
    @Provides
    Student provideBean() {
        return new Student("create from module with name ->shared");
    }

    @Provides
    String provideString() {
        return "string from module " + new Random().nextInt();
    }


//    @ActivityScoped  //表示在同一个activity周期内获得的实例为同一个
    @Provides
    Student provideBean2(@ApplicationContext Context context, String str) {
        return new Student(context, str);
    }

    @Singleton
    @Provides
    MMKV provideMMKV(@ApplicationContext Context context) {
        MMKV.initialize(context);
        return MMKV.defaultMMKV();
    }

}
