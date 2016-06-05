package com.cynbean.keep.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tcf24 on 2016/5/31.
 */
public class ImgUtils {
    private Handler handle = new Handler();

    public void setImageBitmap(final String url,ImageView img,Thread thread){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Bitmap bmp = getURLimage(url);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = bmp;
                System.out.println("000");
                handle.sendMessage(msg);
            }
        });
    }

        //在消息队列中实现对控件的更改
            public void handleMessage(Message msg,ImageView img) {
                switch (msg.what) {
                    case 0:
                        System.out.println("111");
                        Bitmap bmp=(Bitmap)msg.obj;
                        img.setImageBitmap(bmp);
                        break;
                }
            };


    //加载图片
    public static Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

}
