package com.zb.review.dagger.module;



import com.zb.review.dagger.bean.Person;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonModule {

    @Provides
    Person providePerson() {
        return new Person();
    }
}
