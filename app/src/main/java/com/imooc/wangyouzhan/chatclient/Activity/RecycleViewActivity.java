package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycle_view);

        recyclerView = new RecyclerView(this);
        setContentView(recyclerView);


        recyclerView.setLayoutManager(new GridLayoutManager(this,4,LinearLayoutManager.HORIZONTAL,true));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));

        recyclerView.setAdapter(new MyAdapter());

    }


}





























