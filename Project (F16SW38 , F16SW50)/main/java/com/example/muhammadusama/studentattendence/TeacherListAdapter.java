package com.example.muhammadusama.studentattendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TeacherListAdapter extends ArrayAdapter<TeacherAttribute> {
    private Context c;
    int r;
    public TeacherListAdapter(Context c, int resource , ArrayList<TeacherAttribute> teacherAttributes)
    {
        super(c,resource,teacherAttributes);
        this.c=c;
        this.r= resource;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        String name= getItem(position).getName();
        String email = getItem(position).getEmail();
        String id = getItem(position).getId();

        TeacherAttribute t__= new TeacherAttribute(name,email,id);

        LayoutInflater layoutInflater = LayoutInflater.from(c);
        convertView= layoutInflater.inflate(r,parent,false);
        TextView t1= convertView.findViewById(R.id.teacher_name_lable);
        TextView t2 = convertView.findViewById(R.id.teacher_id_lable);
         t1.setText(name);
         t2.setText(id);
        return convertView;
    }
}
