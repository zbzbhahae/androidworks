package com.zb.reviewjava.gson;

import com.google.gson.Gson;

public class GsonUtil {
    private static Gson gson = new Gson();
    public static Gson getGson() {
        return gson;
    }
}
