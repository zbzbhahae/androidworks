package com.zb.review.dagger.component;



import com.zb.review.dagger.SecondActivity;
import com.zb.review.dagger.module.StuModule;
import com.zb.review.dagger.scope.ActScope;

import dagger.Component;

/**
 * Student依赖app  可以使用appComponent中显示声明的资源
 * 如果依赖的组件定义了作用域 则本组件也要声明作用域
 * appComponent常用于提供application的context
 */
@ActScope
@Component(dependencies = AppComponent.class, modules = StuModule.class)
public interface StuComponent {

    void inject(SecondActivity act);
}
