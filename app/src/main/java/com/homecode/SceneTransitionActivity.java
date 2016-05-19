package com.homecode;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.homecode.adapters.SceneTransitionAdapter;

import java.util.ArrayList;

public class SceneTransitionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_transition);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Utility.getDataItems(this, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                dataItems = (ArrayList<DataItem>) bundle.getSerializable("list");
                SceneTransitionAdapter adapter = new SceneTransitionAdapter(SceneTransitionActivity.this, dataItems);
                recyclerView.setAdapter(adapter);
                return false;
            }
        });
    }
}
