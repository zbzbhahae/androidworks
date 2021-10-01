package com.zb.reviewjava.gson.newHttp.callback;

/**
 * Created by zWX1094027 on 2021/9/15.
 * 用来解析服务器返回的json数据
 * 注意:
 *  运行在子线程中
 *  T是HttpResultBean 而不是最终需要的bean
 *  一般写法
 *      new IJsonParser<HttpResultBean<YouOwnBean>> {
 *          <HttpResultBean<YouOwnBean>> parsed(String json) {
 *              其中不需要try catch包裹,HttpUtil中有处理
 *          }
 *      }
 */

public interface IJsonParser<T> {

    T parsed(String json);
}
