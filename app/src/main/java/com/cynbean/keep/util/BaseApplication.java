package com.cynbean.keep.util;

import android.app.Application;

import com.cynbean.keep.entity.User;

/**
 * Created by tcf24 on 2016/5/27.
 */
public class BaseApplication extends Application {

    private String token = "";

    private BaseApplication(){};

    private static class SingletonHolder {
        private static final BaseApplication INSTANCE = new BaseApplication();
    }
    public static final BaseApplication getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



}
