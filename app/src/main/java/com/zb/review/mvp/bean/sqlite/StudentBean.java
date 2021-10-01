package com.zb.review.mvp.bean.sqlite;

public class StudentBean {
    public static final String GENDER_MALE = "男";
    public static final String GENDER_FEMALE = "女";

    private String name;
    private int age;
    private String gender;
    private float height;
    private float weight;

    public StudentBean(String name, int age, String gender,
                       float height, float weight) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
