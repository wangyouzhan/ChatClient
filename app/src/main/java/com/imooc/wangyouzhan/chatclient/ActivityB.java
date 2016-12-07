package com.imooc.wangyouzhan.chatclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);


        findViewById(R.id.btn_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityB.this.getApplicationContext(),ActivityC.class);
                ActivityB.this.startActivity(intent);
            }
        });

        ImageView imageView = new ImageView(this);


        getActionBar().setDisplayShowHomeEnabled(true);


    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("------onStart()----:" + this.getClass().getName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("------onRestart()----:" + this.getClass().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("------onResume()----:" + this.getClass().getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("------onPause()----:" + this.getClass().getName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("------onStop()----:" + this.getClass().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("------onDestroy()----:" + this.getClass().getName());
    }
}
