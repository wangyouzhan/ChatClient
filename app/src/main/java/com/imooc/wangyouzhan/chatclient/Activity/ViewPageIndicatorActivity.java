package com.imooc.wangyouzhan.chatclient.Activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.R;

import java.io.File;
import java.io.FileOutputStream;

public class ViewPageIndicatorActivity extends AppCompatActivity {


    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_indicator);

//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ViewPageIndicatorActivity.this);

//        SharedPreferences.Editor  prefEdit = pref.edit();
//        prefEdit.putString("wang","liming");
//        prefEdit.commit();

        getSystemService("");
        SharedPreferences preferences = getSharedPreferences("myPre", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", "张三");
        editor.putBoolean("defaulte", true);
        editor.commit();

//        editor.remove("name");
//        editor.commit();


        ((TextView) findViewById(R.id.textview_show)).setText(preferences.getString("name", "李四"));


        ((Button)findViewById(R.id.btn_skip_two)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long max = Runtime.getRuntime().maxMemory();
                long total = Runtime.getRuntime().totalMemory();
                System.out.println("max-----" + max / (1024 * 1024) + ": total----" + total / (1024 * 1024));
            }
        });

        ((Button)findViewById(R.id.btn_get_package)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);



            }
        });



        ((Button) findViewById(R.id.btn_skip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = getFilesDir();
                System.out.println("file:-- ---" + file.getAbsolutePath());
                File cache = getCacheDir();
                System.out.println("cache:----" + cache.getAbsolutePath());

                File externalDir = getExternalCacheDir();
                System.out.println("externaldri----" + externalDir.getAbsolutePath());

                try {
                    FileOutputStream fos = openFileOutput("name.txt", Context.MODE_PRIVATE);
                    fos.write("wangyouzhan".getBytes());
                    fos.flush();
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Intent intent = new Intent(ViewPageIndicatorActivity.this, SharePreferenceActivity.class);
                startActivity(intent);
            }
        });


    }
}






























