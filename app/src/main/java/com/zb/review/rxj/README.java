package com.zb.review.rxj;


/**
 * RxJava 的操作符 实际上就是创建一个Observable给上游，同时在Observable中建立
 * 一个中间的Observer来订阅上游，同时与下游链接起来
 * SubscribeOn其实就是将subscribe方法包装到runnable中 交给schedulers中的worker去
 * 运行
 * ObserveOn是将上游事件存在队列中，包装成runnable 然后通过worker去处理队列中的事件
 *<img width="1203" height="660" src="http://img.mp.itc.cn/upload/20170414/5257cb7deb7b4368a187a447e6eb30ea_th.jpeg" alt="">
 * 
 * 
 */
public class README {
}
