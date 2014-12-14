package com.example.callum.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class SqliteController extends SQLiteOpenHelper {
    public SqliteController(Context applicationcontext){
        super(applicationcontext, "androidsqlite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE contact (Number TEXT PRIMARY KEY NOT NULL, First_Name TEXT, Surname TEXT, Address TEXT, City TEXT, Post_Code TEXT, Email TEXT, full_name TEXT)";
        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query = "DROP TABLE IF EXISTS contact";
        database.execSQL(query);
        onCreate(database);
    }

    public void insertContact(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("First_Name", queryValues.get("First_Name"));
        values.put("Surname", queryValues.get("Surname"));
        values.put("Number", queryValues.get("Number"));
        values.put("Address", queryValues.get("Address"));
        values.put("City", queryValues.get("City"));
        values.put("Post_Code", queryValues.get("Post_Code"));
        values.put("Email", queryValues.get("Email"));
        database.insert("contact", null, values);
        database.close();
    }

    public void updateContact(HashMap<String, String> queryValues) {
        Log.d("LOG", "Update");
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("First_Name", queryValues.get("First_Name"));
        values.put("Surname", queryValues.get("Surname"));
        values.put("Number", queryValues.get("Number"));
        values.put("Address", queryValues.get("Address"));
        values.put("City", queryValues.get("City"));
        values.put("Post_Code", queryValues.get("Post_Code"));
        values.put("Email", queryValues.get("Email"));
        String strFilter = "Number = " + "'" + queryValues.get("Number") + "'";
        Log.d("LOG", strFilter);
        database.update("contact", values, strFilter, null);
        database.close();
    }

    public void deleteContact(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM contact WHERE Number = '"+ id +"'";
        database.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String, String>> getAllContacts() {
        ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM contact ORDER BY Surname ASC";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Number", cursor.getString(0));
                map.put("First_Name", cursor.getString(1));
                map.put("Surname", cursor.getString(2));
                map.put("Address", cursor.getString(3));
                map.put("City", cursor.getString(4));
                map.put("Post_Code", cursor.getString(5));
                map.put("Email", cursor.getString(6));
                map.put("full_name", cursor.getString(1) + " " + cursor.getString(2));

                wordList.add(map);
            } while (cursor.moveToNext());
        }
        return wordList;
    }
}
