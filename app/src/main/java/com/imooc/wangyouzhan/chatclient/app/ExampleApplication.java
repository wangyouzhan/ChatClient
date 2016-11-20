package com.imooc.wangyouzhan.chatclient.app;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by wangyouzhan on 16/10/28.
 */

public class ExampleApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        
        refWatcher = LeakCanary.install(this);

    }

    private   RefWatcher refWatcher;

    public  static RefWatcher getRefWatcher(Context context){
        ExampleApplication application = (ExampleApplication)context.getApplicationContext();
        return  application.refWatcher;
    }




}
