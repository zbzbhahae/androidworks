package com.zb.reviewjava.gson.newHttp;//package com.huawei.genexcloud.ltequality.http.newHttp;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONObject;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.huawei.genexcloud.ltequality.http.newHttp.callback.IJsonParser;
//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//import com.huawei.genexcloud.ltequality.http.newHttp.bean.GuaranteeBean;
//import com.huawei.genexcloud.ltequality.http.newHttp.bean.GuaranteeWrapperBean;
//import com.huawei.genexcloud.ltequality.http.newHttp.bean.HttpResultBean;
//import com.huawei.genexcloud.ltequality.http.newHttp.bean.HttpFailure;
//import com.huawei.genexcloud.ltequality.http.newHttp.callback.IHttpCallBack;
//
//import android.content.Context;
//import android.text.TextUtils;
//
///**
// * Created by zWX1094027 on 2021/9/14.
// */
//
//public class ApiService {
//
//    public static final String BAIDU = "http://www.baidu.com";
//    public static final String JAVA_HOST = " https://genexcloud-china-dongguan.sd.huawei.com/mobileinfomgmtapps/wcfMapAction?endPoint=mobile";
//
//    public static final String I_GUARANTEE = "QueryOnGuarantee";
//
//    public static IHttpCallBack callBack;
//
//    public static final Callback callback = new Callback() {
//        @Override
//        public void onFailure(Request request, IOException e) {
//            P.p("request:" + request + "---- exception:" + e);
//        }
//
//        @Override
//        public void onResponse(Response response) throws IOException {
//            P.p("code->" + response.code());
//            P.p("success response message:" + response.message());
//            HttpFailure failure = new HttpFailure();
//
//
//            if(response.isSuccessful()) { //访问成功
//                String result = response.body().string();
//                if(TextUtils.isEmpty(result)) {
//                    failure.setMessage("返回内容为空");
//                    callBack.onFailure(failure);
//                }
//            }
//
//
//
//
//            String result = response.body().string();
//            P.p("   --- body:" + result);
//
//            Type listType = new TypeToken<List<GuaranteeBean>>(){}.getType();
//            Type type = new TypeToken<GuaranteeWrapperBean>(){}.getType();
//
//            HttpResultBean<GuaranteeWrapperBean> bean =
//                    new Gson().fromJson(result, type);
//            int a = 1;
//            a = 2;
//        }
//    };
//
//    public static void getBaidu(Context context) {
//        OkHttpUtil.get(context, BAIDU, null, null, callback);
//    }
//
//    public static void postBCM(Context context) {
//        OkHttpUtil.post(context, JAVA_HOST, null, getHeaders(), callback);
//    }
//
//    public static Map<String, String> getHeaders() {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Cookie", COOKIE);
//        headers.put("X-CSRF-TOKEN", TOKEN);
//        return headers;
//    }
//
//    public static Map<String, String> getBody(String messageName, Map<String, String> param) {
//        Map<String, String> params = new HashMap<>();
//        params.put("messageName", messageName);
//        JSONObject jo = new JSONObject(param);
//        params.put("message", jo.toString());
//        return params;
//    }
//
//    public static void postGuarantee(Context context, String userId, String city, String  operator) {
//        Map<String, String> params = new HashMap<>();
//        params.put("userId", userId);
//        params.put("city", city);
//        params.put("operator", operator);
//        OkHttpUtil.post(context, JAVA_HOST, getBody(I_GUARANTEE, params), getHeaders(), callback);
//    }
//
//    public static void getGuranteeWrapper(Context context, String userId, String city,
//                                          String  operator, IHttpCallBack<GuaranteeWrapperBean> callback) {
//        Map<String, String> params = new HashMap<>();
//        params.put("userId", userId);
//        params.put("city", city);
//        params.put("operator", operator);
//        HttpUtil.post(context, JAVA_HOST, getBody(I_GUARANTEE, params), callback, new IJsonParser<HttpResultBean<GuaranteeWrapperBean>>(){
//            @Override
//            public HttpResultBean<GuaranteeWrapperBean> parsed(String json) {
//                Type type = new TypeToken<HttpResultBean<GuaranteeWrapperBean>>(){}.getType();
//                HttpResultBean<GuaranteeWrapperBean> bean =
//                        new Gson().fromJson(json, type);
//                return bean;
//            }
//        });
//    }
//
//
//
//    public static final String COOKIE = "iLearningXUserName=zWX1094027; lang=zh_CN; authmethod=authpwd; suid=F9-7E-FD-4B-4D-19-76-A2-4B-26-59-14-B0-B5-02-EB; hwsso_am=77-22-F7-0E-84-9F-31-CD; login_sip=1A-B6-05-7A-44-7A-62-A8-A8-F9-57-4F-4E-E3-D9-62-31-DD-35-52-A5-7C-E0-59; hwssot=A3-37-B8-60-60-86-16-4B-F9-C9-82-47-E6-F2-CA-94; hwsso_login=s3nE_bDO86SppHW9UeLz5WKigvIdirPMbXMNmT4mRvrfThWGWG_ad6bTflnF8lFbMoKUaL_b1WSm5_by4LJyNDwsWxQ3FuLvQq3E3mbLUxEIHFoBrgxnmzK4aabo6gNu1owSApafjd9ojnVDhIkYomT6A97_bUCQWdEaIFQ00x_bIzCFXzHOOidULtjORByo_aQ5fNzYFbdJwAAspqwfkfpMkfh5_bIUZgd1B17fWTvxRknkvx0s_btAlYM5ve9PMfCSQEnajaDjiNtV484EsqX6dMoy55Q2cVw5d5YhJkkvdBhV8t58_bbH_a18gsNFgN3RDfdKwOB09KLqG_bJ9_bPB4M3feRo3qA_c_c; login_logFlag=in; w3Token=F9-7E-FD-4B-4D-19-76-A2-A4-CB-48-C1-41-C5-3B-26-66-64-51-83-33-36-50-AE-D3-0D-C0-80-D9-9D-20-EB; login_uid=F9-7E-FD-4B-4D-19-76-A2-4B-26-59-14-B0-B5-02-EB; _sid_=4D-2D-E5-2D-0D-6A-02-90-70-13-9B-20-06-D5-68-4A-A3-D3-7D-99-A7-0E-3D-6A-3C-33-D2-7E-A4-EC-C9-61-3E-07-8D-D5-D3-41-FF-45; login_sid=4D-2D-E5-2D-0D-6A-02-90-70-13-9B-20-06-D5-68-4A-A3-D3-7D-99-A7-0E-3D-6A-3C-33-D2-7E-A4-EC-C9-61-3E-07-8D-D5-D3-41-FF-45; LtpaToken=xsOHrCTLIAlUVP2XBzryGj7RmkqVXxsnzNMFLll+GThNk0U/i3rdpUbmX2uCT+5+y8AUJ4rNmEsRiGddMiVmySkODyYtQWe2+NYrSSuI+4DkReRNRIXPvn8VxZsF+Qx9jPKETZRTPMXOEM63Zmw5UhMxslUXRFGXjZOnOSJ8kJ/d/cWNu+JnvHG/pZPySd5YcftuKPLYZj7oO7+ouARATAvF3pYkHE83wgXcvV0V13FFsuMMHOfLBnGgBoVBEurG9D33tMXHzB1zd0vpfM9coW/QKajfXo6Pa36Nl/Y9ONvlZWiUTSNcYqCFCUckATSrbaA960MNY7WDodDAXxp1XueIW/wpwlL0Qw0I6vlKWE2tjHJm0lyigQFC4GsAXlGr; hwssot3=27574272242470; uid=F9-7E-FD-4B-4D-19-76-A2-4B-26-59-14-B0-B5-02-EB; sip=1A-B6-05-7A-44-7A-62-A8-99-36-43-8F-12-7A-22-CE-31-DD-35-52-A5-7C-E0-59; logFlag=in; al=10; gc_uniportal_login=true; user=zwx1094027; user-id=zwx1094027; userId=zwx1094027; encode-user-id=zwx1094027; ui_version=v3; genexcloud_language=zh_CN; project=ee6869d2-9417-11ea-bda0-287ef888c79a; project-id=ee6869d2-9417-11ea-bda0-287ef888c79a; locale=zh_CN; ui_new_version=v4; lastLoginDisplayed=true; CloudName=DongguanChinaBCM; hwsso_uniportal=zQubrGP_bz3SZ1bkgNF_b5wnAMeUusN5j3f8PWcq26V_am96mTHeQSYgmv0QIOPrTuu10MOioalfazI6Qoq4MIMz01b9gW8i98xHPpnxmmYHqSt7ygRLFj0Nz8zGMUaYWa2qqE22OEAl3obPlwvEVhp_aTq1KE0q_aYg1C7W5YtdKSi5u_bqmwnQ67z7ODCCPOF4Yin6RZ6U_brLIlXLCEz585IdsT7NuuRQ1WdFf_aXXr0ci59z_bBU5ky6BVCeInYr1e1nsnucn6iky15ty_aoTQMAY01iWletOzjCPX2_aL3JhWT75EN_apRL_bmgp_ajQwFQ_aVkw7qCl92IGEGwih6xhQk3TFoSg_c_c; hwssotinter=8D-42-CD-1B-67-56-8E-5C-38-22-66-F8-72-6A-39-36; hwssotinter3=27574670253043; sid=EB-89-3D-CD-CD-02-6F-5D-20-92-0A-16-C4-06-A3-58-55-2E-84-2A-1C-EE-DA-E3-E5-4A-6F-2E-18-AB-7D-1B-96-44-95-73-83-75-AE-76; DongGuanChina_BCM_sid=a36bfc9a-0043-488d-8ebb-7ddd3144948a";
//    public static final String TOKEN = "1afe0d7e-dbb3-499e-9317-eea222071329";
//}
