package com.example.muhammadusama.studentattendence;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TeacherClass extends Fragment {
    Button addNewTeacher;
    Button seeAvailableTeacher;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.teacher_layout,container,false);

        addNewTeacher=view.findViewById(R.id.add_new_teacher);
        seeAvailableTeacher=view.findViewById(R.id.teacher_available_currently);
        addNewTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_view,new addNewAddedTeacher()).addToBackStack(null).commit();

            }
        });
        seeAvailableTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_view,new handleAvailableTeacher()).addToBackStack(null).commit();

            }
        });

        return view;
    }

}
