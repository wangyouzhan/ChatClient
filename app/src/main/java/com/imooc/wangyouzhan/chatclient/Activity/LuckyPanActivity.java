package com.imooc.wangyouzhan.chatclient.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.imooc.wangyouzhan.chatclient.R;


/**
 * 抽奖轮盘
 */
public class LuckyPanActivity extends AppCompatActivity {


    private LuckyPanSurfaceView luckyPanSurfaceView;
    private ImageView img_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_pan);

        luckyPanSurfaceView = (LuckyPanSurfaceView) findViewById(R.id.luck_pan);
        img_button = (ImageView) findViewById(R.id.img_button);

        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!luckyPanSurfaceView.isStart())
                {
                    luckyPanSurfaceView.luckyStart(1);
                    img_button.setImageResource(R.mipmap.arrow_down);
                }else{
                    if (!luckyPanSurfaceView.isShouldEnd())
                    {
                        luckyPanSurfaceView.luckyEnd();
                        img_button.setImageResource(R.mipmap.ic_launcher);
                    }
                }
            }
        });
    }



}



























