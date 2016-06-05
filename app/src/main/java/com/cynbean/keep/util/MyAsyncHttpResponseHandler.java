package com.cynbean.keep.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tcf24 on 2016/5/30.
 */
public class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

    private DataResponse<Map<String,Object>> response;

    public MyAsyncHttpResponseHandler(DataResponse<Map<String, Object>> response) {
        this.response = response;
    }

    Map<String, Object> map = new HashMap<String, Object>();
    String json = "";
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        if(responseBody != null){
            json = new String(responseBody);
            Log.i("onSuccess  json -->", json);
            map = (Map<String, Object>) JSON.parse(json);
            response.onData(map);
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        if(responseBody != null){
            json = new String(responseBody);
            map = (Map<String, Object>) JSON.parse(json);
            map.put("flag",false);
            Log.e("onFailure  json  ---> ",  json);
            response.onData(map);
        }
    }
}
