package com.imooc.wangyouzhan.chatclient.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wangyouzhan on 2016/11/15.
 * Email 936804097@qq.com
 */

public class MySqliteHelper extends SQLiteOpenHelper{

    private static final String TAG = "MySqliteHelper";

    public MySqliteHelper(Context context){
        super(context,Constant.DATABASE_NAME ,null, Constant.DATABASE_VERSION);
    }

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table " + Constant.TABLE_NAME + "(" + Constant._ID +
                " Integer primary key," + Constant.NAME + " varchar(10)," +
                Constant.AGE + " Integer)";

        db.execSQL(sql);
        Log.d(TAG, "onCreate: --------------------");
    }

    

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        Log.d(TAG, "onUpgrade: ---------------------");
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        Log.d(TAG, "onOpen: ------------------------");
    }
}
















































































