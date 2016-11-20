package com.imooc.wangyouzhan.chatclient;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


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



