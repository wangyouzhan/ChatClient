package com.imooc.wangyouzhan.chatclient.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.R;

public class DrawableActivity extends AppCompatActivity {

    private ImageView img_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable2);

        img_view = (ImageView) findViewById(R.id.img_view);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.wang);
        CicleImageDrawable cicleImageDrawable = new CicleImageDrawable(bitmap);
        img_view.setImageDrawable(cicleImageDrawable);

    }
}






























