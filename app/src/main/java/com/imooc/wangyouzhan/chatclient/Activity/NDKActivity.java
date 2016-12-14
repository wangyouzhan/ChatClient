package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.imooc.wangyouzhan.chatclient.R;

public class NDKActivity extends AppCompatActivity {


    private TextView tv_show;
    private Button btn_binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);

        tv_show = (TextView) findViewById(R.id.tv_show);
        btn_binder = (Button) findViewById(R.id.btn_binder);
        btn_binder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tv_show.setText(hello() + getFromJNI() + sayHello());
                Toast.makeText(getApplicationContext(), getFromJNI(), Toast.LENGTH_SHORT).show();
                Log.w("dd", Environment.getExternalStorageDirectory().getAbsolutePath());
                String flag = updateFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/body.txt");

            }
        });
    }



    public native String hello();
    public native String getFromJNI();
    public native String sayHello();
    public static native String updateFile(String file);

    static {
        System.loadLibrary("JniDemo");
    }




}
