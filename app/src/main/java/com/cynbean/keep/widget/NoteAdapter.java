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

/**
 * NoteAdapter
 * Created by BFD_303 on 2016/4/30.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> notes = new ArrayList<Note>();
    private Context mContext;

    public NoteAdapter(List<Note> notes, Context mContext) {
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
        Note note = notes.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        return notes == null ? 0 : notes.size();
    }

    public Note getNote(int position){
        Note note = notes.get(position);
        return note == null ? null : note;
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
