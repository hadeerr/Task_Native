package com.example.task_native.model;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {SearchQuery.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract SearchQueryDAO searchQueryDAO();

    static  AppDatabase instance;
    public static AppDatabase getInstance(Context context){
        return  (instance == null)? instance = Room.databaseBuilder(context,
                AppDatabase.class , "MyDatabase").allowMainThreadQueries().build():instance;
    }

}
