package com.zb.review.dagger.component;



import com.zb.review.dagger.ThirdActivity;
import com.zb.review.dagger.module.PersonModule;

import dagger.Subcomponent;

/**
 * 使用子组件  PersonComponent为AppComponent的子组件
 * 需要声明为Subcomponent 并创建提供子组件的工厂接口 标注为@Subcomponent.Factory
 * 同时 也要创建一个SubModule  在submodele注解中指明存在的子组件类 并将module交给父组件
 * 子组件注入时需要从父组件创建子组件Factory调用创建方法来注入
 * PersonComponent.Factory personFactoryProvider(); 方法要声明在父组件中
 */
@Subcomponent(modules = PersonModule.class)
public interface PersonComponent {
    @Subcomponent.Factory
    interface Factory {
        PersonComponent create();
    }
    void inject(ThirdActivity act);
}
