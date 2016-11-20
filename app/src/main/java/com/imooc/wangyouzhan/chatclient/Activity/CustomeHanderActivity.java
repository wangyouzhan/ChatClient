package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.R;

public class CustomeHanderActivity extends AppCompatActivity {


    private Handler handlerTwo = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            System.out.println("uithread --" + Thread.currentThread());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_hander);

            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ((TextView)findViewById(R.id.tv_test)).setText("asdfasdf");
                }
            }.start();

//        thread =  new MyThread();
//        thread.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        thread.handler.sendEmptyMessage(2);
//        handlerTwo.sendEmptyMessage(1);
    }



    class MyThread extends Thread{

        public  Handler handler;
        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {

                    System.out.println("childThread:  --" + Thread.currentThread());
                }
            };
            Looper.loop();


        }
    }

    private MyThread thread;



}
