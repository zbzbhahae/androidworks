package com.zb.reviewjava.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GsonTest {
    public static void main(String[] args) {
        String result = "{\"code\":200,\"message\":\"success!\", \"data\":[{\"age\":18,\"name\":\"jhon\"}, {\"age\":29,\"name\":\"boo\"}]}";
        Gson gson = new Gson();
        Type type = new TypeToken<ResultBean<List<People>>>(){}.getType();
        ResultBean<List<People>> bean = gson.fromJson(result, type);
        int a = 1;
    }
}
