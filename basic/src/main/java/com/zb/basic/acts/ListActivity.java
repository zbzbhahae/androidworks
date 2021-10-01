package com.zb.basic.acts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zb.basic.R;
import com.zb.basic.adapter.ListViewAdapter;
import com.zb.common.utils.P;

public class ListActivity extends BaseActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.list_view);

        lv.setAdapter(new ListViewAdapter());


    }
}