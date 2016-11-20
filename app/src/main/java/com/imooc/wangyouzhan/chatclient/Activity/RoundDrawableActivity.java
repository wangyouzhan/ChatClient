package com.imooc.wangyouzhan.chatclient.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.R;

public class RoundDrawableActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_view;
    private ImageView img_two;
    private ImageView img_clip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_drawable);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.icon_app);

        img_view = (ImageView)findViewById(R.id.img_view);
//        img_view.setImageDrawable(new CircleDrawable(bitmap));
        img_view.setImageDrawable(new RoundDrawable(bitmap));
        System.out.println(findViewById(R.id.ll_layout).getBackground().toString());

        findViewById(R.id.btn_on).setOnClickListener(this);
        findViewById(R.id.btn_close).setOnClickListener(this);
        img_two = (ImageView)findViewById(R.id.img_two);
        img_clip = (ImageView)findViewById(R.id.img_clip);
        ClipDrawable clipDrawable =  (ClipDrawable) img_clip.getDrawable();
        clipDrawable.setLevel(5000);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_close){
            img_two.setImageLevel(3);

        }else if (viewId == R.id.btn_on){

            img_two.setImageLevel(9);
        }

    }
}






























