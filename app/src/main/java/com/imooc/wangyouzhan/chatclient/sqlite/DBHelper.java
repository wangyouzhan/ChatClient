package com.imooc.wangyouzhan.chatclient.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wangyouzhan on 2016/11/15.
 * Email 936804097@qq.com
 */

public class DBHelper {

    private static MySqliteHelper helper;

    public static MySqliteHelper getInstance(Context context){
        if (helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }


    /**
     * 根据sql语句执行
     * @param db
     * @param sql
     */
    public static void execSQL(SQLiteDatabase db,String sql){
        if (db != null) {
            if (sql != null && !"".equals(sql)) {
                db.execSQL(sql);
            }
        }


    }


}
