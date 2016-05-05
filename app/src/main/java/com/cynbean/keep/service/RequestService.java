package com.cynbean.keep.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.cynbean.keep.dao.NoteDB;

/**
 * Created by BFD_303 on 2016/5/3.
 */
public class RequestService extends Service {

    private NoteDB db;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        db = new NoteDB(this);

        return null;


    }
}
