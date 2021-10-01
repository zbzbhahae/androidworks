//package com.zb.reviewjava.gson.newHttp;
//
//import java.io.IOException;
//import java.net.SocketTimeoutException;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//import com.zb.reviewjava.gson.newHttp.bean.HttpFailure;
//import com.zb.reviewjava.gson.newHttp.bean.HttpResultBean;
//import com.zb.reviewjava.gson.newHttp.callback.IHttpCallBack;
//import com.zb.reviewjava.gson.newHttp.callback.IJsonParser;
//
//
///**
// * Created by zWX1094027 on 2021/9/15.
// */
//
//public class HttpUtil {
//
//    private static final Handler handler = new Handler(Looper.getMainLooper());
//    private static final Gson gson = new Gson();
//
//
//    /**
//     * 发送post请求
//     * @param context
//     * @param url
//     * @param params
//     * @param parser    解析回调数据接口 运行于子线程中
//     * @param callBack  回调接口 运行于主线程
//     */
//    public static void post(final Context context, String url,
//                            final Map<String, String> params,
//                            final IHttpCallBack callBack,
//                            final IJsonParser parser) {
//        OkHttpUtil.post(context, url, params, getHeaders(context), new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                String url = request.urlString();
//                HttpFailure failedBean = new HttpFailure();
//                //如果访问的页面是登录页面,而访问失败
//                if (url.equals(Constants.HOST_ADDRESS + "/genexcloud/portal/login.do")) {
//                    Intent intent = new Intent("com.huawei.genexcloud.expire");
//                    context.getApplicationContext().sendBroadcast(intent);
//
//                    GCLogger.debug(Module.HTTP_UTIL, "[] deliveryResult ：Cookie is overdue");
//                    failedBean.setErrorCode(HttpFailure.LOGIN_FAILED);
//                    failedBean.setMessage("登录过期");
//                    sendFailure(callBack, handler, failedBean);
//                    return;
//                }
//
//                if(null != e) { //可能是网络无链接 请求被cancel导致的socket关闭等
//                    failedBean.setErrorCode(HttpFailure.ERROR_EXCEPTION);
//                    failedBean.setMessage(e.toString());
//                    if(e instanceof UnknownHostException) {
//                        failedBean.setShowMessage("网络异常，请检查网络");
//                    } else if(e instanceof SocketTimeoutException) {
//                        failedBean.setShowMessage("请求超时");
//                    }
//                    sendFailure(callBack, handler, failedBean);
//                }
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
////                int respenseCode = response.code();
//                try {
//                    if (response.isSuccessful()) {
//                        String result = response.body().string();
//                        if (TextUtils.isEmpty(result)) { //
//                            sendSuccess(callBack, handler, null, "无内容", -1);
//                        }
//
//                        //验证登录
//                        if (result.contains("<!DOCTYPE HTML"))
//                        {
//                            Intent intent = new Intent("com.huawei.genexcloud.expire");
//                            context.getApplicationContext().sendBroadcast(intent);
//
//                            GCLogger.debug(Module.HTTP_UTIL, "[] deliveryResult ：Cookie is overdue");
//                            sendFailure(callBack, handler, null);
//                            return;
//                        }
//
//
//                        //返回值是json数据
//                        if(isJsonData(result) && result.contains("StatusCode") && result.contains("Message")) {
//                            JSONObject jObject = new JSONObject(result);
//                            int statusCode = jObject.optInt("StatusCode", -1);
//                            String message = jObject.optString("Message");
//                            String data = jObject.optString("JsonData");
//
//                            switch (statusCode) {
//                                case 200:
//                                    HttpResultBean bean = parseJsonToBean(parser, result);
//                                    if(null != bean) {
//                                        sendSuccess(callBack, handler, bean, message, statusCode);
//                                    } else {
//                                        sendSuccess(callBack, handler, null, "解析数据失败", statusCode);
//                                    }
//                                case 202:
//                                case 210://这两个返回码返回的是空值
//                                    sendSuccess(callBack, handler, null, message, statusCode);
//                                    break;
//                                default:
//                                    sendSuccess(callBack, handler, null, message, statusCode);
//                                    break;
//                            }
//                        } else {//不是json字符串或者其中没有message和statuscode
//                            sendSuccess(callBack, handler, null, result, -1);
//                        }
//                    } else { // OKHttp判断的访问是否成功为false
//                        sendSuccess(callBack, handler, null, "访问失败", -1);
//                    }
//                } catch (Exception e) {
//                    if(null != e) {
//                        e.printStackTrace();
//                    }
//                    sendFailure(callBack, handler, null);
//                }
//            }
//        });
//    }
//
//    /**
//     * 判断一段字符串是否是json数据
//     * @param result
//     * @return
//     */
//    private static boolean isJsonData(String result) {
//        if(!result.trim().isEmpty()) {
//            return false;
//        }
//        JsonParser jsonParser = new JsonParser();
//        JsonElement jsonElement = jsonParser.parse(result);
//
//        return jsonElement.isJsonObject();
//    }
//
//    /**
//     * 将返回的json数据解析成HttpResultBean  其中包含StatusCode Message和JsonData
//     * JsonData会根据IJsonParse中传入的泛型进行解析
//     * @param parser
//     * @param result
//     * @return
//     */
//    private static HttpResultBean parseJsonToBean(IJsonParser parser, String result) {
//        HttpResultBean bean = null;
//        if (null != parser && !result.trim().isEmpty()) {
//            try {
//                bean = (HttpResultBean) parser.parsed(result);
//            } catch (Exception e) {
//                GCLogger.error("http", "json数据解析失败");
//            }
//        }
//        return bean;
//    }
//
//    /**
//     * 将网络访问失败信息回调到主线程
//     * @param callBack
//     * @param h
//     * @param failure
//     */
//    private static void sendFailure(final IHttpCallBack callBack, Handler h, final HttpFailure failure) {
//        if(null != callBack) {
//            h.post(new Runnable() {
//                @Override
//                public void run() {
//                    callBack.onFailure(failure);
//                }
//            });
//        }
//    }
//
//
//    private static void sendSuccess(final IHttpCallBack callBack, Handler h,
//                                    final HttpResultBean bean, final String message, final int code) {
//        if(null != callBack) {
//            h.post(new Runnable() {
//                @Override
//                public void run() {
//                    if(null != bean) {
//                        callBack.onSuccess(bean.getData(), bean.getMessage(), bean.getCode());
//                    } else {
//                        callBack.onSuccess(null, message, code);
//                    }
//                }
//            });
//        }
//    }
//
//    /**
//     * 添加认证的header信息  包含Cookie和X-CSRF-TOKEN
//     * @param context
//     * @return
//     */
//    private static Map<String, String> getHeaders(Context context) {
//        long startT = System.currentTimeMillis();
//        Map<String, String> headerMap= new HashMap<>();
//        String sessionId = IPCUtils.getDataToDB(context.getApplicationContext(), Constants.SESSION_ID);
//        if ("".equals(sessionId)) {
//
//            headerMap.put("Cookie", "sid=35d7b57e-2de4-42aa-8a14-cd5530932a86");
//        }
//        else {
//            headerMap.put("Cookie", sessionId);
//        }
//        String phu_csrftoken = IPCUtils.getDataToDB(context.getApplicationContext(), Constants.CSFToken);
//        if ("".equals(phu_csrftoken)) {
//            headerMap.put("X-CSRF-TOKEN", "X-CSRF-TOKEN");
//        }
//        else {
//            headerMap.put("X-CSRF-TOKEN", phu_csrftoken);
//        }
//        long stopT = System.currentTimeMillis();
////        GCLogger.error("zb", "获取cookie信息用时->" + (stopT - startT) + "ms");
//        return headerMap;
//    }
//}
