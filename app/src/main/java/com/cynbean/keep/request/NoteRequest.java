package com.cynbean.keep.request;

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

import java.util.List;

/**
 * Created by BFD_303 on 2016/5/3.
 */
public class NoteRequest {

    private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

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
    public static void findAllNotes(String token, final NoteResponse<String> response) {
        RequestParams params = new RequestParams();
        params.put("token", token);

        client.get(Constant.getAbsoluteUrl(Constant.LIST), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String json = responseBody.toString();
                    response.onData(json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

}
