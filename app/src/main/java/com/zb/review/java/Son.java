package com.zb.review.java;

public class Son extends Father {
    int sonId;
    public Son() {
        super("B", 2);
        sonId = 1;
    }

    public Son(String name) {
        sonId = 100;
    }

    @Override
    public String toString() {
        return "Son{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", sonId=" + sonId +
                '}';
    }
}
