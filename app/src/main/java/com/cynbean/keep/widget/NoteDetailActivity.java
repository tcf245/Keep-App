package com.cynbean.keep.widget;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.cynbean.keep.R;
import com.cynbean.keep.entity.Note;
import com.cynbean.keep.request.NoteRequest;
import com.cynbean.keep.util.BaseApplication;
import com.cynbean.keep.util.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

    private Map<String,Object> note ;
    private boolean isNew = true;
    private int id;
    private ImageView img;
    private NoteRequest noteRequest = new NoteRequest();
    private BaseApplication application = BaseApplication.getInstance();
    private Note newNote = new Note();
    private Note rawNote = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_details);

        etTitle = (EditText) findViewById(R.id.editTextTitle);
        etContent = (EditText) findViewById(R.id.editTextContent);
        tvUpdateTime = (TextView) findViewById(R.id.tvUpdataTime);
        img = (ImageView) findViewById(R.id.img);

        bottom = (Toolbar) findViewById(R.id.toolbar);
        setFAB();

        initDetails();

    }

    @Override
    protected void onStop() {
        super.onStop();

        newNote.setTitle(etTitle.getText().toString());
        newNote.setColor(0);
        newNote.setContent(etContent.getText().toString());
        newNote.setCreate_time(new Date());
        newNote.setUpdate_time(new Date());

        if(isNew){
            addNote();
        }
        if (!isNew ){
            newNote.setId(id);
            updateNote();
        }
    }


    /**
     * 初始化详情页，判断是否为新添加记事
     */
    private void initDetails() {
        note = (Map<String, Object>) JSON.parse((String) getIntent().getSerializableExtra("note"));
        if(note != null){
            isNew = false;
            id = (int) note.get("id");
            etTitle.setText((String) note.get("title"));
            etContent.setText((String) note.get("content"));
            tvUpdateTime.setText((String) note.get("createTime"));
            Glide.with(img.getContext())
                    .load(Constant.BASE_URL + note.get("pic"))
                    .fitCenter()
                    .into(img);

            /** 保存原有记事内容判断是否修改*/
            rawNote.setTitle((String) note.get("title"));
            rawNote.setContent((String) note.get("content"));
            rawNote.setPic((String) note.get("pic"));
            rawNote.setColor((Integer) note.get("color"));
        }else{
            isNew = true;
            newNote = new Note();
        }
    }

    /**
     * 添加记事
     */
    public void addNote(){

        noteRequest.addNotes(application.getToken(), newNote, new NoteRequest.NoteResponse<Map<String, Object>>() {
            @Override
            public void onData(Map<String, Object> data) {
                boolean flag = (boolean) data.get("flag");
                String msg = (String) data.get("msg");
                Toast.makeText(NoteDetailActivity.this, "msg", Toast.LENGTH_SHORT).show();
                if(flag){

                }else{
                    addNote();
                }

            }
        });
    }

    /**
     * 修改记事
     */
    public void updateNote(){
        noteRequest.updateNote(application.getToken(), newNote, new NoteRequest.NoteResponse<Map<String, Object>>() {
            @Override
            public void onData(Map<String, Object> data) {
                boolean flag = (boolean) data.get("flag");
                String msg = (String) data.get("msg");
                Toast.makeText(NoteDetailActivity.this, "msg", Toast.LENGTH_SHORT).show();
                if (flag) {

                } else {
                    updateNote();
                }
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tags:
                Toast.makeText(this, "Tags", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    /**
//     * 保存对笔记内容的修改
//     */
//    private void saveNote() {
//        boolean isUpdate = true;
//        String title = etTitle.getText().toString();
//        String content = etContent.getText().toString();
//
//        if (note.getTitle().equals(title) && note.getContent().equals(content)){
//                isUpdate = false;
//        }
//
//        if(isUpdate){
//
//            note.setTitle(title);
//            note.setContent(content);
//            note.setUpdate_time(new Date());
//            if(isNew){
//                note.setCreate_time(new Date());
//            }
//            ContentValues cv = new ContentValues();
//
//            cv.put(Constant.FIELD_TITLE, note.getTitle());
//            cv.put(Constant.FIELD_CONTENT, note.getContent());
//            cv.put(Constant.FIELD_UPDATE_TIME, String.valueOf(note.getUpdate_time()));
//
//            if(isNew){
//                cv.put(Constant.FIELD_CREATE_TIME, String.valueOf(note.getCreate_time()));
//                dbWrite.insert(Constant.TABLE_NOTE, null, cv);
//            }else{
//                dbWrite.update(Constant.TABLE_NOTE,cv, Constant.FIELD_ID + "=?", new String[]{note.getId()+""});
//            }
//        }
//    }


}
