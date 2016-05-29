package com.cynbean.keep.util;

import android.app.Application;

import com.cynbean.keep.entity.User;

/**
 * Created by tcf24 on 2016/5/27.
 */
public class BaseApplication extends Application {

    private String token = "";

    private BaseApplication(){};

    public static BaseApplication getInstance(){
        BaseApplication application = new BaseApplication();
        return application;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
