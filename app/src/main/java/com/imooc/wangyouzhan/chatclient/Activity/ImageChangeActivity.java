package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.imooc.wangyouzhan.chatclient.R;

import static com.imooc.wangyouzhan.chatclient.R.id.img_view;

public class ImageChangeActivity extends AppCompatActivity {


    class Person {
        int age;
        String name;
    }

    private int images[] = {R.mipmap.activity_live_active, R.mipmap.activity_shop_active_9, R.mipmap.activity_star_active};

    private ImageView image_view;
    private Handler handler = new Handler(
            new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
    ) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
            ((TextView) findViewById(R.id.tv_test)).setText(((Person) msg.obj).name);
        }
    };

    private int index;
    private MyRunnable myRunnable = new MyRunnable();

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            index++;
            index = index % 3;
            image_view.setImageResource(images[index]);
            handler.postDelayed(myRunnable, 1000);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_change);
        image_view = (ImageView) findViewById(img_view);
        handler.postDelayed(myRunnable, 1000);


        Message message = handler.obtainMessage();
        message.arg1 = 10;
        Person person = new Person();
        person.age = 10;
        person.name = "wang";
        message.obj = person;
        message.sendToTarget();
        handler.sendMessage(message);

        Looper.loop();


    }
}
