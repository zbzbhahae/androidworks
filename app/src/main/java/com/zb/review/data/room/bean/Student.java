package com.zb.review.data.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "table_student")
public class Student {

    public static final char male = '男';
    public static final char female = '女';

    /** 测试迁移  1-2 需要在这里写上增加的相应字段*/
//    @ColumnInfo(name = "info")
//    public String info;

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

//    @ColumnInfo(name = "age", defaultValue = "18")
    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "gender")
    public char gender;

    @ColumnInfo(name = "height")
    public float height;

    @ColumnInfo(name = "weight")
    public float weight;

    @Ignore
    private int useless;
    @Ignore
    private int errorTest;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return uid == student.uid;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
