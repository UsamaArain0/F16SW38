package com.example.muhammadusama.trythistoo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Query_Listener {

    @Query("SELECT * FROM StudentInfo")
    List<StudentInfo> getAllStudent();
    @Insert
    void insert(StudentInfo... student);
    @Delete
    void delete(StudentInfo... student);
    @Update
    void update(StudentInfo... student);
    @Query("SELECT * FROM StudentInfo WHERE StudentID = :name")
    public StudentInfo getStudentWithId(String name);
    @Query("DELETE  FROM StudentInfo WHERE StudentID =:name")
    public void removeStudentWithId(String name);
    @Query("UPDATE StudentInfo SET StudentID =:id WHERE StudentID =:id2")
    public void updateStudentWithId(String id, String id2);

}
