package com.cynbean.keep.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cynbean.keep.R;
import com.cynbean.keep.entity.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * NoteAdapter
 * Created by BFD_303 on 2016/4/30.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Object> notes = new ArrayList<Object>();
    private Context mContext;

    public NoteAdapter(List<Object> notes, Context mContext) {
        this.notes = notes;
        this.mContext = mContext;
    }



    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 给ViewHolder设置布局文件
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_note_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder holder, int position) {
        // 给ViewHolder设置元素
        Map<String,Object> note = (Map<String, Object>) notes.get(position);
        holder.tvTitle.setText((String)note.get("title"));
        holder.tvContent.setText((String) note.get("content"));
    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        return notes == null ? 0 : notes.size();
    }

    public Object getNote(int position){
        Map<String,Object> note = (Map<String, Object>) notes.get(position);
        return note;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
        }
    }

}
