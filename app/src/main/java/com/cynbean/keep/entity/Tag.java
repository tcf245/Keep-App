package com.cynbean.keep.entity;

import java.util.List;

/**
 * Created by tcf24 on 2016/4/11.
 */
public class Tag {
    private int id;
    private String title;
    private List<Note> notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
