package com.cynbean.keep.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BFD_303 on 2016/5/2.
 */
public class NoteDB extends SQLiteOpenHelper {

    private String charset = "utf-8";

    public NoteDB(Context context) {
        super(context, "keep", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_note = "DROP TABLE IF EXISTS note;\n" +
                "CREATE TABLE note (\n" +
                "  id int(11) NOT NULL AUTOINCREMENT,\n" +
                "  color int(11) NOT NULL,\n" +
                "  content varchar(255) DEFAULT NULL,\n" +
                "  create_time datetime DEFAULT NULL,\n" +
                "  pic varchar(255) DEFAULT NULL,\n" +
                "  status int(11) NOT NULL,\n" +
                "  title varchar(255) DEFAULT NULL,\n" +
                "  update_time datetime DEFAULT NULL,\n" +
                "  owner_id int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (id),\n" +
                "  )";

        String table_tag = "DROP TABLE IF EXISTS tag;\n" +
                "CREATE TABLE tag (\n" +
                "  id int(11) NOT NULL AUTOINCREMENT,\n" +
                "  title varchar(255) DEFAULT NULL,\n" +
                "  PRIMARY KEY (id)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        String table_user = "DROP TABLE IF EXISTS user;\n" +
                "CREATE TABLE user (\n" +
                "  id int(11) NOT NULL AUTOINCREMENT,\n" +
                "  password varchar(255) DEFAULT NULL,\n" +
                "  username varchar(255) DEFAULT NULL,\n" +
                "  PRIMARY KEY (id)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";


        db.execSQL(table_note);
        db.execSQL(table_tag);
        db.execSQL(table_user);



//        try {
//            String sql = FileUtils.readFileToString(new File("etc/keep.sql"),charset);
//            System.out.println(sql);
//            db.execSQL(sql);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
}
