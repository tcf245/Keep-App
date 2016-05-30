package com.cynbean.keep.util;

/**
 * Created by BFD_303 on 2016/5/2.
 */
public class Constant {

    /**
     * Request Param
     */
    public static final String BASE_URL = "http://crawlertest.chinacloudapp.cn:8081/keepapi";
    public static final String LOGIN = "/login.html";
    public static final String REGISTER = "/register.html";
    public static final String LIST = "/note/list";
    public static final String ADD_NOTE = "/note/add";
    public static final String EDIT_NOTE = "/edit/";
    public static final String DEL_NOTE = "/del/";


    public static final int REQUEST_OK = 200;

    public static final String TEST_TOKEN = "e95c11613ba647299142e789beb8a0af";

    /**
     * 返回完整URL
     * @param s  api接口
     * @return   完整url
     */
    public static String getAbsoluteUrl(String s) {
        return Constant.BASE_URL + s;
    }

    /**
     * Table name
     */
    public static final String TABLE_NOTE = "note";
    public static final String TABLE_USER = "user";
    public static final String TABLE_TAG = "tag";


    /**
     * DataBase field
     */
    public static final String FIELD_ID = "id";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_CONTENT = "content";
    public static final String FIELD_COLOR = "color";
    public static final String FIELD_CREATE_TIME = "create_time";
    public static final String FIELD_UPDATE_TIME = "update_time";
    public static final String FIELD_PIC = "pic";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_STATUS = "status";


    public static final String TEST_CONTENT = "《失控》成书于1994年，作者是《连线》杂志的创始主编凯文·凯利。这本书所记述的，是他对当时科技、社会和经济最前沿的一次漫游，以及借此所窥得的未来图景";

    public static final String TOKEN_FILE  = "token.my";
}
