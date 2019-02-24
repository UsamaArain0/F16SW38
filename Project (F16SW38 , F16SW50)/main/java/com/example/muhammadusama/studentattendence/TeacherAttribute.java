package com.example.muhammadusama.studentattendence;

public class TeacherAttribute {
private String name,email,id;

 public TeacherAttribute(){

 }
public TeacherAttribute(String name,String email,String id)
{
    this.name=name;
    this.email=email;
    this.id=id;
}
public void setName(String name){
    this.name=name;
}
    public void setEmail(String email){
        this.email=email;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
