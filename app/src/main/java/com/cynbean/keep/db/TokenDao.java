package com.cynbean.keep.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by tcf24 on 2016/5/30.
 */
public class TokenDao {

    private DataHelper mHelper;
    private final String TOKEN = "TOKEN";

    public TokenDao(Context context) {
        this.mHelper = new DataHelper(context);
    }

    public void insert(String token) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOKEN, token);

        db.insert(TOKEN, null, values);
        db.close();
    }

    public void delete() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from " + TOKEN);
        db.close();
    }

    public String getToken() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.query(TOKEN, null, null, null, null, null, null);
        String token = "";

        if(c.moveToFirst()){
            token = c.getString(c.getColumnIndex(TOKEN));
        }else{
            return "";
        }

        db.close();
        return token;
    }
}
