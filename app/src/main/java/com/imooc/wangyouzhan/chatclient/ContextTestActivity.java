package com.imooc.wangyouzhan.chatclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ContextTestActivity extends AppCompatActivity {

    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_test);

        Log.e("Test",getvalue(5) + "");
        Log.e("Test",getvalue(5000) + "");

    }


    private int getvalue(int ivalue)
    {
        if (ivalue >= 0)
        {
            return getvalue(--ivalue);
        }
        return  ivalue;
    }

}
































