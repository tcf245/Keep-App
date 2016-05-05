package com.cynbean.keep.request;

import com.cynbean.keep.entity.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONObject;


import java.util.List;

/**
 * Created by BFD_303 on 2016/5/3.
 */
public class NoteRequest {

    private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

    private static final String BASE_URL = "https://api.douban.com/v2/";

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public interface NoteResponse<T> {
        void onData(T data);
    }

    public static void searchNotes(String name, final NoteResponse<List<Note>> response) {
        RequestParams params = new RequestParams();
        params.put("q", name);
        params.put("start", 0);
        params.put("end", 50);

        client.get(getAbsoluteUrl(""), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] bytes) {

            }

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

//        client.get(getAbsoluteUrl("book/search"), params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                try {
//                    Gson gson = new Gson();
//                    JSONObject json = new JSONObject(new String(responseBody));
//                    JSONArray jaBooks = json.optJSONArray("notes");
//                    List<Note> notes = gson.fromJson(jaBooks.toString(), new TypeToken<List<Note>>() {
//                    }.getType());
//                    response.onData(notes);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//            }
//        });
    }
}
