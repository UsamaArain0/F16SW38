package com.example.muhammadusama.trythistoo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class StudentInfo{

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "StudentID")
    private String s_ID;
    @ColumnInfo(name = "Name")
    String s_Name;
    @ColumnInfo(name = "Department")
    String s_Department;

    public void setS_ID(String id)
    {
        s_ID=id;
    }
    public void setS_Name(String n)
    {
        s_Name=n;
    }
    public void setS_Department(String d)
    {
        s_Department=d;
    }
    public String getS_ID()
    {
       return s_ID;
    }
    public String getS_Name()
    {
        return s_Name;
    }
    public String getS_Department()
    {
        return s_Department;
    }



}