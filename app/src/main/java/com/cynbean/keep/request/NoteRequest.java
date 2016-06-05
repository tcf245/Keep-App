package com.cynbean.keep.request;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.cynbean.keep.entity.Note;
import com.cynbean.keep.util.Constant;
import com.cynbean.keep.util.DataResponse;
import com.cynbean.keep.util.MyAsyncHttpResponseHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BFD_303 on 2016/5/3.
 */
public class NoteRequest {

    private static AsyncHttpClient client = new AsyncHttpClient(true,80,443);

    /**
     * 获取用户所有记事请求
     * @param token     用户令牌
     * @param response  数据处理接口
     *
     * {"data":[{"color":0,"content":"测试","createTime":"2016-05-22 16:48:39","id":5,"pic":"noteFile/1463906919033.jpg","status":0,"tags":[],"title":"测试","user":{"$ref":"$.data[0].user"}}],"flag":true,"msg":"sucesess!"}
     */
    public void findAllNotes(String token, final DataResponse<Map<String,Object>> response) {
        Log.i("request  token ==>", token);
        RequestParams params = new RequestParams();
        params.put("token", token);
        Log.d("url ==>",Constant.getAbsoluteUrl(Constant.LIST));
        client.post(Constant.getAbsoluteUrl(Constant.LIST) ,  params,  new MyAsyncHttpResponseHandler(response));
    }

    /**
     * 新增记事请求
     * @param token   用户令牌
     * @param note
     * @param response  数据接口
     */
    public void addNotes(String token, Note note , final DataResponse<Map<String,Object>> response) {

        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("title",note.getTitle());
        params.put("content",note.getContent());
        params.put("color",note.getColor());
//        try {
//            params.put("file",new File(note.getPic()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//
//        }

        Log.d("addNote url ==>", Constant.getAbsoluteUrl(Constant.ADD_NOTE));
        client.post(Constant.getAbsoluteUrl(Constant.ADD_NOTE), params, new MyAsyncHttpResponseHandler(response));
    }


    /**
     * 修改记事请求
     * @param token
     * @param note 记事实例
     * @param response   数据接口
     */
    public void updateNote(String token, Note note, final DataResponse<Map<String,Object>> response) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("title",note.getTitle());
        params.put("content",note.getContent());
        params.put("color",note.getColor());

        Log.d("updateNote url ==>",Constant.getAbsoluteUrl(Constant.EDIT_NOTE + note.getId()));

        client.post(Constant.getAbsoluteUrl(Constant.EDIT_NOTE + note.getId()), params,  new MyAsyncHttpResponseHandler(response));
    }

    /**
     * 删除记事请求
     * @param token
     * @param id
     * @param response
     */
    public void deleteNotes(String token,int id, final DataResponse<Map<String,Object>> response) {
        RequestParams params = new RequestParams();
        params.put("token", token);

        Log.d("delNote url ==>",Constant.getAbsoluteUrl(Constant.DEL_NOTE + id));

        client.post(Constant.getAbsoluteUrl(Constant.DEL_NOTE + id), params,  new MyAsyncHttpResponseHandler(response));
    }

}
