package com.zb.review.dagger.module;



import com.zb.review.dagger.bean.User;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 同一个类的多个实现方法 使用named注解（Qualifier的实现）
 * 注入的地方写
 * @Inject
 * @Named("user1")
 * User xxx
 *
 * 注意 如果提供了多种创建方式，那么之前没有声明@Named的地方
 * 需要全部声明
 * 且依赖该module所在的组件的地方，显式声明的方法也应该加上@Named
 * 或者提供一个默认的方法(不加@Named 的提供方法)
 * @Named 与作用域注解同理，也可以自定义
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    User provideDefaultUser() {
        return new User("create default user from module");
    }

    @Named("user1")
    @Singleton
    @Provides
    User provideUser(){
        return new User("create from module");
    }

    @Named("user2")
    @Singleton
    @Provides
    User provideUser2() {
        return new User("create from module fun2");
    }

}
