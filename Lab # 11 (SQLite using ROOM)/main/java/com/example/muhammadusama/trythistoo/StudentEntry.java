package com.example.muhammadusama.trythistoo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentEntry extends AppCompatActivity {


    //Button save;
    EditText s_name, s_depart, s_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_entry);
        s_name = findViewById(R.id.studentName);
        s_depart = findViewById(R.id.studentDepartment);
        s_id = findViewById(R.id.studentID);
    }
    public void saveTask(View view) {
        final String id = s_id.getText().toString().trim();
        final String name = s_name.getText().toString().trim();
        final String depart= s_depart.getText().toString().trim();
        if (id.isEmpty()) {
            s_id.setError("ID required");
            s_id.requestFocus();
            return;
        }
        else if (name.isEmpty()) {
            s_name.setError("Name required");
            s_name.requestFocus();
            return;
        }
        else if (depart.isEmpty()) {
            s_depart.setError("Department required");
            s_depart.requestFocus();
            return;
        }
        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                //saving student
                StudentInfo s = new StudentInfo();
                s.setS_ID(id);
                s.setS_Department(depart);
                s.setS_Name(name);
                //adding to database
                AppDatabase.database(getApplicationContext()).getQuery_Listener().insert(s);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(StudentEntry.this, MainActivity.class));
                Toast.makeText(StudentEntry.this, "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }
}
