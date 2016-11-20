package com.imooc.wangyouzhan.chatclient.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.imooc.wangyouzhan.chatclient.R;

public class SqliteActivity extends AppCompatActivity {


    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        helper = DBHelper.getInstance(this);
        createDb();


    }


    public void createDb() {
        SQLiteDatabase db = helper.getWritableDatabase();
    }


    public void click(View view) {

        SQLiteDatabase db = helper.getWritableDatabase();

        switch (view.getId()) {
            case R.id.btn_create_table:
                String sql = "CREATE TABLE IF NOT EXISTS " + Constant.TABLE_NAME + "(" + Constant._ID +
                        " Integer primary key," + Constant.NAME + " varchar(10)," +
                        Constant.AGE + " Integer);";

                db.execSQL(sql);
                db.close();
                break;
            case R.id.btn_insert:
                String sql1 = "insert into " + Constant.TABLE_NAME + " values(1, 'zhangsan', 20);";
                DBHelper.execSQL(db, sql1);
                String sql2 = "insert into " + Constant.TABLE_NAME + " values(2, 'lisi', 20);";
                DBHelper.execSQL(db, sql2);
                db.close();
                break;
            case R.id.btn_update:
                String update = "update " + Constant.TABLE_NAME + " set " + Constant.NAME + "='xiaoming' where " + Constant._ID + "=1;";
                DBHelper.execSQL(db, update);
                db.close();
                break;
            case R.id.btn_delete:
                String deleteSql = "delete from " + Constant.TABLE_NAME + " where " + Constant._ID + "=2;";
                DBHelper.execSQL(db,deleteSql);
                db.close();

                break;
        }


    }


    /**
     * 绑定到视图的方法,使用contentValue来操作数据
     * @param view
     */
    public void clickTwo(View view) {

        SQLiteDatabase db = helper.getWritableDatabase();

        switch (view.getId()) {
            case R.id.btn_insert:
                ContentValues values = new ContentValues();
                values.put(Constant._ID,9);
                values.put(Constant.NAME,"张三");
                values.put(Constant.AGE , 40);
                long result = db.insert(Constant.TABLE_NAME, null, values);
                db.close();
                if (result > 0){
                    Toast.makeText(this,"插入成功", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btn_update:
                ContentValues updateStr = new ContentValues();
                updateStr.put(Constant._ID,9);
                updateStr.put(Constant.NAME,"张三");
                updateStr.put(Constant.AGE , 40);
                int count = db.update(Constant.TABLE_NAME, updateStr, Constant._ID + "=?", new String[]{"3"});
                if (count > 0){
                    Toast.makeText(this,"修改成功", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btn_delete:
                /**
                 * 删除数据
                 */
                int deleteResult = db.delete(Constant.TABLE_NAME, Constant._ID + "=?", new String[]{"3"});
                if (deleteResult > 0){
                    Toast.makeText(this,"修改成功", Toast.LENGTH_LONG).show();
                }

                break;
        }


    }

}



























