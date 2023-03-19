package com.example.todolist.db;

import android.provider.BaseColumns;

public class MyConstants{
    public static final String TABLE_NAME = "items";
    public static final String _ID = "_id";
    public static final String NOTES = "notes";
    public static final String DB_NAME = "my_db.db";
    public static final int DB_VERSION = 1;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOTES + " TEXT)";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
