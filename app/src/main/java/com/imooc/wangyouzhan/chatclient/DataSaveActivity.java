package com.imooc.wangyouzhan.chatclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DataSaveActivity extends AppCompatActivity {

    private TextView tv_show_msg;
    private static final String TAG = "DataSaveActivity";


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name","wangyouzhan");
        Log.d(TAG, "onSaveInstanceState: ----------------");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState:---two argument --------------");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ----------------------------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ------------------------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ---------------------");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: --------------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ---------------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: -----------------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ----------------------");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_save);
        Log.d(TAG, "onCreate: ----------------------------");

        if (savedInstanceState != null) {
            Toast.makeText(getApplicationContext(),savedInstanceState.getString("name",""),Toast.LENGTH_SHORT).show();
        }

        long startTime = System.nanoTime();

        findViewById(R.id.btn_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DataSaveActivity.class);
                startActivity(intent);
            }
        });
        long endTime = System.nanoTime();
        Log.d(TAG, "onCreate: ---------" + (endTime - startTime));

        tv_show_msg = (TextView) findViewById(R.id.tv_show_msg);
        Intent intent  = getIntent();
        if (intent != null) {
            int[] array = intent.getIntArrayExtra("data");


        }



    }


}
