package com.cynbean.keep.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cynbean.keep.request.DataTest;
import com.cynbean.keep.request.NoteRequest;
import com.cynbean.keep.util.BaseApplication;
import com.cynbean.keep.util.Constant;
import com.cynbean.keep.widget.NoteAdapter;
import com.cynbean.keep.widget.NoteDetailActivity;
import com.cynbean.keep.R;
import com.cynbean.keep.widget.RecycleItemClickListener;
import com.cynbean.keep.entity.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BFD_303 on 2016/4/30.
 */
public class NotesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NoteAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FloatingActionButton mFabButton;
    private List<Object> notes = new ArrayList<Object>();

    private static final int ANIM_DURATION_FAB = 400;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notelist, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnItemTouchListener(new RecycleItemClickListener(getActivity(), onItemClickListener));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        Log.i("NoteFragment:","Note Fragment start");
        //获取测试数据
//        DataTest dataTest = new DataTest();
//        notes = dataTest.getTestData();

        BaseApplication application = BaseApplication.getInstance();
        Log.i("token:",Constant.TEST_TOKEN);
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.findAllNotes(Constant.TEST_TOKEN, new NoteRequest.NoteResponse<Map<String, Object>>() {
            @Override
            public void onData(Map<String, Object> data) {
                try {
                    Log.i("request all notes : ", (String) data.get("msg"));
                    Log.i("NOTES data ==> ", data.get("data").toString());
                    notes = (List<Object>) data.get("data");
                    Log.i("notes size ===>", notes.size() + "");
                }catch(NullPointerException e){
                    Log.e("NullPointerException" , "data is null");
                }

                //将数据与context封装进adapter
                mAdapter = new NoteAdapter(notes, getContext());
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        setUpFAB(view);
        return view;
    }

//    public List<Note> getData(){
//        return notes = (List<Note>) dbRead.query("note",null,null,null,null,null,null);
//    }

    /**
     * 新增记事操作
     * @param view
     */
    private void setUpFAB(View view) {
        mFabButton = (FloatingActionButton) view.findViewById(R.id.fab);
        mFabButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(),NoteDetailActivity.class);
                startActivity(intent);
            }
        });
    }


    private void startFABAnimation() {
        mFabButton.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(500)
                .setDuration(ANIM_DURATION_FAB)
                .start();
    }


    /**
     * 查看记事操作
     *
     */
    private RecycleItemClickListener.OnItemClickListener onItemClickListener = new RecycleItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Map<String,Object> note = (Map<String, Object>) mAdapter.getNote(position);
            Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
            intent.putExtra("note", String.valueOf(note));

            startActivity(intent);

//            ActivityOptionsCompat options =
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                            view.findViewById(R.id.ivBook), getString(R.string.transition_book_img));
//
//            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());

        }
    };

}
