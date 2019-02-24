package com.example.muhammadusama.studentattendence;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addNewAddedTeacher extends Fragment {
    EditText teacher_n,teacher_e,teacher_id;
    TeacherAttribute teacherAttribute;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    Button addToDatabase;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.adding_new_teacher_layout,container,false);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Teachers");
       progressDialog= new ProgressDialog(getContext());
        teacher_e=view.findViewById(R.id.teacher_email);
        teacherAttribute= new TeacherAttribute();
        teacher_n=view.findViewById(R.id.teacher_name);
        teacher_id=view.findViewById(R.id.teacher_id);
        addToDatabase=view.findViewById(R.id.add_to_database);
        addToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher(teacher_n.getText().toString(),teacher_e.getText().toString(),teacher_id.getText().toString());
            }


        });


        return  view;
    }
    private void addTeacher(String name,String email,String id){
        if (!validateForm()) {
            return;
        }
        progressDialog.setMessage("Adding Teacher to Database...");
        progressDialog.show();
        String key= databaseReference.push().getKey();
        TeacherAttribute t = new TeacherAttribute(name,email,id);
        databaseReference.child(key).setValue(t);
       progressDialog.dismiss();
        Toast.makeText(getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = teacher_e.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            teacher_e.setError("Required.");
            valid = false;
        } else {
            teacher_e.setError(null);
        }

        String t_name = teacher_n.getText().toString().trim();
        if (TextUtils.isEmpty(t_name)) {
            teacher_n.setError("Required.");
            valid = false;
        } else {
            teacher_n.setError(null);
        }
        String id = teacher_id.getText().toString().trim();
        if(TextUtils.isEmpty(id))
        {
            teacher_id.setError("Required");
            valid=false;
        } else {
            teacher_id.setError(null);
        }

        return valid;
    }
}
