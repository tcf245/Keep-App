package com.cynbean.keep.request;

import android.util.Log;

import com.cynbean.keep.util.Constant;
import com.cynbean.keep.util.DataResponse;
import com.cynbean.keep.util.MyAsyncHttpResponseHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.Map;

/**
 * Created by tcf24 on 2016/5/31.
 */
public class TagRequest {
    private static AsyncHttpClient client = new AsyncHttpClient(true,80,443);

    /**
     * 获取用户所有标签请求
     * @param token     用户令牌
     * @param response  数据处理接口
     *
     * {"data":[{"color":0,"content":"测试","createTime":"2016-05-22 16:48:39","id":5,"pic":"noteFile/1463906919033.jpg","status":0,"tags":[],"title":"测试","user":{"$ref":"$.data[0].user"}}],"flag":true,"msg":"sucesess!"}
     */
    public void findAllTags(String token, final DataResponse<Map<String,Object>> response) {
        Log.i("request  token ==>", token);
        RequestParams params = new RequestParams();
        params.put("token", token);
        Log.d("url ==>", Constant.getAbsoluteUrl(Constant.TAG_LIST));
        client.post(Constant.getAbsoluteUrl(Constant.TAG_LIST) ,  params,  new MyAsyncHttpResponseHandler(response));
    }
}
