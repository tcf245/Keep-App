package com.cynbean.keep.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.cynbean.keep.entity.Note;
import com.cynbean.keep.entity.User;

/**
 *
 * Created by BFD_303 on 2016/5/3.
 */
public class DateService extends Service{


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void pull(User user){

    }

    public void push(Note note){

    }

}
