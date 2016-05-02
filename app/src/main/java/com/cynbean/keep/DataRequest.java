package com.cynbean.keep;

import android.database.sqlite.SQLiteDatabase;

import com.cynbean.keep.db.NoteDB;
import com.cynbean.keep.entity.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BFD_303 on 2016/4/30.
 */
public class DataRequest {

//    private NoteDB db = new NoteDB();
//    private SQLiteDatabase dbRead = db.getReadableDatabase();
//    private SQLiteDatabase dbWrite = db.getWritableDatabase();
    private List<Note> notes  = new ArrayList<Note>();


//    public List<Note> getData(){
//        return notes = (List<Note>) dbRead.query("notes",null,null,null,null,null,null);
//    }


    public List<Note> getTestData(){
        for(int i = 0;i < 10; i ++){
            Note note = new Note();
            note.setTitle("note" + i);
            note.setContent(R.string.card_content + "");
            note.setColor(i);
            notes.add(note);
        }
        return notes;
    }
}
