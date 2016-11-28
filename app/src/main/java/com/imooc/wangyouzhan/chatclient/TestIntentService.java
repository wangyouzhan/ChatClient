package com.imooc.wangyouzhan.chatclient;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class TestIntentService extends IntentService {

    private static final String TAG = "TestIntentService";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        System.out.println("onbind-----------------");
        Log.d(TAG, "onBind: fa");
        Log.d(TAG, "onBind() called with: intent = [" + intent + "]");

        Log.d(TAG, "onBind() called with: intent = [" + intent + "]");
        return super.onBind(intent);
    }

    public TestIntentService() {
        super("TestIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();


        System.out.print("oncreate-----------------");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("onstart-------------------");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onstartcommand--------------------");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getExtras().getString("param");
        if (action.equals("oper1")) {
            System.out.println("operation1-------------");
        } else if (action.equals("oper2")) {
            System.out.println("operation2-------------");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroy() {
        System.out.println("ondestroy------------------------");
        super.onDestroy();
    }
}
