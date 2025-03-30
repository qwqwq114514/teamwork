package com.example.shorthandtime;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public abstract class Controller {
    List<Item> itemList;

    MyDBOpenHelper dbHelper = new MyDBOpenHelper(null, "shorthand_time", null, 1);

    SQLiteDatabase db = dbHelper.getWritableDatabase();

    abstract void updateList();

}
