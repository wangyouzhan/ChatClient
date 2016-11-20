package com.imooc.wangyouzhan.chatclient;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class IntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        Intent startServiceIntent = new Intent();
        startServiceIntent.setComponent(new ComponentName(getPackageName(),"com.imooc.wangyouzhan.chatclient.TestIntentService"));
        Bundle bundle = new Bundle();
        bundle.putString("param","oper1");
        startServiceIntent.putExtras(bundle);
        startService(startServiceIntent);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.icon_app);
//
        Intent startServiceIntent2 = new Intent();
        startServiceIntent2.setComponent(new ComponentName(getPackageName(),"com.imooc.wangyouzhan.chatclient.TestIntentService"));
        Bundle bundle2 = new Bundle();
        bundle2.putString("param","oper2");
        startServiceIntent2.putExtras(bundle2);
        startService(startServiceIntent2);

         findViewById (R.id.btn_click).setOnClickListener(new View.OnClickListener() {
             public static final String TAG = "wang" ;

             @Override
             public void onClick(View v) {
                 int a = 0;
                 int b  = 4;
                 int sum = a + b;
                 Log.d(TAG, "onClick: " + sum);



             }
         });





    }
}
