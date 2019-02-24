package com.example.muhammadusama.trythistoo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {StudentInfo.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Query_Listener getQuery_Listener();
    private static AppDatabase INSTANCE;

  public static  AppDatabase database (Context context) {

      if(INSTANCE == null) {
          INSTANCE=Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db-Student").build();
            }
      return  INSTANCE;
  }
    //inserting a student

  //  StudentInfo studentInfo = new StudentInfo();

}
