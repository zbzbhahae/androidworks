package com.zb.review.dagger.module;

import com.zb.review.dagger.FirstActivity;
import com.zb.review.dagger.interfaces.AImpl;
import com.zb.review.dagger.interfaces.BImpl;
import com.zb.review.dagger.interfaces.InterfaceA;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * 使用bind注解
 * 通过bind注解 来提供Interface接口的实现类的注入
 * 注入处InterfaceA  xxx;   该模组会提供实现类的创建 绑定到实现的接口类型上
 * 详情见{@link FirstActivity}
 * 因为是抽象类 所以提供方法使用static
 */
@Module
public abstract class BindModule {

    @Binds
    abstract InterfaceA provideAimpls(AImpl impl);

    @Provides
    static AImpl provideAimpl() {
        return new AImpl();
    }

    @Provides
    static BImpl provideBimpl() {
        return new BImpl();
    }
}
