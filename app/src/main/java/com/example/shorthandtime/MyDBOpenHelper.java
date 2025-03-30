package com.example.shorthandtime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory,
                          int version) {
        super(context, name, null, version);
    }

    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE NOTE(created_time INTEGER PRIMARY KEY, last_updated_time INTEGER, content TEXT, tag TEXT)");
        DB.execSQL("CREATE TABLE SCHEDULE(created_time INTEGER PRIMARY KEY, last_updated_time INTEGER, content TEXT, trigger_time INTEGER, period TEXT)");
    }

    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
    }
}