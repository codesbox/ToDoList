package com.example.todolist.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolist.Card;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }
    public void openDb(){
        db = myDbHelper.getWritableDatabase();
    }
    public void insertToDb(List<Card> note){
        db = myDbHelper.getWritableDatabase();
        for (Card card : note){
            ContentValues cv = new ContentValues();
            cv.put(MyConstants.NOTES, card.getText());
            db.insert(MyConstants.TABLE_NAME, null, cv);
        }
    }
    public List<Card> getFromDb(){
        db = myDbHelper.getReadableDatabase();
        List<Card> notes = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MyConstants.NOTES));
            notes.add(new Card(title));
        }
        cursor.close();
        return notes;
    }
    public void deleteAllDb(){
        db.delete(MyConstants.TABLE_NAME, null, null);

    }
    public void closeDb(){
        myDbHelper.close();
    }
}
