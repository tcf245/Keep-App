package com.cynbean.keep.request;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.cynbean.keep.entity.Note;
import com.cynbean.keep.util.Constant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BFD_303 on 2016/5/3.
 */
public class NoteRequest {

    private static AsyncHttpClient client = new AsyncHttpClient(true,80,443);

    public interface NoteResponse<T> {
        void onData(T data);
    }

    /**
     * 获取用户所有记事请求
     * @param token     用户令牌
     * @param response  数据处理接口
     *
     * {"data":[{"color":0,"content":"测试","createTime":"2016-05-22 16:48:39","id":5,"pic":"noteFile/1463906919033.jpg","status":0,"tags":[],"title":"测试","user":{"$ref":"$.data[0].user"}}],"flag":true,"msg":"sucesess!"}
     */
    public static void findAllNotes(String token, final NoteResponse<Map<String,Object>> response) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        Log.d("url ==>",Constant.getAbsoluteUrl(Constant.LIST));
        client.post(Constant.getAbsoluteUrl(Constant.LIST), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody);
                    Log.e("json -->",json);
                    Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
                    response.onData(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.e("request", "onFailure :: " + bytes);
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("msg","网络连接超时");
                response.onData(map);
            }
        });
    }


    /**
     * 新增记事请求
     * @param token   用户令牌
     * @param note
     * @param response  数据接口
     */
    public static void addNotes(String token, Note note , final NoteResponse<Map<String,Object>> response) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("title",note.getTitle());
        params.put("content",note.getContent());
        params.put("color",note.getColor());
        params.put("createTime",note.getCreate_time());


        Log.d("addNote url ==>",Constant.getAbsoluteUrl(Constant.ADD_NOTE));
        client.post(Constant.getAbsoluteUrl(Constant.ADD_NOTE), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody);
                    Log.e("json -->",json);
                    Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
                    response.onData(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.e("addNote request", "onFailure :: " + bytes);
            }
        });
    }


    /**
     * 修改记事请求
     * @param token
     * @param note 记事实例
     * @param response   数据接口
     */
    public static void updateNote(String token, Note note , final NoteResponse<Map<String,Object>> response) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("title",note.getTitle());
        params.put("content",note.getContent());
        params.put("color",note.getColor());

        Log.d("updateNote url ==>",Constant.getAbsoluteUrl(Constant.EDIT_NOTE + note.getId()));

        client.post(Constant.getAbsoluteUrl(Constant.EDIT_NOTE + note.getId()), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody);
                    Log.e("json -->",json);
                    Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
                    response.onData(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.e("updateNote request", "onFailure :: " + bytes);
            }
        });
    }

    /**
     * 删除记事请求
     * @param token
     * @param id
     * @param response
     */
    public static void deleteNotes(String token,int id, final NoteResponse<Map<String,Object>> response) {
        RequestParams params = new RequestParams();
        params.put("token", token);

        Log.d("delNote url ==>",Constant.getAbsoluteUrl(Constant.DEL_NOTE + id));

        client.post(Constant.getAbsoluteUrl(Constant.DEL_NOTE + id), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody);
                    Log.e("json -->",json);
                    Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
                    response.onData(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.e("delNote request", "onFailure :: " + bytes);
            }
        });
    }

}
