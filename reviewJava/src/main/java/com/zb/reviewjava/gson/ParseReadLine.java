package com.zb.reviewjava.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zb.reviewjava.gson.bean.ReadLineBean;
import com.zb.reviewjava.gson.bean.ResponseBean;
import com.zb.reviewjava.gson.data.NetQualityDataSet;

import java.lang.reflect.Type;

public class ParseReadLine {
    public static void main(String[] args) {
        String result = NetQualityDataSet.DATA_REDLINE;

        Gson g = GsonUtil.getGson();

        Type type = new TypeToken<ResponseBean<ReadLineBean>>(){}.getType();
        ResponseBean rb = g.fromJson(result, type);
        int a = 1;

    }
}
