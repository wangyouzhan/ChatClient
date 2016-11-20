package com.imooc.wangyouzhan.chatclient;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imooc.wangyouzhan.chatclient.app.DensityUtil;
import com.imooc.wangyouzhan.chatclient.okhttp.OKHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ScrollActivity extends AppCompatActivity implements View.OnClickListener {


    private LinearLayout layout;
    private Button btn_request;
    private TextView tv_test;
    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tv_test.setText(msg.obj.toString());

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        tv_test = (TextView)findViewById(R.id.tv_test);
        btn_request = (Button)findViewById(R.id.btn_request);
        btn_request.setOnClickListener(this);
        layout = (LinearLayout)findViewById(R.id.ll_layout);
        System.out.println("wang---dd---------");



        DensityUtil densityUtil = new DensityUtil(this);
        System.out.println("wang------" + densityUtil.toString());


        for (int i = 0; i < 7; i++){

            System.out.println("wang------------");
            Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.wang);

            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.height = densityUtil.dip2px(400);
            params.width = densityUtil.dip2px(300);
            params.setMargins(0, 50, 0, 0);
            params.gravity = Gravity.CENTER;
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
            imageView.setLayoutParams(params);
            imageView.setImageDrawable(drawable);
            imageView.setBackgroundColor(Color.GREEN);
            layout.addView(imageView);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_request){
//           String nameStr =  OKHttpUtil.getStringFromServer("http://127.0.0.1:8081/");

            OKHttpUtil.asyRequestTask("http://192.168.1.101:8081/", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.print("wang----failue" );

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {

                     String tempStr = response.body().string();
                    System.out.println("wang---" +  tempStr);
                    Message mes = new Message();
                    mes.obj = tempStr;
                    myHandler.sendMessage(mes);

                }
            });
        }
    }



}
