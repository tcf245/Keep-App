package com.cynbean.keep.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tcf24 on 2016/5/30.
 */
public class DataHelper extends SQLiteOpenHelper{

    public DataHelper(Context context) {
        super(context, "Keep", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "CREATE TABLE TOKEN (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "TOKEN TEXT DEFAULT '' NOT NULL" +
                ");";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
