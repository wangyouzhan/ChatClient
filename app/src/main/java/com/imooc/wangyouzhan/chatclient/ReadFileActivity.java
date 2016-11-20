package com.imooc.wangyouzhan.chatclient;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReadFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    InputStream is = getResources().getAssets().open("test.txt");
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bis = new BufferedReader(isr);
                    String line;
                    while ((line = bis.readLine()) != null) {
                        System.out.println(line);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        findViewById(R.id.btn_raw_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               try{

                   InputStream inputStream = getResources().openRawResource(R.raw.test);
                   InputStreamReader isr = new InputStreamReader(inputStream);
                   BufferedReader bis = new BufferedReader(isr);
                   String line;
                   while ((line = bis.readLine()) != null) {
                       System.out.println(line);
                   }
               }catch (IOException ex){
               }

            }
        });

        findViewById(R.id.btn_raw_file_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try{
                   FileOutputStream fos = openFileOutput("wang.txt", Context.MODE_PRIVATE);
                   OutputStreamWriter writer = new OutputStreamWriter(fos);
                   writer.write("asdfasdf我");
                   writer.flush();
                   fos.flush();
                   writer.close();
                   fos.close();

               }catch (IOException ios){

               }


            }
        });

        findViewById(R.id.btn_sdcard_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File sdcard = Environment.getExternalStorageDirectory();
                File myfile = new File(sdcard,"This is file.txt");

                if (!sdcard.exists()){
                    Toast.makeText(getApplicationContext(),"adf",Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    myfile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(myfile);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.write("dddasldfkasd我们");
                    osw.flush();
                    fos.flush();
                    osw.close();
                    fos.close();
                    Toast.makeText(getApplicationContext(),"写入完成" + sdcard.getAbsolutePath(),Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

}


















