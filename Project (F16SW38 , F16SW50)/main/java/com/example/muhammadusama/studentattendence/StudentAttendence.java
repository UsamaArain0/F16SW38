package com.example.muhammadusama.studentattendence;

import android.app.Application;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class StudentAttendence extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

           FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
