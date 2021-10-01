package com.zb.review.dagger.component;



import com.zb.review.dagger.FirstActivity;
import com.zb.review.dagger.bean.User;
import com.zb.review.dagger.module.AppModule;
import com.zb.review.dagger.module.BindModule;
import com.zb.review.dagger.module.SubModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SubModule.class, BindModule.class})
public interface AppComponent {
    void inject(FirstActivity act);
    @Named("user1")
    User provideUser();
    @Named("user2")
    User providerUser2();

    PersonComponent.Factory personFactoryProvider();
}
