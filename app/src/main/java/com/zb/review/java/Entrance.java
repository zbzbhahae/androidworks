package com.zb.review.java;

import com.zb.review.rxj.P;

import java.nio.charset.StandardCharsets;

public class Entrance {

    public static void main(String[] args) {
        Son s = new Son("B");
        P.p(s.toString());
        P.p("");
//        String c = "我";
//        byte[] b = c.getBytes(StandardCharsets.UTF_8);
//        P.p(b.length + " ----");
    }
}
