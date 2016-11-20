package com.imooc.wangyouzhan.chatclient;

import android.app.Application;

/**
 * Created by wangyouzhan on 16/10/13.
 */

public class MyApplication extends Application {


    @Override
    public void onTerminate() {
        super.onTerminate();
        System.out.println("ddddddddd.....");
    }




}
