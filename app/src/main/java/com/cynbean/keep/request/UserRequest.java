package com.cynbean.keep.request;

import com.alibaba.fastjson.JSON;
import com.cynbean.keep.entity.Note;
import com.cynbean.keep.util.BaseApplication;
import com.cynbean.keep.util.Constant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tcf24 on 2016/5/27.
 */
public class UserRequest {

    private HttpClient client = new DefaultHttpClient();


    /**
     * 用户登录请求
     * @param username  用户名
     * @param password  密码
     *
     * {"data":"8bca21caf5b444bdb0528e168afe6ea6","flag":true,"msg":"sucesess!"}
     */
    public static void loginAsynRequest(String username,String password){

        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);

        client.get(Constant.getAbsoluteUrl(username + Constant.LOGIN), params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = responseBody.toString();
                Map<String,Object> map = (Map<String, Object>) JSON.parse(json);
                String token = (String) map.get("data");
                boolean flag = (boolean) map.get("flag");
                String msg = (String) map.get("msg");

                if (!token.equals("") && token != null){
                    BaseApplication application = BaseApplication.getInstance();
                    application.setToken(token);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }


    /**
     * 用户注册请求
     * @param username 用户名
     * @param password 密码
     *
     * {"flag":true,"msg":"注册成功！"}
     */
    public static void registerAsynRequest(String username,String password){

        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);

        client.get(Constant.getAbsoluteUrl(Constant.REGISTER), params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = responseBody.toString();
                Map<String,Object> map = (Map<String, Object>) JSON.parse(json);

                boolean flag = (boolean) map.get("flag");
                String msg = (String) map.get("msg");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }


    /**
     * 用户登录请求
     * @param username  用户名
     * @param password  密码
     * @return  token  用户登录令牌
     *
     * {"data":"8bca21caf5b444bdb0528e168afe6ea6","flag":true,"msg":"sucesess!"}
     */
    public boolean loginRequest(String username,String password){
        String url = Constant.BASE_URL + username + Constant.LOGIN;
        HttpPost request = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("password", password));

        HttpEntity entity = null;
        HttpResponse response;
        String token = "";
        boolean flag = false;
        String msg = "";

        try {
            entity = new UrlEncodedFormEntity(params,"utf-8");
            //请求对象
            request.setEntity(entity);
            //发送请求
            response = client.execute(request);

            //请求成功
            if (response.getStatusLine().getStatusCode() == Constant.REQUEST_OK){
                System.out.println("post success");
                String json = response.getEntity().getContent().toString();
                Map<String,Object> map = (Map<String, Object>) JSON.parse(json);
                token = (String) map.get("data");
                flag = (boolean) map.get("flag");
                msg = (String) map.get("msg");

                if (!token.equals("") && token != null){
                    BaseApplication application = BaseApplication.getInstance();
                    application.setToken(token);
                }

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 用户注册请求
     * @param  username  用户名
     * @param  password  密码
     *{"flag":true,"msg":"注册成功！"}
     */
    public boolean registerRequest(String username,String password){
        String url = Constant.BASE_URL +  Constant.REGISTER;
        HttpPost request = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));

        HttpEntity entity = null;
        HttpResponse response;
        boolean flag = false;
        String msg = "";

        try {
            entity = new UrlEncodedFormEntity(params,"utf-8");
            //请求对象
            request.setEntity(entity);
            //发送请求
            response = client.execute(request);

            //请求成功
            if (response.getStatusLine().getStatusCode() == Constant.REQUEST_OK){
                System.out.println("post success");
                String json = response.getEntity().getContent().toString();
                Map<String,Object> map = (Map<String, Object>) JSON.parse(json);
                flag = (boolean) map.get("flag");
                msg = (String) map.get("msg");

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
