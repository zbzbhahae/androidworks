package com.zb.review.rxj.bean;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    static List<Student> students;
    public static void init() {
        students = new ArrayList<>();
        for(int i=0; i<10; i++) {
            List<Course> list = new ArrayList<>();
            list.add(new Course("语文"));
            list.add(new Course("数学"));
            list.add(new Course("英语"));
            list.add(new Course("物理"));
            Student s = new Student("stu" + i, list);
            students.add(s);
        }
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static void setStudents(List<Student> students) {
        StudentModel.students = students;
    }
}
