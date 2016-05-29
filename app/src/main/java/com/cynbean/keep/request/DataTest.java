package com.cynbean.keep.request;


import com.cynbean.keep.entity.Note;
import com.cynbean.keep.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcf24 on 2016/5/24.
 */
public class DataTest {

    public List<Note> getTestData(){
        List<Note> notes = new ArrayList<Note>();
        for (int i = 0;i < 5;i++){
            Note note = new Note();
            note.setTitle("Title" + 1);
            note.setContent(Constant.TEST_CONTENT);
            notes.add(note);
        }
        return notes;
    }
}
