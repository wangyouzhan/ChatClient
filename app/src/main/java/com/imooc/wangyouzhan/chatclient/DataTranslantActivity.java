package com.imooc.wangyouzhan.chatclient;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class DataTranslantActivity extends AppCompatActivity {


    private static final String TAG = "DataTranslantActivity";

    private int postion = 0;

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying())
        {
            postion = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: --------------------DataTranslantActivity");
        if (postion != 0){
            mediaPlayer.seekTo(postion);
            mediaPlayer.start();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: -----------------DataTranslantActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_translant);
        Log.d(TAG, "onCreate: --------------------DataTranslantActivity");



        Log.d(TAG, "onCreate: sonclick");
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),DataSaveActivity.class);
//                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                intent.setAction("www.wang.com");


                startActivity(intent);
            }
        });
        findViewById(R.id.btn_palyer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });


        mediaPlayer = MediaPlayer.create(this,R.raw.gequ);
    }
}
