package com.zb.roomdetail.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zb.roomdetail.bean.Student;

import java.util.List;

/**
 * 访问数据库的接口
 * 增删改查在此声明
 */
@Dao
public interface StudentDao {

    @Insert
    void insertStudent(Student student);
    @Insert
    void insertStudents(Student... students);

    @Update
    void updateStudent(Student student);
    @Update
    void updateStudents(Student... students);

    @Delete
    void deleteStudent(Student student);
    @Query("Delete FROM STUDENT_TABLE")
    void deleteAllStudents();

    @Query("SELECT * FROM STUDENT_TABLE ORDER BY STUDENT_ID DESC")
    List<Student> getStudentsDESC();

    @Query("SELECT * FROM STUDENT_TABLE ORDER BY STUDENT_ID DESC")
    LiveData<List<Student>> getAllStudents();


}
