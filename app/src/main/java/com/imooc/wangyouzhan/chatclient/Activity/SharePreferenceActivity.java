package com.imooc.wangyouzhan.chatclient.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.R;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class SharePreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);


//        SharedPreferences preferences = getSharedPreferences("myPre", MODE_PRIVATE);

//        String nameStr = preferences.getString("name", "wang");

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SharePreferenceActivity.this);
        String nameStr = pref.getString("wang","无");

        ((TextView)findViewById(R.id.tv_wang)).setText(nameStr);


        try {
            FileInputStream fileInputStream = openFileInput("name.txt");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len=fileInputStream.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            String content = baos.toString();
            System.out.println("----------------:" + content);
            fileInputStream.close();
            baos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
