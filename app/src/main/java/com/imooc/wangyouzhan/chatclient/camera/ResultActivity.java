package com.imooc.wangyouzhan.chatclient.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by wangyouzhan on 2016/11/19.
 * Email 936804097@qq.com
 */
public class ResultActivity extends AppCompatActivity{

    private ImageView img_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        img_view = (ImageView) findViewById(R.id.img_view);

        String path = getIntent().getStringExtra("picPath");
        try {
            FileInputStream fis = new FileInputStream(path);

            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(), matrix, true);
            img_view.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}



































