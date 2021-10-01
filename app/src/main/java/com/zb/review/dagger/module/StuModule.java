package com.zb.review.dagger.module;



import com.zb.review.dagger.bean.Student;

import dagger.Module;
import dagger.Provides;

@Module
public class StuModule {

    @Provides
    Student provideStudent() {
        return new Student();
    }
}
