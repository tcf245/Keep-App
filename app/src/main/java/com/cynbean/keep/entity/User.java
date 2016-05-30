package com.cynbean.keep.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tcf24 on 2016/4/11.
 */
public class User implements Serializable{
    private int id;
    private String account;
    private String password;
    private String expireTime;
    private String token;
    private String createTime;

    private List<Note> notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return account.equals(user.account);

    }

    @Override
    public int hashCode() {
        return account.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", notes=" + notes +
                '}';
    }
}
