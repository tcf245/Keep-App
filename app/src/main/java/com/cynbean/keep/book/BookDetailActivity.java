package com.cynbean.keep.book;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cynbean.keep.R;
import com.cynbean.keep.entity.Note;
import com.cynbean.keep.request.NoteRequest;
import com.cynbean.keep.util.BaseApplication;
import com.cynbean.keep.util.DataResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookDetailActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private Book mBook;
    private FloatingActionButton fabButton;
    private BaseApplication application = BaseApplication.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        fabButton = (FloatingActionButton) findViewById(R.id.fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteRequest noteRequest = new NoteRequest();
                Note note = new Note();
                note.setTitle(mBook.getTitle());
                note.setContent(mBook.getSummary());

                noteRequest.addNotes(application.getToken(), note, new DataResponse<Map<String, Object>>() {
                    @Override
                    public List<Map<String, Object>> onData(Map<String, Object> data) {
                        try{
                            boolean flag = (boolean) data.get("flag");
                            String msg = (String) data.get("msg");
                            Toast.makeText(BookDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
                            if(flag){

                            }else{
//                              addNote();
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                            Toast.makeText(BookDetailActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                });
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBook = (Book) getIntent().getSerializableExtra("book");
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mBook.getTitle());

        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        Glide.with(ivImage.getContext())
                .load(mBook.getImages().getLarge())
                .fitCenter()
                .into(ivImage);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("内容简介"));
        tabLayout.addTab(tabLayout.newTab().setText("作者简介"));
        tabLayout.addTab(tabLayout.newTab().setText("目录"));
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(mBook.getSummary()), "内容简介");
        adapter.addFragment(DetailFragment.newInstance(mBook.getAuthor_intro()), "作者简介");
        adapter.addFragment(DetailFragment.newInstance(mBook.getCatalog()), "目录");
        mViewPager.setAdapter(adapter);
    }


    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
