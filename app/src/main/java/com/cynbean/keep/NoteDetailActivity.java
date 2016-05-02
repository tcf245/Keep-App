package com.cynbean.keep;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.cynbean.keep.db.NoteDB;
import com.cynbean.keep.entity.Note;
import com.cynbean.keep.util.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * Created by BFD_303 on 2016/4/30 .
 */
public class NoteDetailActivity extends AppCompatActivity{

    private EditText etTitle;
    private EditText etContent;
    private TextView tvUpdateTime;
    private FloatingActionButton fab;
    private Toolbar bottom;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private Note note;
    private boolean isNew = true;
    private NoteDB db;
    private SQLiteDatabase dbRead,dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_details);

        etTitle = (EditText) findViewById(R.id.editTextTitle);
        etContent = (EditText) findViewById(R.id.editTextContent);
        tvUpdateTime = (TextView) findViewById(R.id.tvUpdataTime);

        bottom = (Toolbar) findViewById(R.id.toolbar);
        setFAB();

        db = new NoteDB(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        initDetails();

    }

    /**
     * 初始化详情页，判断是否为新添加记事
     */
    private void initDetails() {
        note = (Note) getIntent().getSerializableExtra("note");
        if(note != null){
            isNew = false;
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
            tvUpdateTime.setText(getDate(note.getUpdate_time()));
        }else{
            isNew = true;
            note = new Note();
        }
    }

    /**
     * FloatingActionBar 相关设置
     */
    public void setFAB(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    /**
     * 将Date类型数据转换成 yyyy-MM-dd 格式String
     * @param date
     * @return
     */
    public String getDate(Date date){
        if(date == null){
            return "";
        }
        String resultDate = format.format(date);
        return resultDate;
    }

    /**
     * 在Activity生命周期onPause中保存被修改的日志
     */
    @Override
    protected void onPause() {
        super.onPause();

//        saveNote();

    }

    /**
     * 保存对笔记内容的修改
     */
    private void saveNote() {
        boolean isUpdate = true;
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();

        if (note.getTitle().equals(title) && note.getContent().equals(content)){
                isUpdate = false;
        }

        if(isUpdate){

            note.setTitle(title);
            note.setContent(content);
            note.setUpdate_time(new Date());
            if(isNew){
                note.setCreate_time(new Date());
            }
            ContentValues cv = new ContentValues();

            cv.put(Constant.FIELD_TITLE, note.getTitle());
            cv.put(Constant.FIELD_CONTENT, note.getContent());
            cv.put(Constant.FIELD_UPDATE_TIME, String.valueOf(note.getUpdate_time()));

            if(isNew){
                cv.put(Constant.FIELD_CREATE_TIME, String.valueOf(note.getCreate_time()));
                dbWrite.insert(Constant.TABLE_NOTE, null, cv);
            }else{
                dbWrite.update(Constant.TABLE_NOTE,cv, Constant.FIELD_ID + "=?", new String[]{note.getId()+""});
            }
        }
    }


}
