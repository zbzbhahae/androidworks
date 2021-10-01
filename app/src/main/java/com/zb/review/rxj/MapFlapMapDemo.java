package com.zb.review.rxj;

import com.zb.review.rxj.bean.Course;
import com.zb.review.rxj.bean.Student;
import com.zb.review.rxj.bean.StudentModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

public class MapFlapMapDemo {
    public static void main(String[] args) {
        StudentModel.init();
        useRxjava();

    }

    private static void useRxjava() {
        Observable.fromIterable(StudentModel.getStudents())
                .map(student -> student.getCourseList())
                .flatMap((Function<List<Course>, ObservableSource<Course>>) courses -> Observable.fromIterable(courses))
                .subscribe(course -> P.p(course.getName()));
    }

    private void oldFunction() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Student> students = StudentModel.getStudents();
                for(Student student : students) {
                    List<Course> courseList = student.getCourseList();
                    for(Course course : courseList) {
                        P.p("student->" + student.getName() + "  course->" + course.getName());
                    }
                }
            }
        }).start();
    }
}
