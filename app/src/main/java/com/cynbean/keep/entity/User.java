package com.cynbean.keep.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tcf24 on 2016/4/11.
 */
public class User implements Serializable{
    private int id;
    private String username;
    private String password;

    private List<Note> notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
