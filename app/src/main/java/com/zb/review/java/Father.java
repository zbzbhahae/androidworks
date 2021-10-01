package com.zb.review.java;

public class Father {

    String name;
    int id;

    public Father() {
        name = "A";
        id = 1;
    }

    public Father(String name) {
        this.name = name;
    }

    public Father(int id) {
        this.id = id;
    }

    public Father(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Father{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
