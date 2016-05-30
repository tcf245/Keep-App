package com.cynbean.keep.util;

import android.content.Context;
import android.util.Log;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by tcf24 on 2016/5/29.
 */
public class FileUtil {

    //写数据
    public static void writeFile(String fileName,String writestr,Context context) throws IOException {
        try{
            createFile(Constant.TOKEN_FILE,context);
            FileOutputStream fout = context.openFileOutput(fileName, context.MODE_PRIVATE);

            byte [] bytes = writestr.getBytes();

            fout.write(bytes);

            fout.close();
        }

        catch(Exception e){
            e.printStackTrace();
            Log.d("writeFile","写文件异常");
        }
    }

    //读数据
    public static String readFile(String fileName,Context context) throws IOException{
        String res="";
        try{
            createFile(Constant.TOKEN_FILE,context);
            FileInputStream fin = context.openFileInput(fileName);
            int length = fin.available();
            byte [] buffer = new byte[length];
            fin.read(buffer);
            res = EncodingUtils.getString(buffer, "UTF-8");
            fin.close();
        }
        catch(Exception e){
            e.printStackTrace();
            Log.d("writeFile", "读文件异常");
        }
        return res;
    }

    public static void createFile(String fileName,Context context) throws IOException{
        try{
            File f = new File(context.getFilesDir(),fileName);
            if(!f.exists()){
                f.createNewFile();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            Log.d("writeFile", "添加文件异常");
        }
    }

    public static void clearFile(String fileName,Context context) throws IOException{
        try{
            File f = new File(context.getFilesDir(),fileName);
            if(!f.exists()){
                f.createNewFile();
            }else{
                f.delete();
                f.createNewFile();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            Log.d("writeFile", "清空文件异常");
        }
    }
}
