package com.zb.review.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zb.review.data.room.bean.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM table_student")
    List<Student> getALL();

    @Query("SELECT * FROM table_student WHERE gender like :genderVar")
    List<Student> getStudentByGender(String genderVar);

    @Insert
    void insertAll(List<Student> students);

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student student);

    @Query("DELETE FROM table_student")
    void deleteAll();


}
