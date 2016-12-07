package com.imooc.wangyouzhan.chatclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ActivityA extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);



        findViewById(R.id.btn_onclick).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent intent = new Intent();
//                intent.setClass(ActivityA.this,FragmentActivity.class);
                intent.setClass(ActivityA.this,ActivityB.class);
                ActivityA.this.startActivity(intent);


            }
        });

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
