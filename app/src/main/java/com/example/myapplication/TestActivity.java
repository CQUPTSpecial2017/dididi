package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.R;

public class TestActivity extends AppCompatActivity {
RecyclerView testRecyclev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();

    }
    public void  init(){
        RoomRecycleAdapter recycleAdapter=new RoomRecycleAdapter(this);
        testRecyclev=(RecyclerView)findViewById(R.id.testRec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        testRecyclev.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        testRecyclev.setAdapter(recycleAdapter);



    }}
