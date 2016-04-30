package com.cynbean.keep;

import com.cynbean.keep.entity.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BFD_303 on 2016/4/30.
 */
public class DataRequest {

    public List<Note> getTestData(){
        List<Note> notes  = new ArrayList<Note>();
        for(int i = 0;i < 10; i ++){
            Note note = new Note();
            note.setTitle("note" + i);
            note.setContent("note Content" + i);
            note.setColor(i);
            notes.add(note);
        }

        return notes;
    }
}
