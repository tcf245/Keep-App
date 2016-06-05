package com.cynbean.keep.util;

import com.cynbean.keep.R;

/**
 * Created by BFD_303 on 2016/5/2.
 */
public class Constant {

    /**
     * Request Param
     */

    public static final String BASE_URL = "http://crawlertest.chinacloudapp.cn:8081/";
//    public static final String BASE_URL = "http://192.168.1.112:8080/";

    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String LIST = "note/list";
    public static final String ADD_NOTE = "note/add";
    public static final String EDIT_NOTE = "note/edit/";
    public static final String DEL_NOTE = "note/del/";
    public static final String UPDATE_USER = "user/updateUser";
    public static final String TAG_LIST = "tag/list";


    public static final int REQUEST_OK = 200;

    public static final String TEST_TOKEN = "e95c11613ba647299142e789beb8a0af";
    public static String KEY_WORD = "三国演义";

    /**
     * 返回完整URL
     * @param s  api接口
     * @return   完整url
     */
    public static String getAbsoluteUrl(String s) {
        return Constant.BASE_URL + s;
    }

    /**
     * 颜色
     * @param i
     * @return
     */
    public static int getColor(int i){
        switch(i){
            case 1:
                return R.color.red;
            case 2:
                return R.color.green;
            case 3:
                return R.color.yello;
            case 4:
                return R.color.blue;
            case 5:
                return R.color.red_light;
            case 6:
                return R.color.green_light;
            case 7:
                return R.color.yello_light;
            case 8:
                return R.color.blue_light;
            default: return R.color.white;
        }
//        return R.color.white;
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
