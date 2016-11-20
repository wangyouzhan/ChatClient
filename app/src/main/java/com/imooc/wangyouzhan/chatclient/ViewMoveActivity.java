package com.imooc.wangyouzhan.chatclient;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class ViewMoveActivity extends AppCompatActivity implements View.OnTouchListener{



    private ImageView img1;
    private ImageView img2;
    private int screenWidth;
    private  int screenHeight;
    private Button btn_crash;
    private  Button btn_shan;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_move);

        btn_crash = (Button)findViewById(R.id.btn_crash);
        btn_shan = (Button)findViewById(R.id.btn_shan);
        btn_crash.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {//当RuntimeException时候,引起Crash崩溃(有提示信息)
                    int i = 10;
                int j = 0;
                int k = i / j;

            }
        });

        btn_shan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {//当加载Native的类没有的时候引起闪退(没有系统提示)
                System.loadLibrary("crashtest");
            }
        });

        img1 = (ImageView)this.findViewById(R.id.image1);


        img2 = (ImageView)this.findViewById(R.id.image2);

        img1.setOnTouchListener(this);
        img2.setOnTouchListener(this);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        screenHeight = dm.heightPixels - 50;

    }


    int lastX=0;
    int lastY = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                lastX = (int)event.getRawX();
                lastY = (int)event.getRawY();

                break;

            case  MotionEvent.ACTION_MOVE:


                System.out.println("x---" + lastX + ":y----" + lastY);

                int dx = (int)event.getRawX() - lastX;
                int dy = (int)event.getRawY() - lastY;

                System.out.println("dx--" + dx + ":dy--" + dy);

                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;

                if (left < 0){
                    left = 0;
                    right = left + v.getWidth();
                }

                if (right > screenWidth)
                {
                    right = screenWidth;
                    left = right - v.getWidth();
                }


                if (top < 0)
                {
                    top = 0;
                    bottom = top + v.getHeight();
                }

                if (bottom > screenHeight)
                {
                    bottom = screenHeight;
                    top = bottom - v.getHeight();
                }

                v.layout(left,top,right,bottom);

                lastX = (int)event.getRawX();
                lastY = (int)event.getRawY();

                break;
            case MotionEvent.ACTION_UP:



                break;
        }


        return true;
    }
}



















