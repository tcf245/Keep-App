package com.cynbean.keep;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cynbean.keep.request.TagRequest;
import com.cynbean.keep.util.BaseApplication;
import com.cynbean.keep.util.DataResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tcf24 on 2016/5/29.
 */
public class TagsActivity extends AppCompatActivity {

    private ListView listView;
    private TagRequest tagRequest;
    private List<Map<String,Object>> tags = new ArrayList<>();
    private BaseApplication application = BaseApplication.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(listView);
        listView = (ListView) findViewById(R.id.lv_labels);

//        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.tag_item,getData()));


        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.tag_item,
                new String[]{"title"},
                new int[]{R.id.tvTag});
        listView.setAdapter(adapter);

    }

    private List<Map<String,Object>> getData() {
        tagRequest.findAllTags(application.getToken(), new DataResponse<Map<String, Object>>() {
            @Override
            public List<Map<String, Object>> onData(Map<String, Object> data) {
                boolean flag = (boolean) data.get("flag");
                String msg = (String) data.get("msg");
                tags = (List<Map<String, Object>>) data.get("data");
                Toast.makeText(TagsActivity.this, msg, Toast.LENGTH_SHORT).show();
                return null;
            }
        });
        return tags;
    }
}
