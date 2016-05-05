package com.cynbean.keep.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.cynbean.keep.entity.Note;
import com.cynbean.keep.util.Constant;

/**
 * Created by BFD_303 on 2016/5/3.
 */
public class NoteDao {
    private NoteDB db;
    private SQLiteDatabase dbRead,dbWirte;

    public void insert(Note note){
        dbWirte = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constant.FIELD_ID,note.getId());
        cv.put(Constant.FIELD_TITLE,note.getTitle());
        cv.put(Constant.FIELD_CONTENT,note.getContent());
        cv.put(Constant.FIELD_COLOR,note.getColor());
        cv.put(Constant.FIELD_ID,note.getId());
        cv.put(Constant.FIELD_ID,note.getId());
        cv.put(Constant.FIELD_ID,note.getId());
        cv.put(Constant.FIELD_ID,note.getId());
        cv.put(Constant.FIELD_ID,note.getId());


    }

}
