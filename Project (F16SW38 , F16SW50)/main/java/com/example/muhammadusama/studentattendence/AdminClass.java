package com.example.muhammadusama.studentattendence;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class AdminClass extends Fragment implements View.OnClickListener {

    ImageButton t,c,subj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.admin_layout,container,false);
         t=view.findViewById(R.id.admin_teacher);
         c=view.findViewById(R.id.admin_class);
        subj=view.findViewById(R.id.admin_subject);
        t.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
       int ids= v.getId();
       switch (ids)
       {
           case R.id.admin_teacher:
             getFragmentManager().beginTransaction().replace(R.id.frame_view,new TeacherClass()).addToBackStack(null).commit();
               break;
           case R.id.admin_class:

               break;
           case R.id.admin_subject:

               break;

       }
    }

    public static class TeacherAdd extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v= super.onCreateView(inflater, container, savedInstanceState);

           return v;
        }
    }
}
